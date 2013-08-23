package com.aljoschability.eclipse.grepadeto.diagram.features.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grepadeto.GrepadetoFactory;
import com.aljoschability.eclipse.grepadeto.GrepadetoPackage;
import com.aljoschability.eclipse.grepadeto.Pattern;
import com.aljoschability.eclipse.grepadeto.PatternOccurrence;
import com.aljoschability.eclipse.grepadeto.diagram.Images;

public class CreatePatternAnnotationFeature extends AbstractCreateNodeFeature {
	private static String NAME = "Pattern Annotation";
	private static String DESCRIPTION = EcoreUtil.getDocumentation(GrepadetoPackage.Literals.PATTERN_OCCURRENCE);

	public CreatePatternAnnotationFeature(IFeatureProvider fp) {
		super(fp, NAME, DESCRIPTION);
	}

	@Override
	protected EObject create(Object context) {
		PatternOccurrence element = GrepadetoFactory.eINSTANCE.createPatternOccurrence();

		// add to parent
		element.setPattern((Pattern) context);

		element.setName("annotation" + element.getPattern().getPatterns().size());

		return element;
	}

	@Override
	public String getCreateImageId() {
		return Images.PATTERN_ANNOTATION;
	}

	@Override
	protected boolean canCreate(Object context) {
		return context instanceof Pattern;
	}
}
