package com.aljoschability.eclipse.grepadeto.diagram.properties;

import javax.inject.Named;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILinkService;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import com.aljoschability.eclipse.grepadeto.ObjectOccurrence;

/**
 * This class carries various filters for the property sheet.
 */
public final class Filters {
	public static abstract class AbstractFilter extends AbstractPropertySectionFilter {

		protected abstract boolean accept(EObject element);

		@Override
		protected boolean accept(PictogramElement pe) {
			// get link service
			ILinkService l = Graphiti.getLinkService();

			return accept(l.getBusinessObjectForLinkedPictogramElement(pe));
		}
	}

	public static class ActivityEdgeFilter extends AbstractFilter {
		@Override
		protected boolean accept(EObject element) {
			return element instanceof ObjectOccurrence;
		}
	}

	public static class NamedElementFilter extends AbstractFilter {
		@Override
		protected boolean accept(EObject element) {
			return element instanceof Named;
		}
	}

	private Filters() {
		// hide constructor
	}
}
