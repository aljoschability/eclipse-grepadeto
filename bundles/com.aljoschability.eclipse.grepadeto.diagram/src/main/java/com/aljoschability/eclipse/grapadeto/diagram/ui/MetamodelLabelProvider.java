package com.aljoschability.eclipse.grapadeto.diagram.ui;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.aljoschability.eclipse.grapadeto.model.Metamodel;

public class MetamodelLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int index) {
		if (index == 0) {
			return null;
		}

		return null;
	}

	@Override
	public String getColumnText(Object element, int index) {
		if (index == 0) {
			return ((Metamodel) element).getName();
		}

		return String.valueOf(element);
	}
}
