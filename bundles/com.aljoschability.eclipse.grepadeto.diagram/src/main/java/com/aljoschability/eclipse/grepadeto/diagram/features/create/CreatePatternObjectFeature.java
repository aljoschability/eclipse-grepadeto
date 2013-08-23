package com.aljoschability.eclipse.grepadeto.diagram.features.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grepadeto.GrepadetoFactory;
import com.aljoschability.eclipse.grepadeto.GrepadetoPackage;
import com.aljoschability.eclipse.grepadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grepadeto.Pattern;
import com.aljoschability.eclipse.grepadeto.diagram.Images;

public class CreatePatternObjectFeature extends AbstractCreateNodeFeature {
	private static String NAME = "Pattern Object";
	private static String DESCRIPTION = EcoreUtil.getDocumentation(GrepadetoPackage.Literals.OBJECT_OCCURRENCE);

	public CreatePatternObjectFeature(IFeatureProvider fp) {
		super(fp, NAME, DESCRIPTION);
	}

	@Override
	protected EObject create(Object context) {
		ObjectOccurrence element = GrepadetoFactory.eINSTANCE.createObjectOccurrence();

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
