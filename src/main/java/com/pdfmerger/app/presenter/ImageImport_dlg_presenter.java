package com.pdfmerger.app.presenter;

import com.components.AbstractPresenter;
import com.pdfmerger.app.ui.ImageImport_dlg;

public class ImageImport_dlg_presenter extends AbstractPresenter {

	public ImageImport_dlg_presenter(ImageImport_dlg objViewContainer) {
		super(objViewContainer);
	}

	@Override
	public ImageImport_dlg getView() {
		return (ImageImport_dlg) getObjViewContainer();
	}

	@Override
	public void registerActions() {

	}

}
