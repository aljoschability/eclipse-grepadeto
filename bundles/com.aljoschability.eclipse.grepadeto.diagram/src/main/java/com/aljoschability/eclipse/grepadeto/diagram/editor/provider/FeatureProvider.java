package com.aljoschability.eclipse.grepadeto.diagram.editor.provider;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.eclipse.swt.widgets.Link;

import com.aljoschability.eclipse.grepadeto.Connection;
import com.aljoschability.eclipse.grepadeto.Constraint;
import com.aljoschability.eclipse.grepadeto.ConstraintExpression;
import com.aljoschability.eclipse.grepadeto.Fragment;
import com.aljoschability.eclipse.grepadeto.Fragmented;
import com.aljoschability.eclipse.grepadeto.Node;
import com.aljoschability.eclipse.grepadeto.ObjectOccurrence;
import com.aljoschability.eclipse.grepadeto.Path;
import com.aljoschability.eclipse.grepadeto.PatternOccurrence;
import com.aljoschability.eclipse.grepadeto.Weighted;
import com.aljoschability.eclipse.grepadeto.diagram.features.NoResizeFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.add.AddConstraintExpressionFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.add.AddConstraintFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.add.AddFragmentFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.add.AddLinkFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.add.AddPathFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.add.AddPatternAnnotationFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.add.AddPatternObjectFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.create.CreateLinkFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.create.CreatePathFeature;
import com.aljoschability.eclipse.grepadeto.diagram.features.layout.LayoutPatternObjectFeature;

public class FeatureProvider extends DefaultFeatureProvider {
	public FeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		Object element = getBusinessObjectForPictogramElement(context.getShape());
		if (element instanceof Constraint) {
			return new NoResizeFeature(this);
		}

		return super.getResizeShapeFeature(context);
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		Object element = context.getNewObject();

		if (element instanceof Connection) {
			if (element instanceof Link) {
				return new AddLinkFeature(this);
			}
			if (element instanceof Path) {
				return new AddPathFeature(this);
			}

			return null;
		}

		if (element instanceof Weighted) {
			if (element instanceof Constraint) {
				return new AddConstraintFeature(this);
			}

			if (element instanceof Fragmented) {
				if (element instanceof ConstraintExpression) {
					return new AddConstraintExpressionFeature(this);
				}
				if (element instanceof Fragment) {
					return new AddFragmentFeature(this);
				}
				if (element instanceof Node) {
					if (element instanceof ObjectOccurrence) {
						return new AddPatternObjectFeature(this);
					}

					if (element instanceof PatternOccurrence) {
						return new AddPatternAnnotationFeature(this);
					}

					return null;
				}
			}

			return null;
		}

		return null;
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] { new CreateLinkFeature(this), new CreatePathFeature(this) };
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		Object element = getBusinessObjectForPictogramElement(context.getPictogramElement());

		if (element instanceof ObjectOccurrence) {
			return new LayoutPatternObjectFeature(this);
		}

		return null;
	}
}
