package com.aljoschability.eclipse.grapadeto.diagram.util;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

import com.aljoschability.eclipse.grapadeto.Constraint;
import com.aljoschability.eclipse.grapadeto.ConstraintExpression;
import com.aljoschability.eclipse.grapadeto.Link;
import com.aljoschability.eclipse.grapadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grapadeto.Pattern;
import com.aljoschability.eclipse.grapadeto.PatternOccurrence;

public final class TextUtil {
	private TextUtil() {
		// hide constructor
	}

	public static String getText(ObjectOccurrence objectVariable) {
		StringBuilder text = new StringBuilder();

		text.append(objectVariable.getName());

		text.append(":");

		EClass classifier = objectVariable.getType();
		if (classifier == null) {
			text.append(classifier);
		} else {
			text.append(classifier.getName());
		}

		return text.toString();
	}

	public static String getText(PatternOccurrence primitiveVariable) {
		StringBuilder text = new StringBuilder();

		text.append(primitiveVariable.getName());

		text.append(":");

		Pattern classifier = primitiveVariable.getType();
		if (classifier == null) {
			text.append(classifier);
		} else {
			text.append(classifier.getName());
		}

		return text.toString();
	}

	public static String getText(Link link) {
		if (link.getType() != null) {
			return link.getType().getName();
		}
		return String.valueOf(link.getName());
	}

	private static String getText(EAttribute attribute) {
		if (attribute != null) {
			return attribute.getName();
		}

		return String.valueOf(attribute);
	}

	public static String getText(Constraint element) {
		return String.valueOf(element);
	}

	public static String getText(ConstraintExpression element) {
		return String.valueOf(element);
	}
}
