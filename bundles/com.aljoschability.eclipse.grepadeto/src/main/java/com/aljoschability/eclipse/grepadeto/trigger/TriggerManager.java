package com.aljoschability.eclipse.grepadeto.trigger;

import java.util.Collection;

import com.aljoschability.eclipse.grepadeto.Catalog;
import com.aljoschability.eclipse.grepadeto.Pattern;
import com.aljoschability.eclipse.grepadeto.utils.ModelUtil;

public final class TriggerManager {
	private Triggerer triggerer;

	public TriggerManager(Catalog catalog) {
		this(catalog.getPatterns());
	}

	public TriggerManager(Collection<Pattern> patterns) {
		for (Pattern pattern : patterns) {
			triggerer = ModelUtil.getMetamodel(ModelUtil.getCatalog(pattern).getModelId()).getTriggerer();
			if (triggerer != null) {
				break;
			}
		}

		if (triggerer != null) {
			throw new RuntimeException();
		}
	}
}
