package com.aljoschability.eclipse.grapadeto.diagram;

import com.aljoschability.core.ui.runtime.AbstractActivator
import com.aljoschability.core.ui.runtime.IActivator

final class Activator extends AbstractActivator {
	var static IActivator INSTANCE

	def static IActivator get() {
		Activator::INSTANCE
	}

	override protected initialize() {
		Activator::INSTANCE = this
	}

	override protected dispose() {
		Activator::INSTANCE = null
	}
}
