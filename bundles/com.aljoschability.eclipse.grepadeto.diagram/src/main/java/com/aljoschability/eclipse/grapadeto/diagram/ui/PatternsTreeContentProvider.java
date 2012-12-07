package com.aljoschability.eclipse.grapadeto.diagram.ui;

import java.util.Collection;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

import com.aljoschability.eclipse.grapadeto.Catalog;
import com.aljoschability.eclipse.grapadeto.Pattern;

public class PatternsTreeContentProvider extends ArrayContentProvider implements ITreeContentProvider {
	@Override
	public Object[] getChildren(Object element) {
		if (element instanceof Catalog) {
			Collection<Pattern> children = ((Catalog) element).getPatterns();
			return children.toArray(new Pattern[children.size()]);
		}

		if (element instanceof Pattern) {
			Collection<?> children = ((Pattern) element).getPatterns();
			return children.toArray(new Object[children.size()]);
		}

		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Pattern) {
			return ((Pattern) element).eContainer();
		}

		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}
}
