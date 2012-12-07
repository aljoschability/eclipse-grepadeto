package com.aljoschability.eclipse.grapadeto.ui;

import org.eclipse.jface.resource.ImageDescriptor;

public final class UIImages {
	public static final String BANNER_CREATE_PATTERNS = "icons/wizards/banner_create_patterns_catalog.png"; //$NON-NLS-1$

	public static ImageDescriptor getImageDescriptor(String key) {
		return Activator.getInstance().getImageDescriptor(key);
	}

	private UIImages() {
		// hide constructor
	}
}
