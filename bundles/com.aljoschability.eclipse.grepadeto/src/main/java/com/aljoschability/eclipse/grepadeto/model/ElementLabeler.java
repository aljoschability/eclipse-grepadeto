package com.aljoschability.eclipse.grepadeto.model;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

import com.aljoschability.core.ui.runtime.IActivator;
import com.aljoschability.eclipse.grapadeto.Activator;
import com.aljoschability.eclipse.grepadeto.GrapadetoImages;

public abstract class ElementLabeler {

	public ElementLabeler() {
		super();
	}

	protected Image getImage(ElementType element) {
		return getImage(element, VisibilityType.NONE);
	}

	protected Image getImage(ElementType element, VisibilityType visibilty) {
		return getImage(element, visibilty, ModifierType.NONE);
	}

	protected Image getImage(ElementType element, VisibilityType visibilty, Collection<ModifierType> modifiers) {

		IActivator p = Activator.get();

		switch (element) {
		case PROJECT:
			return p.getImage(GrapadetoImages.PROJECT);

		case DIRECTORY:
			return p.getImage(GrapadetoImages.DIRECTORY);

		case FILE:
			return p.getImage(GrapadetoImages.FILE);

		case PACKAGE:
			return p.getImage(GrapadetoImages.PACKAGE);

		case CLASS:
			switch (visibilty) {
			case PUBLIC:
				return p.getImage(GrapadetoImages.CLASS_PUBLIC);

			case PROTECTED:
				return p.getImage(GrapadetoImages.CLASS_PROTECTED);

			case PRIVATE:
				return p.getImage(GrapadetoImages.CLASS_PRIVATE);

			default:
				return p.getImage(GrapadetoImages.CLASS_NONE);
			}

		case INTERFACE:
			switch (visibilty) {
			case PUBLIC:
				return p.getImage(GrapadetoImages.INTERFACE_PUBLIC);

			case PROTECTED:
				return p.getImage(GrapadetoImages.INTERFACE_PROTECTED);

			case PRIVATE:
				return p.getImage(GrapadetoImages.INTERFACE_PRIVATE);

			default:
				return p.getImage(GrapadetoImages.INTERFACE_NONE);
			}

		case ATTRIBUTE:
			switch (visibilty) {
			case PUBLIC:
				return p.getImage(GrapadetoImages.ATTRIBUTE_PUBLIC);

			case PROTECTED:
				return p.getImage(GrapadetoImages.ATTRIBUTE_PROTECTED);

			case PRIVATE:
				return p.getImage(GrapadetoImages.ATTRIBUTE_PRIVATE);

			default:
				return p.getImage(GrapadetoImages.ATTRIBUTE_NONE);
			}

		case OPERATION:
			switch (visibilty) {
			case PUBLIC:
				return p.getImage(GrapadetoImages.OPERATION_PUBLIC);

			case PROTECTED:
				return p.getImage(GrapadetoImages.OPERATION_PROTECTED);

			case PRIVATE:
				return p.getImage(GrapadetoImages.OPERATION_PRIVATE);

			default:
				return p.getImage(GrapadetoImages.OPERATION_NONE);
			}

		case VARIABLE:
			return p.getImage(GrapadetoImages.VARIABLE);

		case UNKNOWN:
			return p.getImage(GrapadetoImages.UNKNOWN);

		default:
			return null;
		}
	}

	protected Image getImage(ElementType element, VisibilityType visibilty, ModifierType modifier) {
		return getImage(element, visibilty, Collections.singleton(modifier));
	}

	public Image getImage(EObject object) {
		return null;
	}

	public abstract String getLabel(EObject object);

	public String getQualifiedLabel(EObject object) {
		return getLabel(object);
	}
}
