package com.aljoschability.eclipse.grapadeto.diagram.ui;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;

import com.aljoschability.eclipse.grapadeto.Pattern;
import com.aljoschability.eclipse.grapadeto.diagram.editor.PatternsDiagramEditor;
import com.aljoschability.eclipse.grapadeto.diagram.editor.provider.DiagramTypeProvider;

public final class StoryEditorPageContainer {

	private final TransactionalEditingDomain domain;
	private final Pattern element;

	private DiagramEditor editor;
	private DiagramEditorInput input;

	public StoryEditorPageContainer(PatternsDiagramEditor editor, Pattern element) {
		this.domain = editor.getEditingDomain();
		this.element = element;
	}

	public DiagramEditor getEditor() {
		if (editor == null) {
			editor = new DiagramEditor();
		}

		return editor;
	}

	public DiagramEditorInput getInput() {
		if (input == null) {
			input = DiagramEditorInput.createEditorInput(getDiagram(), DiagramTypeProvider.ID);
		}

		return input;
	}

	public Diagram getDiagram() {
		final Diagram diagram = Graphiti.getCreateService().createDiagram(DiagramTypeProvider.ID, "test", true);

		PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
		link.getBusinessObjects().add(element);

		diagram.setLink(link);

		Command command = new AbstractCommand() {
			@Override
			protected boolean prepare() {
				return true;
			}

			@Override
			public void redo() {
				element.eResource().getContents().add(diagram);
			}

			@Override
			public void undo() {
				element.eResource().getContents().remove(diagram);
			}

			@Override
			public void execute() {
				redo();
			}
		};

		domain.getCommandStack().execute(command);

		return diagram;
	}
}
