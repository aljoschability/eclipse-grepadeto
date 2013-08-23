package com.aljoschability.eclipse.grepadeto.diagram.features.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.grepadeto.GrepadetoFactory;
import com.aljoschability.eclipse.grepadeto.GrepadetoPackage;
import com.aljoschability.eclipse.grepadeto.Link;
import com.aljoschability.eclipse.grepadeto.Node;
import com.aljoschability.eclipse.grepadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grepadeto.PatternOccurrence;
import com.aljoschability.eclipse.grepadeto.diagram.Images;

public class CreateLinkFeature extends AbstractCreateEdgeFeature {
	private static final String NAME = "Link Variable";
	private static final String DESCRIPTION = EcoreUtil.getDocumentation(GrepadetoPackage.Literals.LINK);

	public CreateLinkFeature(IFeatureProvider fp) {
		super(fp, NAME, DESCRIPTION);
	}

	@Override
	protected boolean canStartConnection(Object source) {
		return source instanceof Node;
	}

	@Override
	protected boolean canCreate(Object source, Object target) {
		return source instanceof Node && target instanceof Node;
	}

	@Override
	protected EObject create(Object source, Object target) {
		// create element
		Link element = GrepadetoFactory.eINSTANCE.createLink();

		if (source instanceof PatternOccurrence) {
			element.setPattern(((PatternOccurrence) source).getPattern());
		} else if (source instanceof ObjectOccurrence) {
			element.setPattern(((ObjectOccurrence) source).getPattern());
		}

		element.setName("role");
		element.setSource((Node) source);
		element.setTarget((Node) target);

		return element;
	}

	@Override
	public String getCreateImageId() {
		return Images.LINK;
	}
}
