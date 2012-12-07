package com.aljoschability.eclipse.grapadeto.diagram.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

import com.aljoschability.eclipse.grapadeto.Constraint;
import com.aljoschability.eclipse.grapadeto.diagram.util.TextUtil;

public class AddConstraintFeature extends AbstractAddFeature {

	private static final int MIN_W = 72;
	private static final int MIN_H = 32;

	public AddConstraintFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof Constraint;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		// get services
		IPeCreateService pec = Graphiti.getPeCreateService();
		IGaService ga = Graphiti.getGaService();

		// get elements
		Constraint model = (Constraint) context.getNewObject();
		ContainerShape target = context.getTargetContainer();

		// create node container
		ContainerShape shape = pec.createContainerShape(target, true);

		// add anchor
		pec.createChopboxAnchor(shape);

		// link shape and model
		link(shape, model);

		// create the text
		Text text = ga.createText(shape, TextUtil.getText(model));
		text.setForeground(manageColor(IColorConstant.BLACK));
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);

		// define the bounds for the shape
		int x = context.getX();
		int y = context.getY();
		int w = context.getWidth();
		int h = context.getHeight();

		ga.setLocationAndSize(text, x, y, w < MIN_W ? MIN_W : w, h < MIN_H ? MIN_H : h);

		// layout it
		layoutPictogramElement(shape);

		return shape;
	}
}
