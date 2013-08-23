package com.aljoschability.eclipse.grepadeto.diagram.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grepadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grepadeto.diagram.util.TextUtil;

public class AddPatternAnnotationFeature extends AbstractAddVariableFeature {
	public AddPatternAnnotationFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected boolean canAdd(Object object) {
		return object instanceof ObjectOccurrence;
	}

	@Override
	protected String getHeaderText(Object element) {
		return TextUtil.getText((ObjectOccurrence) element);
	}
}
