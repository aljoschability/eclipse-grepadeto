package com.aljoschability.eclipse.grepadeto.diagram.features.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;

public abstract class AbstractCreateNodeFeature extends AbstractCreateFeature {
	public AbstractCreateNodeFeature(IFeatureProvider fp, String name, String description) {
		super(fp, name, description);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return canCreate(getBusinessObjectForPictogramElement(context.getTargetContainer()));
	}

	protected abstract boolean canCreate(Object context);

	protected abstract EObject create(Object context);

	@Override
	public Object[] create(ICreateContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getTargetContainer());
		EObject element = create(object);
		addGraphicalRepresentation(context, element);
		return new Object[] { element };
	}
}
