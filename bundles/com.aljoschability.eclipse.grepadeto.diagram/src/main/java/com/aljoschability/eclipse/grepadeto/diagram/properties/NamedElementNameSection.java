package com.aljoschability.eclipse.grepadeto.diagram.properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import com.aljoschability.eclipse.grepadeto.Named;

public class NamedElementNameSection extends AbstractPropertySection {

	private Text name;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage page) {
		super.createControls(parent, page);

		// get factory
		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();

		// create area
		Composite area = factory.createFlatFormComposite(parent);

		FormData data;

		name = factory.createText(area, ""); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, VSPACE);
		name.setLayoutData(data);

		CLabel label = factory.createCLabel(area, "Name:");
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(name, -HSPACE);
		data.top = new FormAttachment(name, 0, SWT.CENTER);
		label.setLayoutData(data);
	}

	@Override
	public void refresh() {
		super.refresh();

		String value = getElement().getName();
		if (value == null) {
			value = ""; //$NON-NLS-1$
		}

		name.setText(value);
	}

	@Override
	protected Named getElement() {
		return (Named) super.getElement();
	}
}
