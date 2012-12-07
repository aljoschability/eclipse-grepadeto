package com.aljoschability.eclipse.grapadeto.metric;

import org.eclipse.emf.ecore.EObject;

public interface MetricCalculator {
	double getValue(EObject element);

	boolean canBeCalculated(EObject element);
}
