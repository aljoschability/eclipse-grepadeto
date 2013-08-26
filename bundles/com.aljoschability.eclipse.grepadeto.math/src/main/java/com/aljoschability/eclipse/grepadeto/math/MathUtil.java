package com.aljoschability.eclipse.grepadeto.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.aljoschability.eclipse.grepadeto.math.Activator;
import com.aljoschability.eclipse.grepadeto.math.impl.FunctionImpl;
import com.aljoschability.eclipse.grepadeto.math.impl.ParameterImpl;

public final class MathUtil {
	private static final String ID_FUNCTION = "com.aljoschability.patterns.math.function"; //$NON-NLS-1$
	private static final String KEY_ID = "id"; //$NON-NLS-1$
	private static final String KEY_NAME = "name"; //$NON-NLS-1$
	private static final String KEY_DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String KEY_CLASS = "class"; //$NON-NLS-1$
	private static final String KEY_IMAGE = "image"; //$NON-NLS-1$
	private static final String KEY_PARAMETER = "parameter"; //$NON-NLS-1$
	private static final String KEY_DEFAULT_VALUE = "default"; //$NON-NLS-1$

	private static final Comparator<? super Function> FUNCTION_COMPARATOR = new Comparator<Function>() {
		@Override
		public int compare(Function one, Function two) {
			return one.getID().compareToIgnoreCase(two.getID());
		}
	};

	private static Map<String, FunctionImpl> functions;

	private static List<Function> sortedFunctions;

	public static List<Function> getAllFunctions() {
		if (sortedFunctions == null) {
			sortedFunctions = new ArrayList<Function>();

			sortedFunctions.addAll(getFunctions().values());

			Collections.sort(sortedFunctions, FUNCTION_COMPARATOR);
		}

		return sortedFunctions;
	}

	private static Map<String, FunctionImpl> getFunctions() {
		if (functions == null) {
			// create map
			functions = new HashMap<String, FunctionImpl>();

			// go through all registered extensions
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			for (IConfigurationElement ce : registry.getConfigurationElementsFor(ID_FUNCTION)) {
				// try to get the calculator
				Calculator calculator = null;
				try {
					Object elem = ce.createExecutableExtension(KEY_CLASS);
					if (elem instanceof Calculator) {
						calculator = (Calculator) elem;
					}
				} catch (CoreException e) {
					String text = "The given calculator class could not be initialized.";
					Activator.getInstance().error(text, e.getCause());
					continue;
				}

				if (calculator == null) {
					String text = "The registered function has no valid calculator.";
					Activator.getInstance().error(text);
					continue;
				}

				// deliver simple attributes
				String id = ce.getAttribute(KEY_ID);
				String name = ce.getAttribute(KEY_NAME);
				String description = ce.getAttribute(KEY_DESCRIPTION);
				String imagePath = ce.getAttribute(KEY_IMAGE);

				// get image
				String pluginId = "";
				Image image = AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, imagePath).createImage();

				// create the function
				FunctionImpl function = new FunctionImpl(id, name, description, image, calculator);

				// get parameters
				for (IConfigurationElement param : ce.getChildren(KEY_PARAMETER)) {
					String pId = param.getAttribute(KEY_ID);
					String pName = param.getAttribute(KEY_NAME);
					String pDescription = param.getAttribute(KEY_DESCRIPTION);
					String pDefaultValueText = param.getAttribute(KEY_DEFAULT_VALUE);
					double pDefaultValue = 0;

					// parse as double
					try {
						pDefaultValue = Double.parseDouble(pDefaultValueText);
					} catch (NumberFormatException e) {
						String text = "The given default value cannot be parsed as double.";
						Activator.getInstance().error(text, e);
					}

					Parameter parameter = new ParameterImpl(pId, pName, pDescription, pDefaultValue);
					function.addParameter(parameter);
				}

				// cache it
				functions.put(id, function);
			}
		}

		return functions;
	}

	public static double getValue(String id, Map<String, Double> parameters, double x) {
		// get function
		FunctionImpl function = getFunctions().get(id);

		// check for function
		if (function == null) {
			String message = "The mathematical function is not registered.";
			Activator.getInstance().error(message, new NullPointerException());
			return Function.ERROR_VALUE;
		}

		return function.getValue(x, parameters);
	}

	private MathUtil() {
		// hide constructor
	}
}
