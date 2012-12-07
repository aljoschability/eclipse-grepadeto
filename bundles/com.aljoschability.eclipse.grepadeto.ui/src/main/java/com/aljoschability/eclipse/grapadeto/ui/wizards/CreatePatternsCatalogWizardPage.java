package com.aljoschability.eclipse.grapadeto.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CreatePatternsCatalogWizardPage extends WizardPage {
	private static final String DEFAULT_FILE_NAME = "catalog";
	private static final String DEFAULT_FILE_EXTENSION = "patterns";

	private IContainer fileContainer;
	private String fileName;

	private Text fileContainerText;
	private Text fileNameText;

	protected CreatePatternsCatalogWizardPage() {
		super(CreatePatternsCatalogWizardPage.class.getName());

		setDescription("Create a XXXXXX");
		setTitle("Create Patterns Diagram");

		fileContainer = ResourcesPlugin.getWorkspace().getRoot();
		fileName = DEFAULT_FILE_NAME + "." + DEFAULT_FILE_EXTENSION;
	}

	protected void setContainer(IContainer container) {
		this.fileContainer = container;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblContainer = new Label(composite, SWT.NONE);
		lblContainer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContainer.setText("Container:");

		fileContainerText = new Text(composite, SWT.BORDER);
		fileContainerText.setText(fileContainer.getFullPath().toString());
		fileContainerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblFileName = new Label(composite, SWT.NONE);
		lblFileName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFileName.setText("File Name:");

		fileNameText = new Text(composite, SWT.BORDER);
		fileNameText.setText(fileName);
		fileNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		hookListeners();

		setControl(composite);
	}

	private void hookListeners() {
		fileContainerText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				IResource resource = root.findMember(fileContainerText.getText());

				if (resource instanceof IContainer) {
					fileContainer = (IContainer) resource;
				}
				setPageComplete(isValid());
			}
		});

		fileNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				fileName = fileNameText.getText();
				setPageComplete(isValid());
			}
		});
	}

	protected boolean isValid() {
		if (fileContainer == null || !fileContainer.isAccessible()) {
			setErrorMessage("Container wrong!");
			return false;
		}

		if (fileName == null || fileName.isEmpty()) {
			setErrorMessage("Provide a file name!");
			return false;
		}

		IResource existing = fileContainer.findMember(fileName);
		if (existing instanceof IFile && existing.isAccessible()) {
			setMessage("The file will be overwritten!", WARNING);
			return true;
		}

		setMessage(null);
		setErrorMessage(null);
		return true;
	}

	public String getModelPath() {
		IPath path = fileContainer.getFullPath().append(fileName);
		if (!path.getFileExtension().equalsIgnoreCase(DEFAULT_FILE_EXTENSION)) {
			path.addFileExtension(DEFAULT_FILE_EXTENSION);
		}

		return path.toString();
	}

	public String getDiagramPath() {
		return getModelPath() + ".diagrams";
	}
}
