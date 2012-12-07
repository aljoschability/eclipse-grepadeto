package com.aljoschability.eclipse.grapadeto.math;

import java.util.List;

import org.eclipse.swt.graphics.Image;

public interface Function {
	double ERROR_VALUE = -1.0d;

	String getID();

	String getName();

	String getDescription();

	Image getImage();

	List<Parameter> getParameters();
}
