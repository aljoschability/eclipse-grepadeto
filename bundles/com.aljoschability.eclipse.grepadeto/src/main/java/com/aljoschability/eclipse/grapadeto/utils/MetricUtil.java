package com.aljoschability.eclipse.grapadeto.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.aljoschability.eclipse.grapadeto.Activator;
import com.aljoschability.eclipse.grapadeto.metric.Metric;
import com.aljoschability.eclipse.grapadeto.metric.impl.MetricImpl;

public final class MetricUtil {
	private static final String ID_METRIC = "com.aljoschability.patterns.metric"; //$NON-NLS-1$
	private static final String ID = "id"; //$NON-NLS-1$
	private static final String ACRONYM = "acronym"; //$NON-NLS-1$
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$

	private static final Comparator<? super Metric> METRIC_COMPARATOR = new Comparator<Metric>() {
		@Override
		public int compare(Metric one, Metric two) {
			return one.getAcronym().compareTo(two.getAcronym());
		}
	};

	private static Map<String, Metric> metrics;
	private static List<Metric> sortedMetrics;

	public static Collection<Metric> getAllMetrics() {
		if (sortedMetrics == null) {
			sortedMetrics = new ArrayList<Metric>();

			Collections.sort(sortedMetrics, METRIC_COMPARATOR);
		}

		return sortedMetrics;
	}

	public static Metric getMetric(String id) {
		return getMetamodels().get(id);
	}

	private static Map<String, Metric> getMetamodels() {
		if (metrics == null) {
			// collect registered models
			metrics = new HashMap<String, Metric>();

			// go through all registered extensions
			for (IConfigurationElement ce : Platform.getExtensionRegistry().getConfigurationElementsFor(ID_METRIC)) {
				// deliver simple attributes
				String id = ce.getAttribute(ID);
				String acronym = ce.getAttribute(ACRONYM);
				String name = ce.getAttribute(NAME);
				String description = ce.getAttribute(DESCRIPTION);

				// create metric
				MetricImpl metric = new MetricImpl(id, acronym, name, description);

				// add the metric
				if (metrics.containsKey(id)) {
					Activator.get().error("A meta model with the same ID has already been registered.");
				} else {
					metrics.put(id, metric);
				}
			}
		}

		return metrics;
	}

	private MetricUtil() {
		// hide constructor
	}
}
