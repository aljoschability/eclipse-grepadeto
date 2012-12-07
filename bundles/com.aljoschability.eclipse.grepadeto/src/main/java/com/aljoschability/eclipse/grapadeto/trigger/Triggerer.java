package com.aljoschability.eclipse.grapadeto.trigger;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;

public interface Triggerer {
	EClass getTrigger(Collection<EClass> possibilities);
}
