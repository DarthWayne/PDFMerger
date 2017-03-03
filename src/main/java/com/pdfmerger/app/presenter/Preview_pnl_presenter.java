package com.pdfmerger.app.presenter;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;

import com.components.AbstractPresenter;
import com.pdfmerger.app.ui.Preview_pnl;

public class Preview_pnl_presenter extends AbstractPresenter {

	public static final String ActionDelete = "Delete";
	public static final String ActionDuplicate = "Duplicate";
	public static final String MoveToFront = "Front";
	public static final String MoveToBack = "Back";

	private Map<Integer, ImageIcon> mapThumbnails = new HashMap<Integer, ImageIcon>();
	private Image myImage;

	public Preview_pnl_presenter(Preview_pnl objViewContainer) {
		super(objViewContainer);
	}

	@Override
	public Preview_pnl getView() {
		return (Preview_pnl) getObjViewContainer();
	}

	@Override
	public void initialize() {
		super.initialize();
//		File tempfolder = Files.crea
	}
	
	@Override
	public void registerActions() {
		getView().getBtnDelete().addActionListener(e -> firePropertyChange(ActionDelete, false, true));
		getView().getMniDelete().addActionListener(e -> firePropertyChange(ActionDelete, false, true));
		getView().getMniDuplicate().addActionListener(e -> firePropertyChange(ActionDuplicate, false, true));
		getView().getMniMoveToFront().addActionListener(e -> firePropertyChange(MoveToFront, false, true));
		getView().getMniMoveToBack().addActionListener(e -> firePropertyChange(MoveToBack, false, true));
	}

	public void setImage(Image image) {
		int h = 300;
		int w = (int) (h / Math.sqrt(2));
		myImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ExecutorService exec = Executors.newSingleThreadExecutor();
		exec.execute(() -> createThumbnails());
		exec.shutdown();
	}

	private void createThumbnails() {
		Image image = myImage;

		double sqrt2 = Math.sqrt(2);

		for (int h = 300; h > 100; h -= 100) {
			int w = (int) (h / sqrt2);
			image = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			mapThumbnails.put(h, new ImageIcon(image));
		}
	}

	public void scaleImage(int h) {
		if (mapThumbnails.containsKey(h)) {
			getView().getLblThumbnail().setIcon(mapThumbnails.get(h));
		} else {
			// should not be called if create thumbnails didn't fuck up
			int w = (int) (h / Math.sqrt(2));
			ImageIcon image = new ImageIcon(myImage.getScaledInstance(w, h, Image.SCALE_FAST));
			mapThumbnails.put(h, image);
			getView().getLblThumbnail().setIcon(image);
		}
		int dw = getView().getLblThumbnail().getIcon().getIconWidth() + 10;
		int dh = getView().getLblThumbnail().getIcon().getIconHeight() + 5;
		getView().setPreferredSize(new Dimension(dw, dh));
	}

	private class MouseInput extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("klick");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// getView().getPnlOverlay().setVisible(false);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			getView().getPnlOverlay().setVisible(true);
		}
	}
}
