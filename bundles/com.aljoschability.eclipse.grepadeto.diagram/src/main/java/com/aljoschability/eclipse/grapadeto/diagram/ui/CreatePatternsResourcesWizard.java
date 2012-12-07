package com.aljoschability.eclipse.grapadeto.diagram.ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.aljoschability.eclipse.grapadeto.Catalog;
import com.aljoschability.eclipse.grapadeto.GrapadetoFactory;
import com.aljoschability.eclipse.grapadeto.diagram.Activator;

public class CreatePatternsResourcesWizard extends Wizard implements INewWizard {

	private CreatePatternsResourcesWizardPage page;
	protected ResourceSet resourceSet;

	@Override
	public void addPages() {
		addPage(page);
	}

	public CreatePatternsResourcesWizard() {
		super();

		// set wizard data
		setWindowTitle("New Story Diagram Catalog");
		// setDefaultPageImageDescriptor(descriptor);
		setNeedsProgressMonitor(true);

		// set dialog settings
		String settingsID = CreatePatternsResourcesWizard.class.getSimpleName();
		IDialogSettings plugin = Activator.get().getDialogSettings();
		IDialogSettings settings = plugin.getSection(settingsID);
		if (settings == null) {
			settings = plugin.addNewSection(settingsID);
		}
		setDialogSettings(settings);

		// create page
		page = new CreatePatternsResourcesWizardPage();
		resourceSet = new ResourceSetImpl();
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection != null && !selection.isEmpty()) {
			page.setSelection(selection);
		}
	}

	@Override
	public boolean performFinish() {
		// TODO save dialog settings

		final String modelPath = page.getModelPath();
		final String diagramPath = page.getDiagramPath();
		final String metamodelID = page.getMetamodelID();

		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				Catalog catalog = GrapadetoFactory.eINSTANCE.createCatalog();
				catalog.setModelId(metamodelID);

				URI modelUri = URI.createPlatformResourceURI(modelPath, true);
				Resource modelResource = resourceSet.createResource(modelUri);

				modelResource.getContents().add(catalog);
				try {
					modelResource.save(Collections.emptyMap());
				} catch (IOException e) {
					e.printStackTrace();
					throw new InvocationTargetException(e);
				}

				Diagram diagram = Graphiti.getCreateService().createDiagram("", "diagram_name", true);

				URI diagramUri = URI.createPlatformResourceURI(diagramPath, true);
				Resource diagramResource = resourceSet.createResource(diagramUri);

				PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
				link.getBusinessObjects().add(catalog);
				diagram.setLink(link);

				diagramResource.getContents().add(diagram);
				try {
					diagramResource.save(Collections.emptyMap());
				} catch (IOException e) {
					e.printStackTrace();
					throw new InvocationTargetException(e);
				}
			}
		};

		try {
			getContainer().run(true, false, runnable);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
