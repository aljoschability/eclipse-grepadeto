package com.aljoschability.eclipse.grepadeto.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.graphics.Image;

import com.aljoschability.eclipse.grepadeto.model.ElementLabeler;
import com.aljoschability.eclipse.grepadeto.model.Metamodel;
import com.aljoschability.eclipse.grepadeto.model.ParsePage;
import com.aljoschability.eclipse.grepadeto.trigger.Triggerer;

public class MetamodelImpl implements Metamodel {

	private final String id;
	private final String name;
	private final String description;

	private final Collection<String> packageNsUris;

	private Triggerer chooser;
	private ElementLabeler labeler;
	private ParsePage parseOptionsPage;

	private List<EClass> types;
	private Image image;

	public MetamodelImpl(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;

		packageNsUris = new ArrayList<String>();
	}

	public boolean addPackage(String uri) {
		return packageNsUris.add(uri);
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String getID() {
		return id;
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
	public Triggerer getTriggerer() {
		return chooser;
	}

	@Override
	public ElementLabeler getLabeler() {
		return labeler;
	}

	@Override
	public ParsePage getParsePage() {
		return parseOptionsPage;
	}

	@Override
	public List<EClass> getAllEClasses() {
		if (types == null) {
			types = new ArrayList<EClass>();

			for (String nsUri : packageNsUris) {
				add(EPackage.Registry.INSTANCE.getEPackage(nsUri));
			}

			// sort them
			Collections.sort(types, new Comparator<EClass>() {
				@Override
				public int compare(EClass one, EClass two) {
					return one.getName().compareTo(two.getName());
				}

			});
		}

		return types;
	}

	public void setTriggerer(Triggerer chooser) {
		this.chooser = chooser;
	}

	public void setLabeler(ElementLabeler labeler) {
		this.labeler = labeler;
	}

	public void setParseOptionsPage(ParsePage parseOptionsPage) {
		this.parseOptionsPage = parseOptionsPage;
	}

	private void add(EPackage pack) {
		// add sub packages
		for (EPackage sub : pack.getESubpackages()) {
			add(sub);
		}

		// add all contained EClass
		for (EClassifier type : pack.getEClassifiers()) {
			if (type instanceof EClass) {
				types.add((EClass) type);
			}
		}
	}

	public boolean hasPackages() {
		return !packageNsUris.isEmpty();
	}

	@Override
	public Image getImage() {
		return image;
	}
}
