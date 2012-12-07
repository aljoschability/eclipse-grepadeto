package com.aljoschability.eclipse.grapadeto.ui.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
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
import com.aljoschability.eclipse.grapadeto.Pattern;
import com.aljoschability.eclipse.grapadeto.ui.Activator;
import com.aljoschability.eclipse.grapadeto.ui.UIImages;

public class CreatePatternsCatalogWizard extends Wizard implements INewWizard {
	private CreatePatternsCatalogWizardPage page;
	private List<?> selection;
	private final ResourceSet resourceSet;

	public CreatePatternsCatalogWizard() {
		// create resource set
		resourceSet = new ResourceSetImpl();

		// configure
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(UIImages.getImageDescriptor(UIImages.BANNER_CREATE_PATTERNS));
		setWindowTitle("Create Patterns Catalog");

		// set dialog settings
		String key = CreatePatternsCatalogWizard.class.getName();
		IDialogSettings pluginSettings = Activator.getInstance().getDialogSettings();
		IDialogSettings wizardSettings = pluginSettings.getSection(key);
		if (wizardSettings == null) {
			wizardSettings = pluginSettings.addNewSection(key);
		}
		setDialogSettings(wizardSettings);
	}

	@Override
	public void addPages() {
		page = new CreatePatternsCatalogWizardPage();
		addPage(page);

		// set container
		if (selection != null) {
			for (Object object : selection) {
				if (object instanceof IContainer) {
					page.setContainer((IContainer) object);
					break;
				} else if (object instanceof IFile) {
					page.setContainer(((IFile) object).getParent());
				}
			}
		}
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (!selection.isEmpty()) {
			this.selection = selection.toList();
		}
	}

	@Override
	public boolean performFinish() {
		// save settings

		// get paths
		final String modelPath = page.getModelPath();
		final String diagramPath = page.getDiagramPath();
		final String modelID = "com.aljoschability.patterns.model.fzi";

		// create runnable
		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				// prepare catalog
				Catalog catalog = GrapadetoFactory.eINSTANCE.createCatalog();
				catalog.setName("First Catalog");
				catalog.setModelId(modelID);

				Pattern pattern = GrapadetoFactory.eINSTANCE.createPattern();
				pattern.setName("First Pattern");
				pattern.setCatalog(catalog);

				// store model resource
				URI modelUri = URI.createPlatformResourceURI(modelPath, true);
				Resource modelResource = resourceSet.createResource(modelUri);
				modelResource.getContents().add(catalog);
				try {
					modelResource.save(Collections.emptyMap());
				} catch (IOException e) {
					e.printStackTrace();
				}

				// prepare diagram container
				Diagram diagram = Graphiti.getCreateService().createDiagram("PatternsDiagram", "First Pattern Diagram",
						true);

				PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
				link.getBusinessObjects().add(pattern);
				diagram.setLink(link);

				// store diagram resource
				URI diagramUri = URI.createPlatformResourceURI(diagramPath, true);
				Resource diagramResource = resourceSet.createResource(diagramUri);
				diagramResource.getContents().add(diagram);
				try {
					diagramResource.save(Collections.emptyMap());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		// start the creation
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
