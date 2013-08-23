package com.aljoschability.eclipse.grepadeto.diagram.features.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grepadeto.AttributeConstraint;
import com.aljoschability.eclipse.grepadeto.GrepadetoFactory;
import com.aljoschability.eclipse.grepadeto.GrepadetoPackage;
import com.aljoschability.eclipse.grepadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grepadeto.diagram.editor.provider.ImageProvider;

public class CreateAttributeConstraintFeature extends AbstractCreateNodeFeature {
	private static final String NAME = "Attribute Constraint";
	private static final String DESCRIPTION = EcoreUtil
			.getDocumentation(GrepadetoPackage.Literals.ATTRIBUTE_CONSTRAINT);

	public CreateAttributeConstraintFeature(IFeatureProvider fp) {
		super(fp, NAME, DESCRIPTION);
	}

	@Override
	protected boolean canCreate(Object context) {
		return context instanceof ObjectOccurrence;
	}

	@Override
	protected EObject create(Object context) {
		AttributeConstraint element = GrepadetoFactory.eINSTANCE.createAttributeConstraint();

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
