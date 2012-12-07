package com.aljoschability.eclipse.grapadeto.diagram.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

public abstract class AbstractAddConnectionFeature extends AbstractAddFeature {
	protected static final IPeCreateService PEC = Graphiti.getPeCreateService();
	protected static final IGaService GA = Graphiti.getGaService();

	public AbstractAddConnectionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public final boolean canAdd(IAddContext context) {
		return context instanceof IAddConnectionContext && canAdd((IAddConnectionContext) context);
	}

	@Override
	public final PictogramElement add(IAddContext context) {
		return add((IAddConnectionContext) context);
	}

	protected abstract PictogramElement add(IAddConnectionContext context);

	protected abstract boolean canAdd(IAddConnectionContext context);
}
