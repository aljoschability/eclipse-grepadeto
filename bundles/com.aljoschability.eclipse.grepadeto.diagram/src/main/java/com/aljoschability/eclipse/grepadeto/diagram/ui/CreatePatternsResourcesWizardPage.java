package com.aljoschability.eclipse.grepadeto.diagram.ui;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.aljoschability.eclipse.grepadeto.model.Metamodel;
import com.aljoschability.eclipse.grepadeto.utils.ModelUtil;

public class CreatePatternsResourcesWizardPage extends WizardPage {
	private IStructuredSelection selection;

	private Text containerText;
	private Text modelFileText;
	private Text diagramFileText;
	private TableViewer metamodelViewer;

	private Button containerButton;

	protected SelectWorkspaceContainerDialog containerDialog;

	protected String containerPath;

	protected String modelFile;

	protected String diagramFile;

	protected String metamodelID;

	protected CreatePatternsResourcesWizardPage() {
		super(CreatePatternsResourcesWizardPage.class.getName());

		setTitle("Create Patterns Diagram Catalog");
		setDescription("Select the options for the story diagram to be created.");

		containerDialog = new SelectWorkspaceContainerDialog(getShell());
	}

	@Override
	public void createControl(Composite parentComposite) {
		// create main composite
		Composite mainComposite = new Composite(parentComposite, SWT.NONE);
		GridDataFactory.fillDefaults().applyTo(mainComposite);
		GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(mainComposite);

		// create resource group
		Group resourceGroup = new Group(mainComposite, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(resourceGroup);
		GridLayoutFactory.fillDefaults().margins(6, 6).numColumns(3).applyTo(resourceGroup);

		Label containerLabel = new Label(resourceGroup, SWT.TRAIL);
		GridDataFactory.fillDefaults().applyTo(containerLabel);

		containerText = new Text(resourceGroup, SWT.LEAD | SWT.BORDER | SWT.SINGLE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(containerText);

		containerButton = new Button(resourceGroup, SWT.PUSH | SWT.CENTER);

		Label modelFileLabel = new Label(resourceGroup, SWT.TRAIL);
		GridDataFactory.fillDefaults().applyTo(modelFileLabel);

		modelFileText = new Text(resourceGroup, SWT.LEAD | SWT.BORDER | SWT.SINGLE);
		GridDataFactory.fillDefaults().grab(true, false).span(2, 1).applyTo(modelFileText);

		Label diagramFileLabel = new Label(resourceGroup, SWT.TRAIL);
		GridDataFactory.fillDefaults().applyTo(diagramFileLabel);

		diagramFileText = new Text(resourceGroup, SWT.LEAD | SWT.BORDER | SWT.SINGLE);
		GridDataFactory.fillDefaults().grab(true, false).span(2, 1).applyTo(diagramFileText);

		// create meta model group
		Group metamodelGroup = new Group(mainComposite, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(metamodelGroup);
		GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(metamodelGroup);

		metamodelViewer = new TableViewer(metamodelGroup, SWT.BORDER | SWT.SINGLE);
		metamodelViewer.setContentProvider(new ArrayContentProvider());
		metamodelViewer.setLabelProvider(new MetamodelLabelProvider());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(metamodelViewer.getControl());

		Table metamodelTable = metamodelViewer.getTable();
		metamodelTable.setHeaderVisible(true);
		metamodelTable.setLinesVisible(true);

		TableColumn nameTableColumn = new TableColumn(metamodelTable, SWT.LEAD);
		nameTableColumn.setText("Name");
		nameTableColumn.setWidth(200);

		// initialize values
		resourceGroup.setText("Resources");
		containerLabel.setText("Container:");
		containerButton.setText("Select");
		modelFileLabel.setText("Model File Name:");
		diagramFileLabel.setText("Diagram File Name:");
		metamodelGroup.setText("Meta Model");

		modelFileText.setText("default.patterns");
		diagramFileText.setText("default.patterns.diagrams");
		metamodelViewer.setInput(ModelUtil.getAllMetamodels());

		hookListeners();

		setControl(mainComposite);
	}

	private void hookListeners() {
		containerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (containerDialog.open() == Window.OK) {
					containerText.setText(containerDialog.getPath());
				}
			}
		});

		containerText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				containerPath = containerText.getText();
				setPageComplete(isValid());
			}
		});

		modelFileText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				modelFile = modelFileText.getText();
				setPageComplete(isValid());
			}
		});

		diagramFileText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				diagramFile = diagramFileText.getText();
				setPageComplete(isValid());
			}
		});

		metamodelViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				metamodelID = ((Metamodel) ((IStructuredSelection) metamodelViewer.getSelection()).getFirstElement())
						.getID();
				setPageComplete(isValid());
			}
		});
	}

	protected boolean isValid() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		// check container
		if (containerPath == null) {
			setErrorMessage("Select an accessible container!");
			return false;
		} else {
			IResource container = root.findMember(containerPath);
			if (!(container instanceof IContainer) || !container.isAccessible()) {
				setErrorMessage("Select an accessible container!");
				return false;
			}
		}

		IPath path = root.findMember(containerPath).getFullPath().addTrailingSeparator();

		// check model file
		if (modelFile == null) {
			setErrorMessage("Provide a model file name!");
			return false;
		} else {
			IResource model = root.findMember(path.append(modelFile));
			if (model instanceof IFile) {
				setMessage("Model file already exists and will be overwritten.", WARNING);
			}
		}

		// check diagram file
		if (diagramFile == null) {
			setErrorMessage("Provide a diagram file name!");
			return false;
		} else {
			IResource diagram = root.findMember(path.append(diagramFile));
			if (diagram instanceof IFile) {
				setMessage("Diagram file already exists and will be overwritten.", WARNING);
			}
		}

		if (metamodelID == null) {
			setErrorMessage("Select the meta model for the catalog!");
			return false;
		}

		setErrorMessage(null);
		setMessage(null);
		return true;
	}

	public void setSelection(IStructuredSelection selection) {
		this.selection = selection;
	}

	public String getModelPath() {
		return containerPath + IPath.SEPARATOR + modelFile;
	}

	public String getDiagramPath() {
		return containerPath + IPath.SEPARATOR + diagramFile;
	}

	public String getMetamodelID() {
		return metamodelID;
	}
}
