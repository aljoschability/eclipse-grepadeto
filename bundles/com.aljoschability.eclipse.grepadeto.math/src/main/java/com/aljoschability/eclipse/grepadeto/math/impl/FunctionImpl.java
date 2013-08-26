package com.aljoschability.eclipse.grepadeto.math.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;

import com.aljoschability.eclipse.grepadeto.math.Calculator;
import com.aljoschability.eclipse.grepadeto.math.Function;
import com.aljoschability.eclipse.grepadeto.math.Parameter;

public final class FunctionImpl implements Function {

	private final String id;
	private final String name;
	private final Calculator calculator;

	private final String description;
	private final Image image;
	private final List<Parameter> parameters;

	public FunctionImpl(String id, String name, String description, Image image, Calculator calculator) {
		this.id = id;
		this.name = name;
		this.calculator = calculator;
		this.description = description;
		this.image = image;
		this.parameters = new ArrayList<Parameter>();
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
	public String getDescription() {
		return description;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public List<Parameter> getParameters() {
		return parameters;
	}

	public boolean addParameter(Parameter parameter) {
		return getParameters().add(parameter);
	}

	public double getValue(double x, Map<String, Double> parameters) {
		return calculator.getValue(x, parameters);
	}
}
