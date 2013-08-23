package com.aljoschability.eclipse.grepadeto.diagram.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

public class SDMTypeMapper implements ITypeMapper {

	@Override
	public Class<? extends Object> mapType(Object object) {

		Class<? extends Object> type = object.getClass();
		if (object instanceof EditPart) {

			Object model = ((EditPart) object).getModel();

			type = model.getClass();
			if (model instanceof PictogramElement) {
				PictogramElement pe = (PictogramElement) model;

				EObject businessObject = Graphiti.getLinkService()
						.getBusinessObjectForLinkedPictogramElement(pe);
				if (businessObject == null) {
					return pe.getClass();
				} else {
					return businessObject.eClass().getClass();
				}
			}

		}

		return type;
	}
}