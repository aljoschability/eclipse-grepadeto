package com.aljoschability.eclipse.grapadeto.diagram.editor;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

import com.aljoschability.eclipse.grapadeto.Catalog;
import com.aljoschability.eclipse.grapadeto.GrapadetoFactory;
import com.aljoschability.eclipse.grapadeto.Pattern;
import com.aljoschability.eclipse.grapadeto.diagram.editor.provider.DiagramTypeProvider;
import com.aljoschability.eclipse.grapadeto.diagram.editor.provider.ImageProvider;
import com.aljoschability.eclipse.grapadeto.diagram.ui.StoryEditorPageContainer;
import com.aljoschability.eclipse.grapadeto.diagram.util.EPackageResolver;

public class PatternsDiagramEditor extends FormEditor implements ITabbedPropertySheetPageContributor,
		IEditingDomainProvider {

	public static final String ID = "org.storydriven.modeling.diagram.ui.StoryDiagramEditor"; //$NON-NLS-1$

	private TransactionalEditingDomain editingDomain;
	private PatternsDiagramOverviewPage overviewPage;

	private Resource modelResource;
	private Resource diagramResource;

	private final Map<Pattern, StoryEditorPageContainer> activities;

	private Catalog catalog;

	private Collection<EPackage> referencesInput;

	@Override
	public IFileEditorInput getEditorInput() {
		return (IFileEditorInput) super.getEditorInput();
	}

	public PatternsDiagramEditor() {
		super();

		activities = new HashMap<Pattern, StoryEditorPageContainer>();

		GraphitiUi.getExtensionManager().createDiagramTypeProvider(DiagramTypeProvider.ID);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		try {
			modelResource.save(Collections.emptyMap());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getCommandStack().flush();
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void addPages() {
		overviewPage = new PatternsDiagramOverviewPage(this);
		try {
			addPage(overviewPage);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Catalog getCatalog() {
		if (catalog == null) {
			// get it
			String path = getEditorInput().getFile().getFullPath().toString();
			URI uri = URI.createPlatformResourceURI(path, true);

			// try to load it
			try {
				modelResource = getResourceSet().getResource(uri, true);
			} catch (WrappedException e) {
				e.exception().printStackTrace();
				return null;
			}

			// find catalog
			for (EObject content : modelResource.getContents()) {
				if (content instanceof Diagram) {
					for (EObject linkedBO : ((Diagram) content).getLink().getBusinessObjects()) {
						if (linkedBO instanceof Catalog) {
							catalog = (Catalog) content;
						}
					}
				} else if (content instanceof Catalog) {
					catalog = (Catalog) content;
				}
			}
		}

		return catalog;
	}

	public Collection<EPackage> getReferencesInput() {
		if (referencesInput == null) {
			referencesInput = new HashSet<EPackage>();

			// create a resolver
			EPackageResolver resolver = new EPackageResolver();

			// get the references
			Map<EObject, Collection<Setting>> references = EcoreUtil.ExternalCrossReferencer.find(getResourceSet());

			// go through them
			for (EObject key : references.keySet()) {
				for (Setting s : references.get(key)) {
					Object ob = s.get(true);
					if (ob instanceof EFactory) {
						referencesInput.addAll(resolver.getAllPackages(((EFactory) ob).getEPackage()));
					} else if (ob instanceof EClassifier) {
						referencesInput.addAll(resolver.getAllPackages(((EClassifier) ob).getEPackage()));
					}
				}
			}
		}

		return referencesInput;
	}

	public void open(Pattern data) {
		StoryEditorPageContainer element = activities.get(data);

		IEditorInput input = element.getInput();
		IEditorPart editor = element.getEditor();
		try {
			int index = addPage(editor, input);

			((CTabFolder) getContainer()).getItem(index).setShowClose(true);
			setPageText(index, input.getName());
			setPageImage(index, ImageProvider.getImage(ImageProvider.PATTERN));

			setActivePage(index);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResourceSet getResourceSet() {
		return getEditingDomain().getResourceSet();
	}

	public void execute(Command command) {
		getCommandStack().execute(command);
	}

	public CommandStack getCommandStack() {
		return getEditingDomain().getCommandStack();
	}

	@Override
	public TransactionalEditingDomain getEditingDomain() {
		if (editingDomain == null) {
			editingDomain = GraphitiUi.getEmfService().createResourceSetAndEditingDomain();
		}

		return editingDomain;
	}

	@Override
	public String getContributorId() {
		return ID + ".PropertyContributor";
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addContainer(Pattern a, StoryEditorPageContainer c) {
		activities.put(a, c);
	}

	public void add() {
		Pattern pattern = GrapadetoFactory.eINSTANCE.createPattern();
		pattern.setName("blablabla");
		
		getCatalog().getPatterns().add(pattern);
		System.out.println("add new");
	}

	public void remove(Pattern selected) {
		System.out.println("remove " + selected.getName());
	}
}
