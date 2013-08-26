package com.aljoschability.eclipse.grepadeto.math.impl;

import java.util.Map;

import com.aljoschability.eclipse.grepadeto.math.Calculator;

public class LinearFunction implements Calculator {

	private static final String DELTA_X = "deltaX"; //$NON-NLS-1$
	private static final String DELTA_Y = "deltaY"; //$NON-NLS-1$
	private static final String GRADIENT = "gradient"; //$NON-NLS-1$

	@Override
	public double getValue(double x, Map<String, Double> parameters) {
		double deltaX = parameters.get(DELTA_X);
		double deltaY = parameters.get(DELTA_Y);
		double gradient = parameters.get(GRADIENT);

		return gradient * (x - deltaX) + deltaY;
	}
}
