package com.aljoschability.eclipse.grepadeto.diagram.editor.provider;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;

import com.aljoschability.eclipse.grepadeto.Modifier;
import com.aljoschability.eclipse.grepadeto.diagram.features.create.CreateAttributeConstraintFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.create.CreateFragmentFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.create.CreateLinkFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.create.CreatePathFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.create.CreatePatternAnnotationFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.create.CreatePatternObjectFeature;

public class ToolBehaviorProvider extends DefaultToolBehaviorProvider {

	private IPaletteCompartmentEntry[] palette;

	public ToolBehaviorProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	private static ObjectCreationToolEntry createToolEntry(ICreateFeature feature) {
		// get data
		String label = feature.getCreateName();
		String description = feature.getCreateDescription();
		String iconId = feature.getCreateImageId();
		String largeIconId = feature.getCreateLargeImageId();

		return new ObjectCreationToolEntry(label, description, iconId, largeIconId, feature);
	}

	private static ConnectionCreationToolEntry createConnectionToolEntry(ICreateConnectionFeature feature) {
		// get data
		String label = feature.getCreateName();
		String description = feature.getCreateDescription();
		String iconId = feature.getCreateImageId();
		String largeIconId = feature.getCreateLargeImageId();

		ConnectionCreationToolEntry tool = new ConnectionCreationToolEntry(label, description, iconId, largeIconId);
		tool.addCreateConnectionFeature((ICreateConnectionFeature) feature);

		return tool;
	}

	@Override
	public IPaletteCompartmentEntry[] getPalette() {
		if (palette == null) {
			FeatureProvider fp = getFeatureProvider();
			PaletteCompartmentEntry nodes = new PaletteCompartmentEntry("Nodes", ImageProvider.PALETTE_PATTERNS);
			nodes.addToolEntry(createToolEntry(new CreatePatternObjectFeature(fp)));
			nodes.addToolEntry(createToolEntry(new CreatePatternAnnotationFeature(fp)));

			PaletteCompartmentEntry connections = new PaletteCompartmentEntry("Connections",
					ImageProvider.PALETTE_PATTERNS);
			nodes.addToolEntry(createConnectionToolEntry(new CreateLinkFeature(fp)));
			nodes.addToolEntry(createConnectionToolEntry(new CreatePathFeature(fp)));

			PaletteCompartmentEntry fragments = new PaletteCompartmentEntry("Fragments", ImageProvider.PALETTE_PATTERNS);
			fragments.addToolEntry(createToolEntry(new CreateFragmentFeature(fp, Modifier.ADDITIONAL)));
			fragments.addToolEntry(createToolEntry(new CreateFragmentFeature(fp, Modifier.NEGATIVE)));
			fragments.addToolEntry(createToolEntry(new CreateFragmentFeature(fp, Modifier.SET)));

			PaletteCompartmentEntry constraints = new PaletteCompartmentEntry("Constraints",
					ImageProvider.PALETTE_PATTERNS);
			constraints.addToolEntry(createToolEntry(new CreateAttributeConstraintFeature(fp)));

			palette = new IPaletteCompartmentEntry[] { nodes, connections, fragments, constraints };
		}

		return palette;
	}

	@Override
	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		// get services
		// IPeService pes = Graphiti.getPeService();

		IContextButtonPadData data = super.getContextButtonPad(context);
		PictogramElement pe = context.getPictogramElement();

		// 1. set the generic context buttons
		// note, that we do not add 'remove' (just as an example)
		setGenericContextButtons(data, pe, CONTEXT_BUTTON_DELETE | CONTEXT_BUTTON_UPDATE | CONTEXT_BUTTON_REMOVE);

		// 2. set the collapse button
		// simply use a dummy custom feature (senseless example)
		CustomContext cc = new CustomContext(new PictogramElement[] { pe });
		ICustomFeature[] cf = getFeatureProvider().getCustomFeatures(cc);
		for (int i = 0; i < cf.length; i++) {
			ICustomFeature iCustomFeature = cf[i];
			System.out.println(iCustomFeature);
			// if (iCustomFeature instanceof CollapseNodeFeature) {
			// IContextButtonEntry collapseButton =
			// ContextEntryHelper.createCollapseContextButton(true,
			// iCustomFeature, cc);
			// data.setCollapseContextButton(collapseButton);
			// break;
			// }
		}

		// add attribute constraint button to pattern object
		if (context.getPictogramElement() instanceof ContainerShape) {
			// CreateContext cContext = new CreateContext();
			// cContext.setTargetContainer((ContainerShape)
			// context.getPictogramElement());
			// ContextButtonEntry entry = new ContextButtonEntry(
			// new CreateAttributeConstraintFeature(getFeatureProvider()),
			// cContext);
			// // entry.setIconId(Constants.IMG_FRAG_ADD);
			// entry.setText("Create Attribute Constraint");
			// entry.setDescription("Create a new attribute constraint");
			//
			// data.getDomainSpecificContextButtons().add(entry);
		}

		// 3. add one domain specific context-button, which offers all
		// available connection-features as drag&drop features...

		// 3.a. create new CreateConnectionContext
		CreateConnectionContext ccc = new CreateConnectionContext();
		ccc.setSourcePictogramElement(pe);
		Anchor anchor = null;
		if (pe instanceof Anchor) {
			anchor = (Anchor) pe;
		} else if (pe instanceof AnchorContainer) {
			for (Anchor a : ((AnchorContainer) pe).getAnchors()) {
				if (a instanceof ChopboxAnchor) {
					// TODO: assume, that our shapes always have chopbox anchors
					anchor = a;
				}
			}
		}
		ccc.setSourceAnchor(anchor);

		// add context button to create new connections
		ContextButtonEntry dragger = new ContextButtonEntry(null, context);
		dragger.setText("Create connection");
		dragger.setDescription("Create a new connection from this node by dragging.");
		// dragger.setIconId(SDMConstants.IMG_CONNS);

		// collect connection create features
		for (ICreateConnectionFeature feature : getFeatureProvider().getCreateConnectionFeatures()) {
			if (feature.isAvailable(ccc) && feature.canStartConnection(ccc)) {
				dragger.addDragAndDropFeature(feature);
			}
		}

		// add the connection dragger
		if (!dragger.getDragAndDropFeatures().isEmpty()) {
			data.getDomainSpecificContextButtons().add(dragger);
		}

		return data;
	}

	@Override
	protected FeatureProvider getFeatureProvider() {
		return (FeatureProvider) super.getFeatureProvider();
	}
}
