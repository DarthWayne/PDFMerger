package com.pdfmerger.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

import com.components.Flowlayout4Scrollpane;
import com.components.Fonts;
import com.components.LightScrollPane;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Merger_frm extends JFrame {
	
	private JPanel pnlPreviews;
	private JSlider sliThumbnailSize;
	private JPanel pnlBottom;
	private JLabel lblThumbnailSize;
	private JLabel lblThumbnailSizeValue;
	private LightScrollPane scrollPane;
	private JButton btnImport;
	private JButton btnSaveAs;
	private JMenuBar menuBar;
	private JMenu mnFile;
	public JMenuItem mniSave;
	public JMenuItem mniExit;
	private JMenu mnEdit;
	public JMenuItem mniUndo;
	public JMenuItem mniRedo;
	public JMenuItem mniImport;
	public JMenuItem mniDeleteAll;
	public JMenuItem mniPageNumbers;
	private JToolBar toolBar;
	private JLabel lblStatusbar;
	private JMenuItem mniOpen;

	public Merger_frm() {
		initialize();
	}

	private void initialize() {
		// frame properties
		URL iconUrl = getClass().getResource("/resources/icons/application_icon.png");
		if (iconUrl == null) {
			iconUrl = getClass().getResource("/icons/application_icon.png");
		}
		setIconImage(new ImageIcon(iconUrl).getImage());

		setTitle("PDF Merger");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(781, 471);
		setLocationRelativeTo(null);

		// content
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPnlBottom(), BorderLayout.SOUTH);
		getContentPane().add(getScrollPane(), BorderLayout.CENTER);
		setJMenuBar(getMenuBar_1());
		getContentPane().add(getToolBar(), BorderLayout.NORTH);
	}

	public JPanel getPnlPreviews() {
		if (pnlPreviews == null) {
			pnlPreviews = new JPanel();
			pnlPreviews.setLayout(new Flowlayout4Scrollpane(Flowlayout4Scrollpane.LEFT, 20, 20));
			pnlPreviews.add(new Preview_pnl());
			pnlPreviews.add(new Preview_pnl());
			pnlPreviews.add(new Preview_pnl());
			pnlPreviews.add(new Preview_pnl());
		}
		return pnlPreviews;
	}

	public JSlider getSliThumbnailSize() {
		if (sliThumbnailSize == null) {
			sliThumbnailSize = new JSlider();
			sliThumbnailSize.setForeground(Color.WHITE);
			sliThumbnailSize.setOpaque(false);
			sliThumbnailSize.setMinimum(200);
			sliThumbnailSize.setMaximum(300);
			sliThumbnailSize.setValue(300);
			sliThumbnailSize.setSnapToTicks(true);
			sliThumbnailSize.setPaintTicks(true);
			sliThumbnailSize.setMajorTickSpacing(100);
			sliThumbnailSize.setMinorTickSpacing(100);
		}
		return sliThumbnailSize;
	}

	private JPanel getPnlBottom() {
		if (pnlBottom == null) {
			pnlBottom = new JPanel();
			pnlBottom.setLayout(new FormLayout(
					new ColumnSpec[] { ColumnSpec.decode("20px"), ColumnSpec.decode("default:grow"),
							FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
							ColumnSpec.decode("100dlu"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
							ColumnSpec.decode("20px"), },
					new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, }));
			pnlBottom.add(getLblStatusbar(), "2, 1");
			pnlBottom.add(getLblThumbnailSize(), "4, 1, right, default");
			pnlBottom.add(getSliThumbnailSize(), "6, 1, fill, top");
			pnlBottom.add(getLblThumbnailSizeValue(), "8, 1");
		}
		return pnlBottom;
	}

	private JLabel getLblThumbnailSize() {
		if (lblThumbnailSize == null) {
			lblThumbnailSize = new JLabel("Thumbnail Size");
			lblThumbnailSize.setFont(Fonts.SMALL);
		}
		return lblThumbnailSize;
	}

	public JLabel getLblThumbnailSizeValue() {
		if (lblThumbnailSizeValue == null) {
			lblThumbnailSizeValue = new JLabel("200 px");
			lblThumbnailSizeValue.setFont(Fonts.SMALL);
		}
		return lblThumbnailSizeValue;
	}

	private LightScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new LightScrollPane();
			scrollPane.getScrollPane().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getPnlPreviews());
		}
		return scrollPane;
	}

	public JButton getBtnImport() {
		if (btnImport == null) {
			btnImport = new JButton("Add PDF or Image...");
			btnImport.setOpaque(false);
		}
		return btnImport;
	}

	public JButton getBtnSaveAs() {
		if (btnSaveAs == null) {
			btnSaveAs = new JButton("Save as...");
			btnSaveAs.setOpaque(false);
		}
		return btnSaveAs;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnEdit());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setFont(Fonts.SMALL);
			mnFile.add(getMniSave());
			mnFile.add(getMniOpen());
			mnFile.add(getMniExit());
		}
		return mnFile;
	}

	public JMenuItem getMniSave() {
		if (mniSave == null) {
			mniSave = new JMenuItem("Save as...");
			mniSave.setFont(Fonts.SMALL);
		}
		return mniSave;
	}

	public JMenuItem getMniExit() {
		if (mniExit == null) {
			mniExit = new JMenuItem("Exit");
			mniExit.setFont(Fonts.SMALL);
		}
		return mniExit;
	}

	private JMenu getMnEdit() {
		if (mnEdit == null) {
			mnEdit = new JMenu("Edit");
			mnEdit.setFont(Fonts.SMALL);
			mnEdit.add(getMniUndo());
			mnEdit.add(getMniRedo());
			mnEdit.add(getMniImport());
			mnEdit.add(getMniPageNumbers());
			mnEdit.add(getMniDeleteAll());
		}
		return mnEdit;
	}

	public JMenuItem getMniUndo() {
		if (mniUndo == null) {
			mniUndo = new JMenuItem("Undo");
			mniUndo.setEnabled(false);
			mniUndo.setFont(Fonts.SMALL);
		}
		return mniUndo;
	}

	public JMenuItem getMniRedo() {
		if (mniRedo == null) {
			mniRedo = new JMenuItem("Redo");
			mniRedo.setEnabled(false);
			mniRedo.setFont(Fonts.SMALL);
		}
		return mniRedo;
	}

	public JMenuItem getMniImport() {
		if (mniImport == null) {
			mniImport = new JMenuItem("Import...");
			mniImport.setFont(Fonts.SMALL);
		}
		return mniImport;
	}

	public JMenuItem getMniDeleteAll() {
		if (mniDeleteAll == null) {
			mniDeleteAll = new JMenuItem("Delete all pages");
			mniDeleteAll.setFont(Fonts.SMALL);
		}
		return mniDeleteAll;
	}

	public JMenuItem getMniPageNumbers() {
		if (mniPageNumbers == null) {
			mniPageNumbers = new JMenuItem("Add page numbers...");
			mniPageNumbers.setEnabled(false);
			mniPageNumbers.setFont(Fonts.SMALL);
		}
		return mniPageNumbers;
	}

	private JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.add(getBtnSaveAs());
			toolBar.add(getBtnImport());
		}
		return toolBar;
	}

	public JLabel getLblStatusbar() {
		if (lblStatusbar == null) {
			lblStatusbar = new JLabel();
			lblStatusbar.setFont(Fonts.SMALL);
		}
		return lblStatusbar;
	}

	public JMenuItem getMniOpen() {
		if (mniOpen == null) {
			mniOpen = new JMenuItem("Open...");
			mniOpen.setFont(Fonts.SMALL);
		}
		return mniOpen;
	}
}
