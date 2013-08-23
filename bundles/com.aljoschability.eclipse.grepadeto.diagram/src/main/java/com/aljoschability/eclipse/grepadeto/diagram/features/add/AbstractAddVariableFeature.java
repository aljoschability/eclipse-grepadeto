package com.aljoschability.eclipse.grepadeto.diagram.features.add;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.ui.services.IUiLayoutService;
import org.eclipse.graphiti.util.IColorConstant;

public abstract class AbstractAddVariableFeature extends AbstractAddFeature {
	protected static final IPeCreateService PEC = Graphiti.getPeCreateService();
	protected static final IGaService GA = Graphiti.getGaService();
	protected static final IUiLayoutService UIL = GraphitiUi.getUiLayoutService();

	protected static final int MINIMUM_WIDTH = 10;
	protected static final int MINIMUM_HEIGHT = 10;
	protected static final int PADDING = 4;

	protected final Font font = getTextFont();

	public AbstractAddVariableFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public PictogramElement add(IAddContext context) {
		// create the shape
		ContainerShape shape = PEC.createContainerShape(context.getTargetContainer(), true);
		PEC.createChopboxAnchor(shape);

		// get data
		String headerText = getHeaderText(context.getNewObject());
		IDimension headerDimension = UIL.calculateTextSize(headerText, font);

		// link model--shape
		link(shape, context.getNewObject());

		// coordinates
		int width = Math.max(MINIMUM_WIDTH, Math.max(context.getWidth(), headerDimension.getWidth() + 2 * PADDING));
		int height = Math.max(MINIMUM_HEIGHT, Math.max(context.getHeight(), headerDimension.getHeight() + 2 * PADDING));
		int x = context.getWidth() != -1 ? context.getX() : context.getX() - width / 2;
		int y = context.getHeight() != -1 ? context.getY() : context.getY() - height / 2;

		int sepY = -1 + headerDimension.getHeight() + 2 * PADDING;

		// create the main frame
		GraphicsAlgorithm frame = createFrame(shape);
		GA.setLocationAndSize(frame, x, y, width, height);

		Text text = createHeaderText(frame, headerText);
		GA.setLocationAndSize(text, 0, 0, width, sepY);

		// add header separator
		{
			int[] points = new int[] { 0, sepY, width, sepY };
			GA.createPlainPolyline(frame, points);
		}

		// add header text line
		{
			int lineXstart = (width - headerDimension.getWidth()) / 2 - 2;
			int lineXend = lineXstart + headerDimension.getWidth() + 2;
			int lineY = sepY - PADDING - 1;

			int[] points = new int[] { lineXstart, lineY, lineXend, lineY };
			GA.createPlainPolyline(frame, points);
		}

		layoutPictogramElement(shape);

		return shape;
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return canAdd(context.getNewObject());
	}

	protected abstract boolean canAdd(Object object);

	protected GraphicsAlgorithm createFrame(GraphicsAlgorithmContainer container) {
		Rectangle frame = Graphiti.getGaService().createPlainRectangle(container);

		frame.setBackground(manageColor(IColorConstant.WHITE));
		frame.setForeground(manageColor(IColorConstant.BLACK));
		frame.setLineWidth(1);
		frame.setFilled(true);

		return frame;
	}

	protected Text createHeaderText(GraphicsAlgorithm container, String text) {
		Text header = Graphiti.getGaService().createText(container, text);

		header.setForeground(manageColor(IColorConstant.BLACK));
		header.setFont(font);
		header.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		header.setVerticalAlignment(Orientation.ALIGNMENT_MIDDLE);

		return header;
	}

	protected abstract String getHeaderText(Object element);

	protected Font getTextFont() {
		String name = "Calibri";// ga.manageDefaultFont(getDiagram()).getName();
		Font font = GA.manageFont(getDiagram(), name, 10, false, true);

		return font;
	}
}
