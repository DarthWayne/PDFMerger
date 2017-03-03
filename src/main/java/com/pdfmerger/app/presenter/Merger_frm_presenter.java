package com.pdfmerger.app.presenter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.components.AbstractPresenter;
import com.pdfmerger.app.ui.Merger_frm;
import com.pdfmerger.app.ui.Preview_pnl;

public class Merger_frm_presenter extends AbstractPresenter {

	private String[] supportedFormats = { ".pdf", ".jpg", ".jpeg", ".tif", ".tiff", ".gif", ".bmp", ".png" };
	private PDDocument workingDoc;
	private File workingfile;
	private Map<Preview_pnl_presenter, PDPage> mapPages = new HashMap<Preview_pnl_presenter, PDPage>();

	public Merger_frm_presenter(Merger_frm objViewContainer) {
		super(objViewContainer);
	}

	@Override
	public void initialize() {
		super.initialize();

		try {
			workingfile = File.createTempFile("PdfMergerWorkingFile", "pdf");
			workingfile.deleteOnExit();
			getView().getPnlPreviews().removeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Merger_frm getView() {
		return (Merger_frm) getObjViewContainer();
	}

	@Override
	public void registerActions() {
		getView().getSliThumbnailSize().addChangeListener(e -> updateThumbnails());
		getView().getBtnImport().addActionListener(e -> importFile());
		getView().getMniImport().addActionListener(e -> importFile());

		getView().getMniOpen().addActionListener(e -> open());

		getView().getMniSave().addActionListener(e -> save());
		getView().getBtnSaveAs().addActionListener(e -> save());
		getView().getMniExit().addActionListener(e -> System.exit(0));
		getView().getMniDeleteAll().addActionListener(e -> clear());
	}

	private void updateThumbnails() {
		getView().getLblThumbnailSizeValue().setText(getView().getSliThumbnailSize().getValue() + " px");

		if (!getView().getSliThumbnailSize().getValueIsAdjusting()) {
			for (Preview_pnl_presenter pre : mapPages.keySet()) {
				pre.scaleImage(getView().getSliThumbnailSize().getValue());
			}
			getView().repaint();
			getView().revalidate();
		}
	}

	private void open() {
		clear();
		importFile();
	}

	private void importFile() {
		JFileChooser filechooser = new JFileChooser();
		filechooser.setFileFilter(new ImportFileFilter());
		filechooser.setMultiSelectionEnabled(true);

		if (filechooser.showOpenDialog(getView()) == JFileChooser.APPROVE_OPTION) {
			ExecutorService exec = Executors.newSingleThreadExecutor();
			exec.execute(() -> importFiles(filechooser.getSelectedFiles()));
			exec.shutdown();
		}
	}

	public void importFiles(File[] files) {
		for (File file : files) {
			if (!file.exists()) {
				continue;
			}
			if (file.isDirectory()) {
				// import whole directoy
				importFiles(file.listFiles());
			} else if (file.getName().endsWith(".pdf")) {
				// import single pdf
				importPdf(file);
			} else {
				// try to import an image
				try {
					importPdf(convertImageToPdf(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		getView().repaint();
		getView().revalidate();

	}

	private void importPdf(File file) {
		try {
			if (workingDoc == null) {
				workingDoc = new PDDocument();
			} else {
				workingDoc = PDDocument.load(workingfile);
			}

			PDDocument importDoc = PDDocument.load(file);
			PDFRenderer pdfRenderer = new PDFRenderer(importDoc);

			for (int i = 0; i < importDoc.getNumberOfPages(); i++) {
				getView().getLblStatusbar()
						.setText("Importing page " + (i + 1) + " of " + importDoc.getNumberOfPages());
				Preview_pnl_presenter prePreview = new Preview_pnl_presenter(new Preview_pnl());
				prePreview.setImage(pdfRenderer.renderImageWithDPI(i, 100, ImageType.RGB));
				prePreview.scaleImage(getView().getSliThumbnailSize().getValue());

				getView().getPnlPreviews().add(prePreview.getView());
				mapPages.put(prePreview, workingDoc.importPage(importDoc.getPage(i)));
			}
			if (workingDoc.getNumberOfPages() > 0) {
				workingDoc.save(workingfile);
			}
			workingDoc.close();
			importDoc.close();
			getView().getLblStatusbar().setText("Successfully imported " + file.getName());

		} catch (IOException e) {
			getView().getLblStatusbar().setText("Import failed: " + e.getLocalizedMessage());
			e.printStackTrace();
			try {
				workingDoc.close();
			} catch (IOException e1) {
				System.out.println("You fucked up");
			}
		}
	}

	private File convertImageToPdf(File file) throws IOException {

		PDDocument importDoc = new PDDocument();
		importDoc.addPage(new PDPage());
		
		PDImageXObject image = PDImageXObject.createFromFileByExtension(file, importDoc);
		PDPageContentStream contentStream = new PDPageContentStream(importDoc, importDoc.getPage(0));

		float docw = importDoc.getPage(0).getMediaBox().getWidth();
		float doch = importDoc.getPage(0).getMediaBox().getHeight();

		float docratio = doch / docw;
		float imgratio = (float) image.getHeight() / (float) image.getWidth();

		boolean docratiobigger = docratio > imgratio;
		float scale = docratiobigger ? docw / image.getWidth() : doch / image.getHeight();
		float w = docratiobigger ? docw : image.getWidth() * scale;
		float h = docratiobigger ? image.getHeight() * scale : doch;
		float x = docratiobigger ? 0 : (docw - w) / 2;
		float y = docratiobigger ? (doch - h) / 2 : 0;

		contentStream.drawImage(image, x, y, w, h);
		contentStream.close();

		File tempfile = File.createTempFile("tempImg", ".pdf");
		tempfile.deleteOnExit();
		importDoc.save(tempfile);

		return tempfile;
	}

	private void save() {
		try {
			File desktop = new File(System.getProperty("user.home") + "/Desktop");

			JFileChooser filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(desktop);
			filechooser.setFileFilter(new SaveFileFilter());
			filechooser.setMultiSelectionEnabled(true);
			filechooser.setDialogTitle("Save the merged PDF");

			if (filechooser.showSaveDialog(getView()) == JFileChooser.APPROVE_OPTION) {
				File file = filechooser.getSelectedFile();

				// String path = file.getAbsolutePath();
				// if (!path.endsWith(".pdf")) {
				// path = path + ".pdf";
				// }

				workingDoc = PDDocument.load(workingfile);
				workingDoc.save(file);
				workingDoc.close();

				getView().getLblStatusbar().setText("Successfully saved " + file.getName());
				getView().repaint();
				getView().revalidate();
			}
		} catch (IOException e) {
			getView().getLblStatusbar().setText("Saving failed: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public void clear() {
		getView().getPnlPreviews().removeAll();
		mapPages.clear();
		workingDoc = null;
		getView().repaint();
		getView().revalidate();
	}

	private class ImportFileFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}
			for (String format : supportedFormats) {
				if (f.getName().endsWith(format)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public String getDescription() {
			return "pdf or image files";
		}
	}

	private class SaveFileFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			return f.getName().endsWith(".pdf") || f.isDirectory();
		}

		@Override
		public String getDescription() {
			return ".pdf";
		}

	}
}
