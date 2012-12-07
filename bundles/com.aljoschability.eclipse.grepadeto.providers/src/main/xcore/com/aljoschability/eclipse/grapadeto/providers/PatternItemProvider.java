/**
 * Copyright 2013 by Aljoschability and others. All rights reserved. This program and its materials are made available
 * under the terms of the Eclipse Public License v1.0 which is referenced in this distribution.
 * 
 * 	Contributors:
 * 		Aljoscha Hark <aljoschability@gmail.com> - Initial code
 */
package com.aljoschability.eclipse.grapadeto.providers;


import com.aljoschability.eclipse.grapadeto.GrapadetoFactory;
import com.aljoschability.eclipse.grapadeto.GrapadetoPackage;
import com.aljoschability.eclipse.grapadeto.Pattern;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.aljoschability.eclipse.grapadeto.Pattern} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PatternItemProvider
  extends CommentableItemProvider
  implements
    IEditingDomainItemProvider,
    IStructuredItemContentProvider,
    ITreeItemContentProvider,
    IItemLabelProvider,
    IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PatternItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addAbstractPropertyDescriptor(object);
      addCatalogPropertyDescriptor(object);
      addTypeIdPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Abstract feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAbstractPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Pattern_abstract_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_Pattern_abstract_feature", "_UI_Pattern_type"),
         GrapadetoPackage.Literals.PATTERN__ABSTRACT,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Catalog feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCatalogPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Pattern_catalog_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_Pattern_catalog_feature", "_UI_Pattern_type"),
         GrapadetoPackage.Literals.PATTERN__CATALOG,
         true,
         false,
         true,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Type Id feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTypeIdPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Pattern_typeId_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_Pattern_typeId_feature", "_UI_Pattern_type"),
         GrapadetoPackage.Literals.PATTERN__TYPE_ID,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(GrapadetoPackage.Literals.PATTERN__PATTERNS);
      childrenFeatures.add(GrapadetoPackage.Literals.PATTERN__OBJECTS);
      childrenFeatures.add(GrapadetoPackage.Literals.PATTERN__FRAGMENTS);
      childrenFeatures.add(GrapadetoPackage.Literals.PATTERN__CONSTRAINTS);
      childrenFeatures.add(GrapadetoPackage.Literals.PATTERN__LINKS);
      childrenFeatures.add(GrapadetoPackage.Literals.PATTERN__PATHS);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getText(Object object)
  {
    String label = ((Pattern)object).getName();
    return label == null || label.length() == 0 ?
      getString("_UI_Pattern_type") :
      getString("_UI_Pattern_type") + " " + label;
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(Pattern.class))
    {
      case GrapadetoPackage.PATTERN__ABSTRACT:
      case GrapadetoPackage.PATTERN__TYPE_ID:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case GrapadetoPackage.PATTERN__PATTERNS:
      case GrapadetoPackage.PATTERN__OBJECTS:
      case GrapadetoPackage.PATTERN__FRAGMENTS:
      case GrapadetoPackage.PATTERN__CONSTRAINTS:
      case GrapadetoPackage.PATTERN__LINKS:
      case GrapadetoPackage.PATTERN__PATHS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    newChildDescriptors.add
      (createChildParameter
        (GrapadetoPackage.Literals.PATTERN__PATTERNS,
         GrapadetoFactory.eINSTANCE.createPatternOccurrence()));

    newChildDescriptors.add
      (createChildParameter
        (GrapadetoPackage.Literals.PATTERN__OBJECTS,
         GrapadetoFactory.eINSTANCE.createObjectOccurrence()));

    newChildDescriptors.add
      (createChildParameter
        (GrapadetoPackage.Literals.PATTERN__FRAGMENTS,
         GrapadetoFactory.eINSTANCE.createFragment()));

    newChildDescriptors.add
      (createChildParameter
        (GrapadetoPackage.Literals.PATTERN__CONSTRAINTS,
         GrapadetoFactory.eINSTANCE.createConstraintExpression()));

    newChildDescriptors.add
      (createChildParameter
        (GrapadetoPackage.Literals.PATTERN__LINKS,
         GrapadetoFactory.eINSTANCE.createLink()));

    newChildDescriptors.add
      (createChildParameter
        (GrapadetoPackage.Literals.PATTERN__PATHS,
         GrapadetoFactory.eINSTANCE.createPath()));
  }

}
