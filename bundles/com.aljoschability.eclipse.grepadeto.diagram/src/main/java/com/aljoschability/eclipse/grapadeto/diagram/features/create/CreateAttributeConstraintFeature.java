package com.aljoschability.eclipse.grapadeto.diagram.features.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grapadeto.AttributeConstraint;
import com.aljoschability.eclipse.grapadeto.GrapadetoFactory;
import com.aljoschability.eclipse.grapadeto.GrapadetoPackage;
import com.aljoschability.eclipse.grapadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grapadeto.diagram.editor.provider.ImageProvider;

public class CreateAttributeConstraintFeature extends AbstractCreateNodeFeature {
	private static final String NAME = "Attribute Constraint";
	private static final String DESCRIPTION = EcoreUtil
			.getDocumentation(GrapadetoPackage.Literals.ATTRIBUTE_CONSTRAINT);

	public CreateAttributeConstraintFeature(IFeatureProvider fp) {
		super(fp, NAME, DESCRIPTION);
	}

	@Override
	protected boolean canCreate(Object context) {
		return context instanceof ObjectOccurrence;
	}

	@Override
	protected EObject create(Object context) {
		AttributeConstraint element = GrapadetoFactory.eINSTANCE.createAttributeConstraint();

		// add to parent
		// element.setConstraining((PatternObject) context);
		// TODO: fuck meta model!

		element.setAttribute(EcorePackage.Literals.ECLASS__ABSTRACT);

		return element;
	}

	@Override
	public String getCreateImageId() {
		return ImageProvider.ATTRIBUTE_CONSTRAINT;
	}
}
