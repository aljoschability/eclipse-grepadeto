package com.aljoschability.eclipse.grapadeto.model;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.graphics.Image;

import com.aljoschability.eclipse.grapadeto.trigger.Triggerer;

public interface Metamodel {
	String getID();

	String getName();

	String getDescription();

	Image getImage();

	Collection<EClass> getAllEClasses();

	ElementLabeler getLabeler();

	Triggerer getTriggerer();

	ParsePage getParsePage();
}