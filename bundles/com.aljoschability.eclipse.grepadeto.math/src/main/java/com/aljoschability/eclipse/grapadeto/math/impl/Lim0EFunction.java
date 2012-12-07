package com.aljoschability.eclipse.grapadeto.math.impl;

public class Lim0EFunction extends AbstractExponentialFunction {

	@Override
	protected double calculateTempValue(double x, double deltaX, double compression) {
		return (1.0d + Math.exp((x + deltaX) / compression));
	}
}
