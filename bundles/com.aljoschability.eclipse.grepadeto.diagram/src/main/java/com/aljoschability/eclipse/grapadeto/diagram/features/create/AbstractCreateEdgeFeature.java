package com.aljoschability.eclipse.grapadeto.diagram.features.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;

public abstract class AbstractCreateEdgeFeature extends AbstractCreateConnectionFeature {

	public AbstractCreateEdgeFeature(IFeatureProvider fp, String name, String description) {
		super(fp, name, description);
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		if (context.getSourceAnchor() != null && context.getTargetAnchor() != null) {
			Object source = getBusinessObjectForPictogramElement(context.getSourceAnchor().getParent());
			Object target = getBusinessObjectForPictogramElement(context.getTargetAnchor().getParent());

			return canCreate(source, target);
		}

		return false;
	}

	protected abstract boolean canStartConnection(Object source);

	protected abstract boolean canCreate(Object source, Object target);

	protected abstract EObject create(Object source, Object target);

	@Override
	public Connection create(ICreateConnectionContext context) {
		Object source = getBusinessObjectForPictogramElement(context.getSourceAnchor().getParent());
		Object target = getBusinessObjectForPictogramElement(context.getTargetAnchor().getParent());

		EObject element = create(source, target);

		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(element);

		return (Connection) getFeatureProvider().addIfPossible(addContext);
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		if (context.getSourceAnchor() != null) {
			Object source = getBusinessObjectForPictogramElement(context.getSourceAnchor().getParent());

			return canStartConnection(source);
		}

		return false;
	}
}
