package com.aljoschability.eclipse.grepadeto.diagram.editor.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.swt.graphics.Image;

import com.aljoschability.eclipse.grepadeto.diagram.Images;

public class ImageProvider extends AbstractImageProvider implements Images {
	public static Image getImage(String key) {
		return GraphitiUi.getImageService().getImageForId(DiagramTypeProvider.ID, key);
	}

	@Override
	protected void addAvailableImages() {
		// palette categories
		add(PALETTE_ACTIVITIES);
		add(PALETTE_PATTERNS);

		// ecore package
		add(EANNOTATION);
		add(EATTRIBUTE);
		add(ECLASS);
		add(EDATA_TYPE);
		add(EENUM);
		add(EENUM_LITERAL);
		add(EFACTORY);
		add(EOPERATION);
		add(EPACKAGE);
		add(EPACKAGE_LINKED);
		add(EPACKAGE_PLUGIN);
		add(EPACKAGE_WORKSPACE);
		add(EPARAMETER);
		add(EREFERENCE);
		add(ESTRING_TO_STRING_MAP_ENTRY);

		// patterns package
	}

	private void add(String path) {
		addImageFilePath(path, path);
	}
}
