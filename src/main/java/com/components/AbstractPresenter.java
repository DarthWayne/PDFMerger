package com.components;

import java.awt.Container;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

public abstract class AbstractPresenter extends PropertyChangeSupport {
	
	
	protected static final String RefreshAction = "refresh";
	protected static final String DeleteAction = "delete";
	protected static final String CreateAction = "create";
	protected static final String UpdateAction = "update";
	protected static final String SelectAction = "select";
	protected static final String UnselectAction = "unselect";
	
	private static final Logger logger = Logger.getLogger(AbstractPresenter.class);
	private Container objViewContainer;
	private boolean initialized = false;
	private String appPath;

	public AbstractPresenter(final Container objViewContainer) {
		super(objViewContainer);
		this.objViewContainer = objViewContainer;
	}

	public void initialize() {
		if (!initialized) {
			initialized = true;
			registerActions();
		}
	}

	public Container getObjViewContainer() {
		return this.objViewContainer;
	}

	public void setVisible(final boolean visible) {
		getView().setVisible(visible);
	}

	public void firePropertyChange_Select(final Object value) {
		firePropertyChange(SelectAction, null, value);
	}

	public void firePropertyChange_Unselect(final Object value) {
		firePropertyChange(UnselectAction, null, value);
	}

	public void firePropertyChange_Update(final Object oldvalue, final Object newvalue) {
		firePropertyChange(UpdateAction, oldvalue, newvalue);
	}

	public void firePropertyChange_Refresh(final Object value) {
		firePropertyChange(RefreshAction, null, value);
	}

	public void firePropertyChange_Refresh() {
		firePropertyChange(RefreshAction, 0, 1);
	}

	public String readIniValue(final File file, final String node, final String property) {
		try {
			Preferences prefs = new IniPreferences(new Ini(file));
			return prefs.node(node).get(property, null);
		}
		catch (IOException e) {
			logger.warn("Failed to read ini property " + property, e);
		}
		return null;
	}

	public void saveIniValue(final File file, final String node, final String property,
			final String value) {
		try {
			Ini ini = new Ini(file);
			Preferences prefs = new IniPreferences(ini);
			prefs.node(node).put(property, value);
			ini.store();
		}
		catch (IOException e) {
			logger.warn("Failed to write ini property " + property, e);
		}
	}

	public String getAppPath() {
		if (appPath == null) {
			try {
				appPath = URLDecoder.decode(
						ClassLoader.getSystemClassLoader().getResource(".").getPath(), "UFT-8");
				appPath = appPath.substring(1);
			}
			catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return appPath;
	}

	public abstract Container getView();

	public abstract void registerActions();
}
