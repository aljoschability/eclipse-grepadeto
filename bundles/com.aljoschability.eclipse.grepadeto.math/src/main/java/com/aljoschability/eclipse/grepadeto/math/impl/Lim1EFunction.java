package com.aljoschability.eclipse.grepadeto.math.impl;

public class Lim1EFunction extends AbstractExponentialFunction {

	@Override
	protected double calculateTempValue(double x, double deltaX, double compression) {
		return (1.0d + Math.exp((-x + deltaX) / compression));
	}
}
