package com.aljoschability.eclipse.grapadeto.diagram.ui;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class ReferencesContentProvider extends ArrayContentProvider implements ITreeContentProvider {
	@Override
	public Object[] getElements(Object element) {
		Collection<EPackage> children = new HashSet<EPackage>();

		for (Object object : (Collection<?>) element) {
			if (((EPackage) object).getESuperPackage() == null) {
				children.add((EPackage) object);
			}
		}

		return children.toArray(new Object[children.size()]);
	}

	@Override
	public Object[] getChildren(Object element) {
		if (element instanceof EPackage) {
			Collection<?> children = ((EPackage) element).getESubpackages();
			return children.toArray(new Object[children.size()]);
		}

		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof EPackage) {
			return ((EPackage) element).getESuperPackage();
		}

		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}
}
