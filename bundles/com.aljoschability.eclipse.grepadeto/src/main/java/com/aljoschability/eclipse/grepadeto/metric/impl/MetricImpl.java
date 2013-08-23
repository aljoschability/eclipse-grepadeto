package com.aljoschability.eclipse.grepadeto.metric.impl;

import com.aljoschability.eclipse.grepadeto.metric.Metric;

public class MetricImpl implements Metric {

	private final String id;
	private final String acronym;
	private final String name;
	private final String description;

	public MetricImpl(String id, String acronym, String name, String description) {
		this.id = id;
		this.acronym = acronym;
		this.name = name;
		this.description = description;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getAcronym() {
		return acronym;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof Metric) {
			return ((Metric) other).getAcronym().equals(getAcronym());
		}

		return super.equals(other);
	}
}
