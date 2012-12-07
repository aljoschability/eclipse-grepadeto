package com.aljoschability.eclipse.grapadeto.diagram.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grapadeto.Pattern;

public class AddFragmentFeature extends AbstractAddVariableFeature {
	public AddFragmentFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected boolean canAdd(Object element) {
		return element instanceof Pattern;
	}

	@Override
	protected String getHeaderText(Object element) {
		return "TextUtil.getText((Fragment) element)";
	}
}
