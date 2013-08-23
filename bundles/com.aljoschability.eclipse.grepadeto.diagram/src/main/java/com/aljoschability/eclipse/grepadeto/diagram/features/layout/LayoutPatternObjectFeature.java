package com.aljoschability.eclipse.grepadeto.diagram.features.layout;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

import com.aljoschability.eclipse.grepadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grepadeto.diagram.Constants;

public class LayoutPatternObjectFeature extends AbstractLayoutFeature {
	public LayoutPatternObjectFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		return getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof ObjectOccurrence;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean anythingChanged = false;

		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();

		// height
		if (containerGa.getHeight() < Constants.MINIMUM_HEIGHT__STATEMENT_NODE) {
			containerGa.setHeight(Constants.MINIMUM_HEIGHT__STATEMENT_NODE);
			anythingChanged = true;
		}

		// width
		if (containerGa.getWidth() < Constants.MINIMUM_WIDTH__STATEMENT_NODE) {
			containerGa.setWidth(Constants.MINIMUM_WIDTH__STATEMENT_NODE);
			anythingChanged = true;
		}

		int containerWidth = containerGa.getWidth();
		IGaService gaService = Graphiti.getGaService();

		for (GraphicsAlgorithm ga : containerShape.getGraphicsAlgorithm().getGraphicsAlgorithmChildren()) {
			IDimension size = gaService.calculateSize(ga);
			if (containerWidth != size.getWidth()) {
				if (ga instanceof Polyline) {
					Polyline polyline = (Polyline) ga;
					Point secondPoint = polyline.getPoints().get(1);
					Point newSecondPoint = gaService.createPoint(containerWidth, secondPoint.getY());
					polyline.getPoints().set(1, newSecondPoint);
					anythingChanged = true;
				} else {
					gaService.setWidth(ga, containerWidth);
					anythingChanged = true;
				}
			}
		}

		return anythingChanged;
	}
}
