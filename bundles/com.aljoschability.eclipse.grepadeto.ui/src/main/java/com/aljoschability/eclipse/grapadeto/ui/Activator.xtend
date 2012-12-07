package com.aljoschability.eclipse.grapadeto.ui;

import com.aljoschability.core.ui.runtime.AbstractActivator
import com.aljoschability.core.ui.runtime.IActivator

final class Activator extends AbstractActivator {
	var static IActivator INSTANCE

	def static IActivator getInstance() {
		Activator::INSTANCE
	}

	override protected initialize() {
		Activator::INSTANCE = this

		addImage(UIImages.BANNER_CREATE_PATTERNS)
	}

	override protected dispose() {
		Activator::INSTANCE = null
	}
}
