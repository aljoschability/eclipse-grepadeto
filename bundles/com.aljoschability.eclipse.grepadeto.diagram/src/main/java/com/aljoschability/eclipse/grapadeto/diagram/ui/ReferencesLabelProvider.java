package com.aljoschability.eclipse.grapadeto.diagram.ui;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.aljoschability.eclipse.grapadeto.diagram.editor.provider.ImageProvider;

public class ReferencesLabelProvider extends LabelProvider implements ITableLabelProvider {

	private Registry reg;

	public ReferencesLabelProvider() {
		reg = EPackage.Registry.INSTANCE;
	}

	@Override
	public Image getColumnImage(Object element, int index) {
		if (index == 0) {
			URI uri = ((EPackage) element).eResource().getURI();

			if (uri.isPlatformPlugin()) {
				return ImageProvider.getImage(ImageProvider.EPACKAGE_PLUGIN);
			}

			if (uri.isPlatformResource()) {
				return ImageProvider.getImage(ImageProvider.EPACKAGE_WORKSPACE);
			}

			if (reg.getEPackage(((EPackage) element).getNsURI()) != null) {
				return ImageProvider.getImage(ImageProvider.EPACKAGE);
			}

			System.err.println("houston...");
			return null;
			// return DiagramImages.getImage(DiagramImages.ELEMENT_EPACKAGE);
		}

		return null;
	}

	@Override
	public String getColumnText(Object element, int index) {
		// check index
		switch (index) {
		case 0:
			return ((EPackage) element).getName();

		case 1:
			URI uri = ((EPackage) element).eResource().getURI();
			if (uri.isPlatformPlugin()) {
				return "Plug-In";
			}

			if (uri.isPlatformResource()) {
				return "Resource";
			}

			if (reg.getEPackage(((EPackage) element).getNsURI()) != null) {
				return "Registry";
			}

			return "Unknown";

		case 2:
			return ((EPackage) element).getNsURI();

		default:
			return getText(element);
		}
	}
}
