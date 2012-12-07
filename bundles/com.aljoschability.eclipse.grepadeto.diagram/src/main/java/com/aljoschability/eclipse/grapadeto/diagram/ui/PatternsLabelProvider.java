package com.aljoschability.eclipse.grapadeto.diagram.ui;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.aljoschability.eclipse.grapadeto.Pattern;
import com.aljoschability.eclipse.grapadeto.diagram.Images;
import com.aljoschability.eclipse.grapadeto.diagram.editor.provider.ImageProvider;

public class PatternsLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof Pattern) {
			return ((Pattern) element).getName();
		}

		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Pattern) {
			return ImageProvider.getImage(Images.PATTERN);
		}

		return super.getImage(element);
	}
}
