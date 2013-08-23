package com.aljoschability.eclipse.grepadeto.diagram.ui;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.jface.viewers.ViewerComparator;

public final class ReferencesViewerComparator extends ViewerComparator {
	public static final int CAT_REGISTRY = 10;
	public static final int CAT_PLUGIN = 20;
	public static final int CAT_RESOURCE = 30;
	public static final int CAT_UNKNOWN = 40;
	private Registry reg;

	public ReferencesViewerComparator() {
		reg = EPackage.Registry.INSTANCE;
	}

	@Override
	public int category(Object element) {
		URI uri = ((EPackage) element).eResource().getURI();

		if (uri.isPlatformPlugin()) {
			return CAT_PLUGIN;
		}

		if (uri.isPlatformResource()) {
			return CAT_RESOURCE;
		}

		if (reg.getEPackage(((EPackage) element).getNsURI()) != null) {
			return CAT_REGISTRY;
		}

		return CAT_UNKNOWN;
	}
}
