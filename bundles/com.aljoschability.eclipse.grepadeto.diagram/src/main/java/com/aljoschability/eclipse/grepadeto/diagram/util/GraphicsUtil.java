package com.aljoschability.eclipse.grepadeto.diagram.util;

import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.util.IColorConstant;

public final class GraphicsUtil {

	private static final String TYPE = "type"; //$NON-NLS-1$
	private static final String VAL_NAVIGABLE = "connection.decorator.navigable"; //$NON-NLS-1$
	private static final String VAL_DIRECTION = "connection.decorator.direction"; //$NON-NLS-1$
	private static final String VAL_TEXT_LINE = "text.line"; //$NON-NLS-1$

	// services
	private static final IPeCreateService PECS = Graphiti.getPeCreateService();
	private static final IPeService PES = Graphiti.getPeService();
	private static final IGaService GAS = Graphiti.getGaService();

	public static Polyline createNavigableArrow(Connection c) {
		// create decorator
		ConnectionDecorator cd = PECS.createConnectionDecorator(c, false, 1.0,
				true);

		// save type of decorator
		PES.setPropertyValue(cd, TYPE, VAL_NAVIGABLE);

		// create arrow
		int[] coords = new int[] { -10, -5, 0, 0, -10, 5 };
		Polyline arrow = GAS.createPolyline(cd, coords);
		arrow.setForeground(GAS.manageColor(c.getParent(), IColorConstant.BLACK));

		return arrow;
	}

	public static void deleteNavigableArrow(Connection connection) {
		for (ConnectionDecorator cd : connection.getConnectionDecorators()) {
			String value = PES.getPropertyValue(cd, TYPE);
			if (VAL_NAVIGABLE.equals(value)) {
				connection.getConnectionDecorators().remove(cd);
			}
		}
	}

	public static Polygon createDirectionArrow(Connection c) {
		// create decorator
		ConnectionDecorator cd = PECS.createConnectionDecorator(c, false, 0.5,
				true);

		// save type of decorator
		PES.setPropertyValue(cd, TYPE, VAL_DIRECTION);

		// create arrow
		int[] coords = new int[] { 5, -6, -5, -10, 5, -14 };
		Polygon arrow = Graphiti.getGaService().createPolygon(cd, coords);
		arrow.setBackground(GAS.manageColor(c.getParent(), IColorConstant.BLACK));
		arrow.setLineWidth(0);

		return arrow;
	}

	public static String getTitle(Object element) {
		// TODO: original there was an pattern object

		return element.toString();
	}

	public static boolean isUnderline(GraphicsAlgorithm ga) {
		if (ga != null && ga instanceof Polyline) {
			return VAL_TEXT_LINE.equals(PES.getPropertyValue(ga, TYPE));
		}

		return false;
	}

	public static Polyline createUnderline(Diagram diagram,
			ContainerShape container, int[] bounds) {
		// create shape for the line
		Shape shape = PECS.createShape(container, false);

		// create line
		Polyline line = GAS.createPolyline(shape, bounds);

		// set foreground
		line.setForeground(GAS.manageColor(diagram, IColorConstant.BLACK));

		// set underline property
		PES.setPropertyValue(line, TYPE, VAL_TEXT_LINE);

		return line;
	}
}
