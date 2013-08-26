package com.aljoschability.eclipse.grepadeto.math;

import java.util.Map;

public interface Calculator {
	/**
	 * This method should return the value of the specified function at the given x-value and the parameters.
	 * 
	 * @param x The x-value.
	 * @param parameters The mapped parameters.
	 * @return Should return the value at the specified values.
	 */
	double getValue(double x, Map<String, Double> parameters);
}
