package com.aljoschability.eclipse.grepadeto.diagram.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;

public class EPackageResolver {
	private Set<EPackage> visitedPackages;

	public Set<EPackage> getAllPackages(EPackage pack) {
		return getAllPackages(Collections.singleton(pack));
	}

	public Set<EPackage> getAllPackages(Collection<EPackage> packs) {
		// check for empty
		if (packs.isEmpty()) {
			return Collections.emptySet();
		}

		// collect
		Set<EPackage> visited = new HashSet<EPackage>();
		for (EPackage pack : packs) {
			if (pack != null) {
				collect(pack, visited);
			}
		}

		return visited;
	}

	private void collect(EPackage element, Set<EPackage> visited) {
		handleEPackage(element, visited);
		for (EPackage children : element.getESubpackages()) {
			collect(children, visited);
		}
	}

	private void handleEPackage(EPackage element, Set<EPackage> visited) {
		if (element != null && visited.add(element)) {
			this.visitedPackages = visited;
			Set<EClassifier> visitedClassifiers = new HashSet<EClassifier>();
			for (EClassifier classifier : element.getEClassifiers()) {
				handleClassifier(classifier, visitedClassifiers);
			}

			for (EClassifier classifier : visitedClassifiers) {
				EPackage p = classifier.getEPackage();
				if (p != null) {
					visited.add(p);
				} else {
					System.out.println("cannot handle EClassifier " + classifier);
				}
			}
		}
	}

	private void handleClassifier(EClassifier element, Set<EClassifier> visited) {
		if (element != null && visited.add(element)) {
			handleEPackage(element.getEPackage(), visitedPackages);
			handleTypeParameters(element.getETypeParameters(), visited);
			if (element instanceof EClass) {
				EClass type = (EClass) element;
				handleStructuralFeatures(type.getEStructuralFeatures(), visited);
				handleOperations(type.getEOperations(), visited);
				handleGenericTypes(type.getEGenericSuperTypes(), visited);
			}
		}
	}

	private void handleTypeParameters(Collection<ETypeParameter> elements, Set<EClassifier> visited) {
		if (elements != null) {
			for (ETypeParameter element : elements) {
				handleTypeParameter(element, visited);
			}
		}
	}

	private void handleTypeParameter(ETypeParameter element, Set<EClassifier> visited) {
		if (element != null) {
			handleGenericTypes(element.getEBounds(), visited);
		}
	}

	private void handleGenericTypes(Collection<EGenericType> elements, Set<EClassifier> visited) {
		if (elements != null) {
			for (EGenericType element : elements) {
				handleGenericType(element, visited);
			}
		}
	}

	private void handleGenericType(EGenericType element, Set<EClassifier> visited) {
		if (element != null) {
			handleClassifier(element.getEClassifier(), visited);
			handleClassifier(element.getERawType(), visited);
			handleGenericType(element.getELowerBound(), visited);
			handleGenericType(element.getEUpperBound(), visited);
			handleGenericTypes(element.getETypeArguments(), visited);
			handleTypeParameter(element.getETypeParameter(), visited);
		}
	}

	private void handleStructuralFeatures(Collection<EStructuralFeature> elements, Set<EClassifier> visited) {
		if (elements != null) {
			for (EStructuralFeature element : elements) {
				handleGenericType(element.getEGenericType(), visited);
			}
		}
	}

	private void handleOperations(Collection<EOperation> elements, Set<EClassifier> visited) {
		if (elements != null) {
			for (EOperation element : elements) {
				handleGenericType(element.getEGenericType(), visited);
				handleTypeParameters(element.getETypeParameters(), visited);
				handleParameters(element.getEParameters(), visited);
				handleGenericTypes(element.getEGenericExceptions(), visited);
			}
		}
	}

	private void handleParameters(Collection<EParameter> elements, Set<EClassifier> visited) {
		if (elements != null) {
			for (EParameter element : elements) {
				handleClassifier(element.getEType(), visited);
				handleGenericType(element.getEGenericType(), visited);
			}
		}
	}
}
