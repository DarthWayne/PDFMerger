package com.pdfmerger.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

import com.pdfmerger.app.presenter.Merger_frm_presenter;
import com.pdfmerger.app.ui.Merger_frm;

public class Merger_app {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			System.setProperty("sun.awt.noerasebackground", "true");

			Merger_frm_presenter pre = new Merger_frm_presenter(new Merger_frm());
			pre.initialize();
			pre.setVisible(true);

			// import parameters
			List<File> files = new ArrayList<File>();
			for (String param : args) {
				File file = new File(param);
				if (file.exists() && !file.isDirectory()) {
					files.add(file);
				}
			}
			if (!files.isEmpty()) {
				pre.importFiles((File[]) files.toArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
