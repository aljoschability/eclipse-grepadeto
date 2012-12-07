package com.aljoschability.eclipse.grapadeto.diagram.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grapadeto.ConstraintExpression;
import com.aljoschability.eclipse.grapadeto.Pattern;
import com.aljoschability.eclipse.grapadeto.diagram.util.TextUtil;

public class AddConstraintExpressionFeature extends AbstractAddVariableFeature {
	public AddConstraintExpressionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected boolean canAdd(Object element) {
		return element instanceof Pattern;
	}

	@Override
	protected String getHeaderText(Object element) {
		return TextUtil.getText((ConstraintExpression) element);
	}
}
