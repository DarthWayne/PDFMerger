package com.pdfmerger.app.fillstrategy;

import java.awt.Point;

public enum FillStrategy {

	Original("Original"), Stretch("Stretch"), ScaleToFit("Scale to fit"), Matrix("Manual");

	private final String rendername;

	private FillStrategy(String rendername) {
		this.rendername = rendername;
	}

	public Point scale(Point container, Point image) {

		if (this == Original) {
			return image;
		} else if (this == Stretch) {
			return container;
		} else if (this == ScaleToFit) {
			return scaletofit(container, image);
		}		
		return new Point(0, 0);		
	}

	private static Point scaletofit(Point container, Point image) {
		double docratio = container.getY() / container.getX();
		double imgratio = image.getY() / image.getX();

		boolean docratiobigger = docratio > imgratio;
		double scale = docratiobigger ? container.getX() / image.getX() : container.getY() / image.getY();
		double w = docratiobigger ? container.getX() : image.getX() * scale;
		double h = docratiobigger ? image.getY() * scale : container.getY();

		return new Point((int) w, (int) h);
	}

	@Override
	public String toString() {
		return rendername;
	}

}