package com.trs.entity;

import com.trs.type.IComponent;
import com.trs.component.ObjectIDComponent;
import com.trs.component.EntityPointerComponent;
import com.trs.type.IEntity;

import net.runelite.api.gameval.ObjectID;

public enum ResourceEntity implements IEntity {
  ROCK(
    new ObjectIDComponent(ObjectID.GAUNTLET_ROCK, ObjectID.GAUNTLET_ROCK_HM),
    new EntityPointerComponent(ItemEntity.ORE)
  ),
  TREE(
    new ObjectIDComponent(ObjectID.GAUNTLET_TREE, ObjectID.GAUNTLET_TREE_HM),
    new EntityPointerComponent(ItemEntity.BARK)
  ),
  FIBRE(
    new ObjectIDComponent(ObjectID.GAUNTLET_FIBRE, ObjectID.GAUNTLET_FIBRE_HM),
    new EntityPointerComponent(ItemEntity.FIBRE)
  ),
  HERB(
    new ObjectIDComponent(ObjectID.GAUNTLET_HERB, ObjectID.GAUNTLET_HERB_HM),
    new EntityPointerComponent(ItemEntity.HERB)
  ),
  POND(
    new ObjectIDComponent(ObjectID.GAUNTLET_POND, ObjectID.GAUNTLET_POND_HM),
    new EntityPointerComponent(ItemEntity.FISH)
  );
  
  private final IComponent[] components;

  ResourceEntity(IComponent... components) {
    this.components = components;
  }

  @Override
  public IComponent[] getComponents() {
    return components;
  }
}
