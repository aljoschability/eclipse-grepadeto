package com.aljoschability.eclipse.grapadeto.diagram.features.add;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.util.IColorConstant;

import com.aljoschability.eclipse.grapadeto.Link;

public class AddLinkFeature extends AbstractAddConnectionFeature {
	public AddLinkFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddConnectionContext context) {
		return context.getNewObject() instanceof Link;
	}

	@Override
	protected PictogramElement add(IAddConnectionContext context) {
		// calculate link direction
		int sourceX = context.getSourceAnchor().getParent().getGraphicsAlgorithm().getX();
		int targetX = context.getTargetAnchor().getParent().getGraphicsAlgorithm().getX();
		boolean ltr = sourceX <= targetX;

		// get element
		Link link = (Link) context.getNewObject();

		// create connection
		Connection connection = PEC.createFreeFormConnection(getDiagram());
		connection.setStart(context.getSourceAnchor());
		connection.setEnd(context.getTargetAnchor());

		// link model and graphic
		link(connection, link);

		// create direction arrow
		{
			Collection<Point> points = new ArrayList<Point>();
			if (ltr) {
				points.add(GA.createPoint(7, -2));
				points.add(GA.createPoint(7, -14));
				points.add(GA.createPoint(-7, -8));
			} else {
				points.add(GA.createPoint(7, 2));
				points.add(GA.createPoint(7, 14));
				points.add(GA.createPoint(-7, 8));
			}

			ConnectionDecorator decorator = PEC.createConnectionDecorator(connection, false, 0.5, true);

			Polygon arrow = GA.createPlainPolygon(decorator, points);
			arrow.setBackground(manageColor(IColorConstant.BLACK));
			arrow.setLineVisible(false);
		}

		// create role text
		String role = link.getName();
		if (role != null && !role.isEmpty()) {
			Font font = GA.manageFont(getDiagram(), "Calibri", 10);
			int w = GraphitiUi.getUiLayoutService().calculateTextSize(role, font).getWidth();

			ConnectionDecorator decorator = PEC.createConnectionDecorator(connection, true, 0.5, true);

			Text linkRoleText = GA.createText(decorator, role);
			linkRoleText.setFont(font);
			linkRoleText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			linkRoleText.setY(-18);
			linkRoleText.setX(-w / 2);
		}

		// create line
		Polyline line = GA.createPlainPolyline(connection);
		line.setForeground(manageColor(IColorConstant.BLACK));

		// create arrow to source
		if (link.getType() != null) {
			ConnectionDecorator decorator = PEC.createConnectionDecorator(connection, false, 1d, true);
			int[] points = new int[] { -10, 8, 2, 0, -10, -8 };
			Polyline arrow = GA.createPlainPolyline(decorator, points);
			arrow.setForeground(manageColor(IColorConstant.BLACK));
			arrow.setLineWidth(1);
		}

		return connection;
	}
}
