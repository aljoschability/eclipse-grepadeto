package com.aljoschability.eclipse.grapadeto.diagram.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.util.IColorConstant;

import com.aljoschability.eclipse.grapadeto.Path;

public class AddPathFeature extends AbstractAddConnectionFeature {
	public AddPathFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddConnectionContext context) {
		return context.getNewObject() instanceof Path;
	}

	@Override
	protected PictogramElement add(IAddConnectionContext context) {
		// get element
		Path link = (Path) context.getNewObject();

		// create connection
		Connection connection = PEC.createFreeFormConnection(getDiagram());
		connection.setStart(context.getSourceAnchor());
		connection.setEnd(context.getTargetAnchor());

		// link model and graphic
		link(connection, link);

		// create line
		Polyline line = GA.createPlainPolyline(connection);
		line.setTransparency(1d);
		line.setLineWidth(1);

		// create arrow to target
		ConnectionDecorator decorator = PEC.createConnectionDecorator(connection, false, 1d, true);
		int[] points = new int[] { -10, 8, 2, 0, -10, -8 };
		Polyline arrow = GA.createPlainPolyline(decorator, points);
		arrow.setForeground(manageColor(IColorConstant.BLACK));
		arrow.setLineWidth(1);

		// add first line as decorator
		ConnectionDecorator lineOneDec = PEC.createConnectionDecorator(connection, false, 0.5d, true);
		points = new int[] { -50, -1, 50, -1 };
		GA.createPolyline(lineOneDec, points);

		ConnectionDecorator lineTwoDec = PEC.createConnectionDecorator(connection, false, 0.5d, true);
		points = new int[] { -50, 1, 50, 1 };
		GA.createPolyline(lineTwoDec, points);

		return connection;
	}
}
