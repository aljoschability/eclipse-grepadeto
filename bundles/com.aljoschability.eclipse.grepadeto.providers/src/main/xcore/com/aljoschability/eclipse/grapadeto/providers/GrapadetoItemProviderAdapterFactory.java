/**
 * Copyright 2013 by Aljoschability and others. All rights reserved. This program and its materials are made available
 * under the terms of the Eclipse Public License v1.0 which is referenced in this distribution.
 * 
 * 	Contributors:
 * 		Aljoscha Hark <aljoschability@gmail.com> - Initial code
 */
package com.aljoschability.eclipse.grapadeto.providers;

import com.aljoschability.eclipse.grapadeto.util.GrapadetoAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GrapadetoItemProviderAdapterFactory extends GrapadetoAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable
{
  /**
   * This keeps track of the root adapter factory that delegates to this adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection<Object> supportedTypes = new ArrayList<Object>();

  /**
   * This constructs an instance.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GrapadetoItemProviderAdapterFactory()
  {
    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(IItemPropertySource.class);
  }

  /**
   * This keeps track of the one adapter used for all {@link com.aljoschability.eclipse.grapadeto.BooleanConstraint} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BooleanConstraintItemProvider booleanConstraintItemProvider;

  /**
   * This creates an adapter for a {@link com.aljoschability.eclipse.grapadeto.BooleanConstraint}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createBooleanConstraintAdapter()
  {
    if (booleanConstraintItemProvider == null)
    {
      booleanConstraintItemProvider = new BooleanConstraintItemProvider(this);
    }

    return booleanConstraintItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link com.aljoschability.eclipse.grapadeto.Catalog} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CatalogItemProvider catalogItemProvider;

  /**
   * This creates an adapter for a {@link com.aljoschability.eclipse.grapadeto.Catalog}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createCatalogAdapter()
  {
    if (catalogItemProvider == null)
    {
      catalogItemProvider = new CatalogItemProvider(this);
    }

    return catalogItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link com.aljoschability.eclipse.grapadeto.Pattern} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PatternItemProvider patternItemProvider;

  /**
   * This creates an adapter for a {@link com.aljoschability.eclipse.grapadeto.Pattern}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createPatternAdapter()
  {
    if (patternItemProvider == null)
    {
      patternItemProvider = new PatternItemProvider(this);
    }

    return patternItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link com.aljoschability.eclipse.grapadeto.ConstraintExpression} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConstraintExpressionItemProvider constraintExpressionItemProvider;

  /**
   * This creates an adapter for a {@link com.aljoschability.eclipse.grapadeto.ConstraintExpression}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createConstraintExpressionAdapter()
  {
    if (constraintExpressionItemProvider == null)
    {
      constraintExpressionItemProvider = new ConstraintExpressionItemProvider(this);
    }

    return constraintExpressionItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link com.aljoschability.eclipse.grapadeto.Link} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LinkItemProvider linkItemProvider;

  /**
   * This creates an adapter for a {@link com.aljoschability.eclipse.grapadeto.Link}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createLinkAdapter()
  {
    if (linkItemProvider == null)
    {
      linkItemProvider = new LinkItemProvider(this);
    }

    return linkItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link com.aljoschability.eclipse.grapadeto.AttributeConstraint} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AttributeConstraintItemProvider attributeConstraintItemProvider;

  /**
   * This creates an adapter for a {@link com.aljoschability.eclipse.grapadeto.AttributeConstraint}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createAttributeConstraintAdapter()
  {
    if (attributeConstraintItemProvider == null)
    {
      attributeConstraintItemProvider = new AttributeConstraintItemProvider(this);
    }

    return attributeConstraintItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link com.aljoschability.eclipse.grapadeto.Path} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PathItemProvider pathItemProvider;

  /**
   * This creates an adapter for a {@link com.aljoschability.eclipse.grapadeto.Path}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createPathAdapter()
  {
    if (pathItemProvider == null)
    {
      pathItemProvider = new PathItemProvider(this);
    }

    return pathItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link com.aljoschability.eclipse.grapadeto.Fragment} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FragmentItemProvider fragmentItemProvider;

  /**
   * This creates an adapter for a {@link com.aljoschability.eclipse.grapadeto.Fragment}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createFragmentAdapter()
  {
    if (fragmentItemProvider == null)
    {
      fragmentItemProvider = new FragmentItemProvider(this);
    }

    return fragmentItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link com.aljoschability.eclipse.grapadeto.PatternOccurrence} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PatternOccurrenceItemProvider patternOccurrenceItemProvider;

  /**
   * This creates an adapter for a {@link com.aljoschability.eclipse.grapadeto.PatternOccurrence}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createPatternOccurrenceAdapter()
  {
    if (patternOccurrenceItemProvider == null)
    {
      patternOccurrenceItemProvider = new PatternOccurrenceItemProvider(this);
    }

    return patternOccurrenceItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link com.aljoschability.eclipse.grapadeto.ObjectOccurrence} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ObjectOccurrenceItemProvider objectOccurrenceItemProvider;

  /**
   * This creates an adapter for a {@link com.aljoschability.eclipse.grapadeto.ObjectOccurrence}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createObjectOccurrenceAdapter()
  {
    if (objectOccurrenceItemProvider == null)
    {
      objectOccurrenceItemProvider = new ObjectOccurrenceItemProvider(this);
    }

    return objectOccurrenceItemProvider;
  }

  /**
   * This returns the root adapter factory that contains this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

  /**
   * This sets the composed adapter factory that contains this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
    this.parentAdapterFactory = parentAdapterFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object type)
  {
    return supportedTypes.contains(type) || super.isFactoryForType(type);
  }

  /**
   * This implementation substitutes the factory itself as the key for the adapter.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter adapt(Notifier notifier, Object type)
  {
    return super.adapt(notifier, this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object adapt(Object object, Object type)
  {
    if (isFactoryForType(type))
    {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter)))
      {
        return adapter;
      }
    }

    return null;
  }

  /**
   * This adds a listener.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.addListener(notifyChangedListener);
  }

  /**
   * This removes a listener.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.removeListener(notifyChangedListener);
  }

  /**
   * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void fireNotifyChanged(Notification notification)
  {
    changeNotifier.fireNotifyChanged(notification);

    if (parentAdapterFactory != null)
    {
      parentAdapterFactory.fireNotifyChanged(notification);
    }
  }

  /**
   * This disposes all of the item providers created by this factory. 
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void dispose()
  {
    if (booleanConstraintItemProvider != null) booleanConstraintItemProvider.dispose();
    if (catalogItemProvider != null) catalogItemProvider.dispose();
    if (patternItemProvider != null) patternItemProvider.dispose();
    if (constraintExpressionItemProvider != null) constraintExpressionItemProvider.dispose();
    if (linkItemProvider != null) linkItemProvider.dispose();
    if (attributeConstraintItemProvider != null) attributeConstraintItemProvider.dispose();
    if (pathItemProvider != null) pathItemProvider.dispose();
    if (fragmentItemProvider != null) fragmentItemProvider.dispose();
    if (patternOccurrenceItemProvider != null) patternOccurrenceItemProvider.dispose();
    if (objectOccurrenceItemProvider != null) objectOccurrenceItemProvider.dispose();
  }

}