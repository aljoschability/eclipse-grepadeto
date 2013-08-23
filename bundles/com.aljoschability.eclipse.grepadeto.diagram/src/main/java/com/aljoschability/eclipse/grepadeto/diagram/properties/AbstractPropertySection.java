package com.aljoschability.eclipse.grepadeto.diagram.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

public abstract class AbstractPropertySection extends GFPropertySection
		implements ITabbedPropertyConstants {

	protected EObject getElement() {
		// get element
		EObject model = Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(
						getSelectedPictogramElement());

		// check type
		if (model instanceof EObject) {
			return (EObject) model;
		}

		return null;
	}

	protected final void execute(PropertyFeature feature) {
		execute(feature, new CustomContext());
	}

	protected abstract class PropertyFeature extends AbstractFeature {

		public PropertyFeature() {
			super(getDiagramTypeProvider().getFeatureProvider());
		}

		@Override
		public void execute(IContext context) {
			execute();
		}

		public abstract void execute();

		@Override
		public boolean canExecute(IContext context) {
			return true;
		}
	}
}
