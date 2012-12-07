package com.aljoschability.eclipse.grapadeto.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Link;

import com.aljoschability.eclipse.grapadeto.Catalog;
import com.aljoschability.eclipse.grapadeto.Modifier;
import com.aljoschability.eclipse.grapadeto.Named;
import com.aljoschability.eclipse.grapadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grapadeto.Pattern;
import com.aljoschability.eclipse.grapadeto.PatternOccurrence;
import com.aljoschability.eclipse.grapadeto.model.ElementLabeler;
import com.aljoschability.eclipse.grapadeto.model.Metamodel;
import com.aljoschability.eclipse.grapadeto.model.ParsePage;
import com.aljoschability.eclipse.grapadeto.model.impl.MetamodelImpl;
import com.aljoschability.eclipse.grapadeto.trigger.Triggerer;

public final class ModelUtil {
	private static final String MODEL = "com.aljoschability.patterns.model"; //$NON-NLS-1$
	private static final String PACKAGE = "package"; //$NON-NLS-1$
	private static final String ID = "id"; //$NON-NLS-1$
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String TRIGGERER = "triggerer"; //$NON-NLS-1$
	private static final String LABELER = "labeler"; //$NON-NLS-1$
	private static final String PARSE_PAGE = "parse_page"; //$NON-NLS-1$
	private static final String URI = "nsURI"; //$NON-NLS-1$

	private static final Triggerer DEFAULT_TRIGGERER = new Triggerer() {
		@Override
		public EClass getTrigger(Collection<EClass> possibilities) {
			if (possibilities != null) {
				for (EClass eClass : possibilities) {
					return eClass;
				}
			}
			return null;
		}
	};

	private static final ElementLabeler DEFAULT_LABELER = new ElementLabeler() {
		@Override
		public String getLabel(EObject element) {
			return String.valueOf(element);
		}
	};

	private static Map<String, Metamodel> models;

	public static Collection<Metamodel> getAllMetamodels() {
		return getMetamodels().values();
	}

	public static Metamodel getMetamodel(String id) {
		return getMetamodels().get(id);
	}

	private static Map<String, Metamodel> getMetamodels() {
		if (models == null) {
			// collect registered models
			models = new HashMap<String, Metamodel>();

			// go through all registered extensions
			for (IConfigurationElement ce : Platform.getExtensionRegistry().getConfigurationElementsFor(MODEL)) {
				// deliver simple attributes
				String id = ce.getAttribute(ID);
				String name = ce.getAttribute(NAME);
				String description = ce.getAttribute(DESCRIPTION);

				// create model
				MetamodelImpl model = new MetamodelImpl(id, name, description);

				// get trigger chooser
				if (ce.getAttribute(TRIGGERER) != null) {
					try {
						Object elem = ce.createExecutableExtension(TRIGGERER);
						if (elem instanceof Triggerer) {
							model.setTriggerer((Triggerer) elem);
						}
					} catch (CoreException e) {
						// default will be added later
					}
				}

				// get labeler
				if (ce.getAttribute(LABELER) != null) {
					try {
						Object elem = ce.createExecutableExtension(LABELER);
						if (elem instanceof ElementLabeler) {
							model.setLabeler((ElementLabeler) elem);
						}
					} catch (CoreException e) {
						// default will be added later
					}
				}

				// get labeler
				if (ce.getAttribute(PARSE_PAGE) != null) {
					try {
						Object elem = ce.createExecutableExtension(PARSE_PAGE);
						if (elem instanceof ParsePage) {
							model.setParseOptionsPage((ParsePage) elem);
						}
					} catch (CoreException e) {
						// default will be added later
					}
				}

				// add packages
				for (IConfigurationElement pack : ce.getChildren(PACKAGE)) {
					model.addPackage(pack.getAttribute(URI));
				}

				// add the model
				if (isValid(model)) {
					models.put(id, model);
				} else {
					// TODO: throw to log
					System.out.println("could not register model");
				}
			}
		}

		return models;
	}

	private static boolean isValid(MetamodelImpl model) {
		// check for package uri existence
		if (!model.hasPackages()) {
			return false;
		}

		// check labeler
		if (model.getLabeler() == null) {
			// assign default labeler
			model.setLabeler(DEFAULT_LABELER);
		}

		// check for trigger chooser
		if (model.getTriggerer() == null) {
			// assign default
			model.setTriggerer(DEFAULT_TRIGGERER);
		}

		return true;
	}

	private ModelUtil() {
		// hide constructor
	}

	public static boolean isCreate(PatternOccurrence element) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean isCreate(Link element) {
		// TODO Auto-generated method stub
		return false;
	}

	public static String getText(Modifier modifier) {
		return modifier.getName();
	}

	public static boolean isValidName(Named element, String name) {
		// check for null or empty
		if (name == null || name.isEmpty()) {
			return false;
		}

		if (element.eContainer() instanceof Pattern) {
			Pattern pattern = (Pattern) element.eContainer();

			// check annotations
			for (PatternOccurrence annotation : pattern.getPatterns()) {
				if (name.equals(annotation.getName())) {
					return false;
				}
			}

			// check objects
			for (ObjectOccurrence object : pattern.getObjects()) {
				if (name.equals(object.getName())) {
					return false;
				}
			}
		}

		return true;
	}

	public static Catalog getCatalog(Pattern pattern) {
		return (Catalog) pattern.eContainer();
	}
}
