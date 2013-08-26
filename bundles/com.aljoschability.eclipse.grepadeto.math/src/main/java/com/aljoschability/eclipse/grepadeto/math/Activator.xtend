package com.aljoschability.eclipse.grepadeto.math;

import com.aljoschability.core.ui.runtime.AbstractActivator
import com.aljoschability.core.ui.runtime.IActivator
import com.aljoschability.eclipse.grepadeto.math.impl.MathImages

final class Activator extends AbstractActivator {
	static IActivator INSTANCE

	def static IActivator getInstance() {
		Activator::INSTANCE
	}

	override void initialize() {
		Activator::INSTANCE = this

		addImage(MathImages.LIM_0)
		addImage(MathImages.LIM_1)
		addImage(MathImages.LINEAR)
	}

	override protected dispose() {
		Activator::INSTANCE = null
	}
}
