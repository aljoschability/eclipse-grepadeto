package com.aljoschability.eclipse.grapadeto.diagram.ui;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.aljoschability.eclipse.grapadeto.diagram.Activator;

public final class CreateDiagramResourceDialog extends TitleAreaDialog {

	private static final String DESCRIPTION = "Select the container and file name for the resource in which the diagrams will be saved.";

	private IContainer container;
	private String filename;

	public CreateDiagramResourceDialog(IFile modelFile) {
		super(PlatformUI.getWorkbench().getDisplay().getActiveShell());

		container = modelFile.getParent();

		filename = modelFile.getName() + ".diagrams";
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);

		shell.setText("Create Diagram Resource");
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Create", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected IDialogSettings getDialogBoundsSettings() {
		String id = CreateDiagramResourceDialog.class.getSimpleName();
		IDialogSettings plugin = Activator.get().getDialogSettings();
		IDialogSettings settings = plugin.getSection(id);
		if (settings == null) {
			settings = plugin.addNewSection(id);
		}
		return settings;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Create Diagram Resource");
		setMessage(DESCRIPTION);
		setHelpAvailable(false);
		Composite main = (Composite) super.createDialogArea(parent);

		Composite content = new Composite(main, SWT.NONE);
		GridDataFactory.fillDefaults().applyTo(content);
		GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(content);

		TreeViewer containerViewer = new TreeViewer(content, SWT.BORDER | SWT.SINGLE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(containerViewer.getTree());

		containerViewer.setContentProvider(new WorkbenchContentProvider());
		containerViewer.setLabelProvider(new WorkbenchLabelProvider());
		containerViewer.setComparator(new ViewerComparator());
		containerViewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parent, Object element) {
				if (element instanceof IContainer) {
					return ((IContainer) element).getName().charAt(0) != '.';
				}

				return false;
			}
		});

		Composite filenameComposite = new Composite(content, SWT.NONE);
		GridDataFactory.fillDefaults().applyTo(filenameComposite);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(filenameComposite);

		Label filenameLabel = new Label(filenameComposite, SWT.TRAIL);
		GridDataFactory.fillDefaults().applyTo(filenameLabel);

		Text filenameText = new Text(filenameComposite, SWT.LEAD | SWT.BORDER | SWT.SINGLE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(filenameText);

		// initialize values
		containerViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
		containerViewer.setSelection(new StructuredSelection(container));

		filenameLabel.setText("File Name:");

		filenameText.setText(filename);

		return main;
	}

	public Resource getResource() {
		return null;
	}
}
