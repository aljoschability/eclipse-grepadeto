package com.aljoschability.eclipse.grapadeto.math.impl;

import com.aljoschability.eclipse.grapadeto.math.Parameter;

public class ParameterImpl implements Parameter {
	private final String id;
	private final String name;
	private final String description;
	private final double defaultValue;

	public ParameterImpl(String id, String name, String description, double defaultValue) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.defaultValue = defaultValue;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getDefaultValue() {
		return defaultValue;
	}
}
