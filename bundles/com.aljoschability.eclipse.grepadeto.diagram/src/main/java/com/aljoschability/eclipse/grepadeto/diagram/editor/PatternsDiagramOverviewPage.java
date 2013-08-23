package com.aljoschability.eclipse.grepadeto.diagram.editor;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.aljoschability.eclipse.grepadeto.Pattern;
import com.aljoschability.eclipse.grepadeto.diagram.ui.PatternsLabelProvider;
import com.aljoschability.eclipse.grepadeto.diagram.ui.PatternsTreeContentProvider;

public class PatternsDiagramOverviewPage extends FormPage {
	private TreeViewer contentsTreeViewer;
	private Button contentsOpenButton;
	private Button addPatternButton;
	private Button removePatternButton;

	/**
	 * Create the form page.
	 *
	 * @param editor
	 * @wbp.parser.constructor
	 */
	public PatternsDiagramOverviewPage(PatternsDiagramEditor editor) {
		super(editor, "overview", "Overview");
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("Story Diagram Editor");
		// form.setImage(DiagramImages.getImage(DiagramImages.EDITOR_HEADER));
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		ColumnLayout columnLayout = new ColumnLayout();
		columnLayout.maxNumColumns = 1;
		columnLayout.verticalSpacing = 12;
		columnLayout.leftMargin = 6;
		columnLayout.rightMargin = 6;
		columnLayout.topMargin = 12;
		managedForm.getForm().getBody().setLayout(columnLayout);

		Section contentsSection = managedForm.getToolkit().createSection(managedForm.getForm().getBody(),
				Section.EXPANDED | Section.DESCRIPTION | Section.TITLE_BAR);
		contentsSection.setDescription("This section shows all contained activities.");
		managedForm.getToolkit().paintBordersFor(contentsSection);
		contentsSection.setText("Activities");

		Composite contentsArea = managedForm.getToolkit().createComposite(contentsSection, SWT.NONE);
		managedForm.getToolkit().paintBordersFor(contentsArea);
		contentsSection.setClient(contentsArea);
		contentsArea.setLayout(new GridLayout(2, false));

		Tree contentsTree = managedForm.getToolkit().createTree(contentsArea, SWT.NONE);
		contentsTree.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().paintBordersFor(contentsTree);

		contentsTreeViewer = new TreeViewer(contentsTree);
		contentsTreeViewer.setContentProvider(new PatternsTreeContentProvider());
		contentsTreeViewer.setLabelProvider(new PatternsLabelProvider());
		contentsTreeViewer.setAutoExpandLevel(TreeViewer.ALL_LEVELS);

		contentsTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Object selected = ((IStructuredSelection) contentsTreeViewer.getSelection()).getFirstElement();

				if (selected instanceof Pattern) {
					System.out.println("selected " + ((Pattern) selected).getName());
				}
			}
		});

		Composite contentsButtonArea = managedForm.getToolkit().createComposite(contentsArea, SWT.NONE);
		contentsButtonArea.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		managedForm.getToolkit().paintBordersFor(contentsButtonArea);
		GridLayout gl_contentsButtonArea = new GridLayout(1, false);
		gl_contentsButtonArea.marginHeight = 0;
		gl_contentsButtonArea.marginWidth = 0;
		contentsButtonArea.setLayout(gl_contentsButtonArea);

		contentsOpenButton = managedForm.getToolkit().createButton(contentsButtonArea, "Open", SWT.NONE);
		contentsOpenButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		contentsOpenButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object selected = ((IStructuredSelection) contentsTreeViewer.getSelection()).getFirstElement();

				if (selected instanceof Pattern) {
					getEditor().open((Pattern) selected);
				}
			}
		});

		addPatternButton = managedForm.getToolkit().createButton(contentsButtonArea, "Add", SWT.NONE);
		addPatternButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getEditor().add();
			}
		});
		addPatternButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		removePatternButton = managedForm.getToolkit().createButton(contentsButtonArea, "Remove", SWT.NONE);
		removePatternButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object selected = ((IStructuredSelection) contentsTreeViewer.getSelection()).getFirstElement();

				if (selected instanceof Pattern) {
					getEditor().remove((Pattern) selected);
				}
			}
		});
		removePatternButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	}

	@Override
	public PatternsDiagramEditor getEditor() {
		return (PatternsDiagramEditor) super.getEditor();
	}

	@Override
	public void setActive(boolean active) {
		super.setActive(active);

		contentsTreeViewer.setInput(getEditor().getCatalog());
	}
}
