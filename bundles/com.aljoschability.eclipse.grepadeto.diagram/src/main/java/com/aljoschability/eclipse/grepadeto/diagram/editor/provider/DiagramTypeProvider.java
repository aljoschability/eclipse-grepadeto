package com.aljoschability.eclipse.grepadeto.diagram.editor.provider;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

public class DiagramTypeProvider extends AbstractDiagramTypeProvider {

	public static final String ID = "org.storydriven.modeling.diagram.SDMDiagramTypeProvider"; //$NON-NLS-1$

	private IToolBehaviorProvider[] tbps;

	public DiagramTypeProvider() {
		super();
		setFeatureProvider(new FeatureProvider(this));
	}

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (tbps == null) {
			tbps = new IToolBehaviorProvider[] { new ToolBehaviorProvider(this) };
		}

		return tbps;
	}

	@Override
	public void resourcesSaved(Diagram diagram, Resource[] savedResources) {
		// TODO Auto-generated method stub
		super.resourcesSaved(diagram, savedResources);
	}
}
