package com.aljoschability.eclipse.grapadeto.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public interface ParsePage {
	void createOptionControls(Composite parent, WizardPage page);

	void hookListeners();

	void loadDefaults(IDialogSettings settings);

	void saveDefaults(IDialogSettings settings);

	IStatus parse(ResourceSet ress, IProject project, IProgressMonitor monitor);

	Resource getResource();
}
