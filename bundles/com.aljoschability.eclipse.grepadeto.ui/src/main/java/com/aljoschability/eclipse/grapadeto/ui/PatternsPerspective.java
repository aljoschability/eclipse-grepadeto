package com.aljoschability.eclipse.grapadeto.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PatternsPerspective implements IPerspectiveFactory {
	private static final String EDITOR = IPageLayout.ID_EDITOR_AREA;
	private static final String PROJECT_EXPLORER = "org.eclipse.ui.navigator.ProjectExplorer"; //$NON-NLS-1$
	private static final String OUTLINE = "org.eclipse.ui.views.ContentOutline"; //$NON-NLS-1$
	private static final String PROPERTY_SHEET = "org.eclipse.ui.views.PropertySheet"; //$NON-NLS-1$
	private static final String CONSOLE = "org.eclipse.ui.console.ConsoleView"; //$NON-NLS-1$

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.addView(PROJECT_EXPLORER, IPageLayout.LEFT, 0.3f, EDITOR);
		layout.addView(OUTLINE, IPageLayout.BOTTOM, 0.57f, PROJECT_EXPLORER);
		layout.addView(PROPERTY_SHEET, IPageLayout.BOTTOM, 0.5f, EDITOR);
		layout.addView(CONSOLE, IPageLayout.RIGHT, 0.5f, PROPERTY_SHEET);
	}
}
