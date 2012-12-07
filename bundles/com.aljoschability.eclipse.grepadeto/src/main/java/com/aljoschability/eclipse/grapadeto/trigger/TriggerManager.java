package com.aljoschability.eclipse.grapadeto.trigger;

import java.util.Collection;

import com.aljoschability.eclipse.grapadeto.Catalog;
import com.aljoschability.eclipse.grapadeto.Pattern;
import com.aljoschability.eclipse.grapadeto.utils.ModelUtil;

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
