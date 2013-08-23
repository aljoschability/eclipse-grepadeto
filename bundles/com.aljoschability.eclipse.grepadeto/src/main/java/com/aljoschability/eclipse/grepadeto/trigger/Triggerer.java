package com.aljoschability.eclipse.grepadeto.trigger;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;

public interface Triggerer {
	EClass getTrigger(Collection<EClass> possibilities);
}
