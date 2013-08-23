package com.aljoschability.eclipse.grepadeto.diagram.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public final class ResourceUtil {

	public static void save(Resource resource) throws IOException {
		Map<Object, Object> options = Collections.emptyMap();
		if (resource instanceof XMIResourceImpl) {
			options = ((XMIResourceImpl) resource).getDefaultSaveOptions();
		}

		resource.save(options);
	}

	public static void load(Resource resource) throws IOException {
		Map<Object, Object> options = Collections.emptyMap();
		if (resource instanceof XMIResourceImpl) {
			options = ((XMIResourceImpl) resource).getDefaultLoadOptions();
		}

		resource.load(options);
	}

	private ResourceUtil() {
		// hide the constructor
	}
}
