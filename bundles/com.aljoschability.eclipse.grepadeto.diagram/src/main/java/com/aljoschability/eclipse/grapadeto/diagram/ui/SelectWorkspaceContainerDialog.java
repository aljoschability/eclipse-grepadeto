package com.aljoschability.eclipse.grapadeto.diagram.ui;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class SelectWorkspaceContainerDialog extends ElementTreeSelectionDialog {
	public SelectWorkspaceContainerDialog(Shell parent) {
		super(parent, new WorkbenchLabelProvider(), new WorkbenchContentProvider());
		setAllowMultiple(false);
		setComparator(new ViewerComparator());
		setDoubleClickSelects(true);
		setEmptyListMessage("Nothing to list");
		setTitle("title");
		setInput(ResourcesPlugin.getWorkspace().getRoot());

		addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parent, Object element) {
				return element instanceof IContainer;
			}
		});
	}

	public String getPath() {
		return ((IContainer) super.getFirstResult()).getFullPath().toString();
	}
}
