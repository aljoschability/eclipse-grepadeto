package com.aljoschability.eclipse.grapadeto.diagram.features.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grapadeto.GrapadetoFactory;
import com.aljoschability.eclipse.grapadeto.GrapadetoPackage;
import com.aljoschability.eclipse.grapadeto.Pattern;
import com.aljoschability.eclipse.grapadeto.PatternOccurrence;
import com.aljoschability.eclipse.grapadeto.diagram.Images;

public class CreatePatternAnnotationFeature extends AbstractCreateNodeFeature {
	private static String NAME = "Pattern Annotation";
	private static String DESCRIPTION = EcoreUtil.getDocumentation(GrapadetoPackage.Literals.PATTERN_OCCURRENCE);

	public CreatePatternAnnotationFeature(IFeatureProvider fp) {
		super(fp, NAME, DESCRIPTION);
	}

	@Override
	protected EObject create(Object context) {
		PatternOccurrence element = GrapadetoFactory.eINSTANCE.createPatternOccurrence();

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
