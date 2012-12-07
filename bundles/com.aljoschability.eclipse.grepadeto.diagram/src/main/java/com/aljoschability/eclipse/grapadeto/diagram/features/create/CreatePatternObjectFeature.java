package com.aljoschability.eclipse.grapadeto.diagram.features.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grapadeto.GrapadetoFactory;
import com.aljoschability.eclipse.grapadeto.GrapadetoPackage;
import com.aljoschability.eclipse.grapadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grapadeto.Pattern;
import com.aljoschability.eclipse.grapadeto.diagram.Images;

public class CreatePatternObjectFeature extends AbstractCreateNodeFeature {
	private static String NAME = "Pattern Object";
	private static String DESCRIPTION = EcoreUtil.getDocumentation(GrapadetoPackage.Literals.OBJECT_OCCURRENCE);

	public CreatePatternObjectFeature(IFeatureProvider fp) {
		super(fp, NAME, DESCRIPTION);
	}

	@Override
	protected EObject create(Object context) {
		ObjectOccurrence element = GrapadetoFactory.eINSTANCE.createObjectOccurrence();

		// add to parent
		element.setPattern((Pattern) context);

		element.setName("object" + element.getPattern().getObjects().size());

		return element;
	}

	@Override
	public String getCreateImageId() {
		return Images.PATTERN_OBJECT;
	}

	@Override
	protected boolean canCreate(Object context) {
		return context instanceof Pattern;
	}
}
