package com.aljoschability.eclipse.grepadeto.diagram.features.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grepadeto.Fragment;
import com.aljoschability.eclipse.grepadeto.GrepadetoFactory;
import com.aljoschability.eclipse.grepadeto.GrepadetoPackage;
import com.aljoschability.eclipse.grepadeto.Modifier;
import com.aljoschability.eclipse.grepadeto.Pattern;
import com.aljoschability.eclipse.grepadeto.diagram.Images;

public class CreateFragmentFeature extends AbstractCreateNodeFeature {
	private static String NAME = "Fragment";
	private static String DESCRIPTION = EcoreUtil.getDocumentation(GrepadetoPackage.Literals.FRAGMENT);

	private final Modifier modifier;

	public CreateFragmentFeature(IFeatureProvider fp, Modifier modifier) {
		super(fp, NAME, DESCRIPTION);

		this.modifier = modifier;
	}

	@Override
	protected EObject create(Object context) {
		Fragment element = GrepadetoFactory.eINSTANCE.createFragment();

		// add to parent
		if (context instanceof Pattern) {
			element.setPattern((Pattern) context);
		}

		element.setModifier(modifier);
		element.setName("fragment" + element.getPattern().getFragments().size());

		return element;
	}

	@Override
	public String getCreateImageId() {
		switch (modifier) {
		case ADDITIONAL:
			return Images.FRAGMENT_ADDITIONAL;
		case NEGATIVE:
			return Images.FRAGMENT_NEGATIVE;
		case SET:
			return Images.FRAGMENT_SET;
		default:
			return null;
		}
	}

	@Override
	protected boolean canCreate(Object context) {
		return context instanceof Pattern;
	}
}
