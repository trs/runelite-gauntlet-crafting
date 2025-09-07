package com.trs.entity;

import com.trs.type.IResourceItemComponent;
import com.trs.component.ResourceIdentifierComponent;
import com.trs.component.ResourceObjectComponent;

import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.ObjectID;

public enum ResourceItem {
  ORE(
    new ResourceIdentifierComponent(ItemID.GAUNTLET_ORE, ItemID.GAUNTLET_ORE_HM),
    new ResourceObjectComponent(ObjectID.GAUNTLET_ROCK, ObjectID.GAUNTLET_ROCK_HM)
  ),
  BARK(
    new ResourceIdentifierComponent(ItemID.GAUNTLET_BARK, ItemID.GAUNTLET_BARK_HM),
    new ResourceObjectComponent(ObjectID.GAUNTLET_TREE, ObjectID.GAUNTLET_TREE_HM)
  ),
  FIBRE(
    new ResourceIdentifierComponent(ItemID.GAUNTLET_FIBRE, ItemID.GAUNTLET_FIBRE_HM),
    new ResourceObjectComponent(ObjectID.GAUNTLET_FIBRE, ObjectID.GAUNTLET_FIBRE_HM)
  ),
  FISH(
    new ResourceIdentifierComponent(ItemID.GAUNTLET_RAW_FOOD),
    new ResourceObjectComponent(ObjectID.GAUNTLET_POND)
  ),
  CRYSTAL_SHARD(
    new ResourceIdentifierComponent(ItemID.GAUNTLET_CRYSTAL_SHARD, ItemID.GAUNTLET_CRYSTAL_SHARD_HM)
  ),
  WEAPON_FRAME(
    new ResourceIdentifierComponent(ItemID.GAUNTLET_GENERIC_COMPONENT, ItemID.GAUNTLET_GENERIC_COMPONENT_HM)
  ),
  MAGIC_COMPONENT(
    new ResourceIdentifierComponent(ItemID.GAUNTLET_MAGIC_COMPONENT, ItemID.GAUNTLET_MAGIC_COMPONENT_HM)
  ),
  RANGED_COMPONENT(
    new ResourceIdentifierComponent(ItemID.GAUNTLET_RANGED_COMPONENT, ItemID.GAUNTLET_RANGED_COMPONENT_HM)
  ),
  MELEE_COMPONENT(
    new ResourceIdentifierComponent(ItemID.GAUNTLET_MELEE_COMPONENT, ItemID.GAUNTLET_MELEE_COMPONENT_HM)
  ),
  FOOD(
    new ResourceIdentifierComponent(ItemID.GAUNTLET_FOOD)
  );

  public IResourceItemComponent[] components;

  ResourceItem(IResourceItemComponent... components) {
    this.components = components;
  }

  private <T extends IResourceItemComponent> T getComponent(Class<T> componentClass) {
    for (IResourceItemComponent component : components) {
      if (componentClass.isInstance(component)) {
        return componentClass.cast(component);
      }
    }
    return null;
  }

  public boolean hasResourceIdentifier(int itemID) {
    var component = getComponent(ResourceIdentifierComponent.class);
    if (component == null) return false;

    for (int id : ((ResourceIdentifierComponent) component).itemIDs) {
      if (id == itemID) return true;
    }
    return false;
  }

  public int[] getObjectIDs() {
    var component = getComponent(ResourceObjectComponent.class);
    if (component == null) return null;
    return ((ResourceObjectComponent) component).objectIDs;
  }
}
