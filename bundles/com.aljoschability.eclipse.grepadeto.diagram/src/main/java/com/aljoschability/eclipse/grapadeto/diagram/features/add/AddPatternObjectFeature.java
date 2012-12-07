package com.aljoschability.eclipse.grapadeto.diagram.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grapadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grapadeto.diagram.util.TextUtil;

public class AddPatternObjectFeature extends AbstractAddVariableFeature {
	public AddPatternObjectFeature(IFeatureProvider fp) {
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
