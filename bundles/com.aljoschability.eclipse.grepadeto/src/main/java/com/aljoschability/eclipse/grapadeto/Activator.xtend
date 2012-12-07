package com.aljoschability.eclipse.grapadeto;

import com.aljoschability.core.ui.runtime.AbstractActivator
import com.aljoschability.core.ui.runtime.IActivator

final class Activator extends AbstractActivator {
	var static IActivator INSTANCE

	def static IActivator get() {
		Activator::INSTANCE
	}

	override void initialize() {
		Activator::INSTANCE = this

		addImage(GrapadetoImages.UNKNOWN)
		addImage(GrapadetoImages.PROJECT)
		addImage(GrapadetoImages.DIRECTORY)
		addImage(GrapadetoImages.FILE)
		addImage(GrapadetoImages.PACKAGE)
		addImage(GrapadetoImages.CLASS_NONE)
		addImage(GrapadetoImages.CLASS_PUBLIC)
		addImage(GrapadetoImages.CLASS_PROTECTED)
		addImage(GrapadetoImages.CLASS_PRIVATE)
		addImage(GrapadetoImages.INTERFACE_NONE)
		addImage(GrapadetoImages.INTERFACE_PUBLIC)
		addImage(GrapadetoImages.INTERFACE_PROTECTED)
		addImage(GrapadetoImages.INTERFACE_PRIVATE)
		addImage(GrapadetoImages.ATTRIBUTE_NONE)
		addImage(GrapadetoImages.ATTRIBUTE_PUBLIC)
		addImage(GrapadetoImages.ATTRIBUTE_PROTECTED)
		addImage(GrapadetoImages.ATTRIBUTE_PRIVATE)
		addImage(GrapadetoImages.OPERATION_NONE)
		addImage(GrapadetoImages.OPERATION_PUBLIC)
		addImage(GrapadetoImages.OPERATION_PROTECTED)
		addImage(GrapadetoImages.OPERATION_PRIVATE)
		addImage(GrapadetoImages.VARIABLE)
	}

	override void dispose() {
		Activator::INSTANCE = null
	}
}
