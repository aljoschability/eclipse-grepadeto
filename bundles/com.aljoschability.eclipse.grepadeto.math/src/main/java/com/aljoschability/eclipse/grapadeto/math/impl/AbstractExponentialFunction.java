package com.aljoschability.eclipse.grapadeto.math.impl;

import java.util.Map;

import com.aljoschability.eclipse.grapadeto.math.Activator;
import com.aljoschability.eclipse.grapadeto.math.Calculator;
import com.aljoschability.eclipse.grapadeto.math.Function;

public abstract class AbstractExponentialFunction implements Calculator {

	public static final String DELTA_X = "deltaX"; //$NON-NLS-1$
	public static final String DELTA_Y = "deltaY"; //$NON-NLS-1$
	public static final String DELTA_EPSILON = "epsilon"; //$NON-NLS-1$
	public static final String DELTA_COMPRESSION = "compression"; //$NON-NLS-1$

	@Override
	public double getValue(double x, Map<String, Double> parameters) {
		double deltaX = parameters.get(DELTA_X);
		double deltaY = parameters.get(DELTA_Y);
		double epsilon = parameters.get(DELTA_EPSILON);
		double compression = parameters.get(DELTA_COMPRESSION);

		if (compression == 0.0d) {
			// otherwise: division by zero
			Activator.getInstance().error("Invalid parameter values: the compression cannot be zero.");
			return Function.ERROR_VALUE;
		}

		double value = calculateTempValue(x, deltaX, compression);

		if (value == 0.0d) {
			// otherwise: division by zero
			Activator.getInstance().error("Invalid parameter values: the calculated value cannot be zero.");
			return Function.ERROR_VALUE;
		}

		return ((1.0d + epsilon) / value) + deltaY;
	}

	protected abstract double calculateTempValue(double x, double deltaX, double compression);
}
