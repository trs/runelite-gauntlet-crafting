package com.trs.entity;

import com.trs.GauntletPluginConfig;
import com.trs.type.ICraftableItemComponent;
import com.trs.component.ItemTierComponent;
import com.trs.component.ItemIdentifierComponent;
import com.trs.component.ItemCategoryComponent;
import com.trs.component.ItemMaterialComponent;
import com.trs.component.QuantitySettingComponent;
import com.trs.component.CraftingIndexComponent;
import com.trs.component.TierSettingComponent;
import com.trs.model.CraftingIndex;
import com.trs.model.ItemCategory;
import com.trs.model.ItemTier;

import net.runelite.api.gameval.ItemID;

import java.util.ArrayList;
import java.util.List;
      
public enum CraftableItem {
  HELM_T1(
    new ItemTierComponent(ItemTier.BASIC, null),
    new ItemCategoryComponent(ItemCategory.HELM),
    new CraftingIndexComponent(CraftingIndex.HELM),
    new ItemIdentifierComponent(ItemID.GAUNTLET_HELMET_T1, ItemID.GAUNTLET_HELMET_T1_HM),
    new ItemMaterialComponent(ResourceItem.CRYSTAL_SHARD, 40),
    new ItemMaterialComponent(ResourceItem.ORE, 1),
    new ItemMaterialComponent(ResourceItem.BARK, 1),
    new ItemMaterialComponent(ResourceItem.FIBRE, 1),
    new TierSettingComponent(GauntletPluginConfig::craftingOptionHelm)
  ),
  HELM_T2(
    new ItemTierComponent(ItemTier.ATTUNED, HELM_T1),
    new ItemCategoryComponent(ItemCategory.HELM),
    new CraftingIndexComponent(CraftingIndex.HELM),
    new ItemIdentifierComponent(ItemID.GAUNTLET_HELMET_T2, ItemID.GAUNTLET_HELMET_T2_HM),
    new ItemMaterialComponent(ResourceItem.CRYSTAL_SHARD, 60),
    new ItemMaterialComponent(ResourceItem.ORE, 1),
    new ItemMaterialComponent(ResourceItem.BARK, 1),
    new ItemMaterialComponent(ResourceItem.FIBRE, 1),
    new TierSettingComponent(GauntletPluginConfig::craftingOptionHelm)
  ),
  HELM_T3(
    new ItemTierComponent(ItemTier.PERFECTED, HELM_T2),
    new ItemCategoryComponent(ItemCategory.HELM),
    new CraftingIndexComponent(CraftingIndex.HELM),
    new ItemIdentifierComponent(ItemID.GAUNTLET_HELMET_T3, ItemID.GAUNTLET_HELMET_T3_HM),
    new ItemMaterialComponent(ResourceItem.CRYSTAL_SHARD, 80),
    new ItemMaterialComponent(ResourceItem.ORE, 2),
    new ItemMaterialComponent(ResourceItem.BARK, 2),
    new ItemMaterialComponent(ResourceItem.FIBRE, 2),
    new TierSettingComponent(GauntletPluginConfig::craftingOptionHelm)
  );

  public ICraftableItemComponent[] components;

  CraftableItem(ICraftableItemComponent... components) {
    this.components = components;
  }

  private <T extends ICraftableItemComponent> T getComponent(Class<T> componentClass) {
    for (ICraftableItemComponent component : components) {
      if (componentClass.isInstance(component)) {
        return componentClass.cast(component);
      }
    }
    return null;
  }

  private <T extends ICraftableItemComponent> List<T> getComponents(Class<T> componentClass) {
    var matchingComponents = new ArrayList<T>();
    for (ICraftableItemComponent component : components) {
      if (componentClass.isInstance(component)) {
        matchingComponents.add(componentClass.cast(component));
      }
    }
    return matchingComponents;
  }

  public ItemCategory getCategory() {
    var component = getComponent(ItemCategoryComponent.class);
    if (component == null) return null;
    return ((ItemCategoryComponent) component).category;
  }

  public ItemTier getTier() {
    var component = getComponent(ItemTierComponent.class);
    if (component == null) return null;
    return ((ItemTierComponent) component).tier;
  }

  public CraftableItem getUpgradesFrom() {
    var component = getComponent(ItemTierComponent.class);
    if (component == null) return null;
    return ((ItemTierComponent) component).upgradesFrom;
  }

  public ItemTier getTierSetting(GauntletPluginConfig config) {
    var component = getComponent(TierSettingComponent.class);
    if (component == null) return null;
    return ((TierSettingComponent) component).getTier(config);
  }

  public int getQuantitySetting(GauntletPluginConfig config) {
    var component = getComponent(QuantitySettingComponent.class);
    if (component == null) return 0;
    return ((QuantitySettingComponent) component).getQuantity(config);
  }

  public int[] getItemIDs() {
    var component = getComponent(ItemIdentifierComponent.class);
    if (component == null) return null;
    return ((ItemIdentifierComponent) component).itemIDs;
  }

  public boolean hasItemID(int itemID) {
    var ids = getItemIDs();
    if (ids == null) return false;

    for (int id : ids) {
      if (id == itemID) return true;
    }
    return false;
  }

  public CraftingIndex getIndex() {
    var component = getComponent(CraftingIndexComponent.class);
    if (component == null) return null;
    return ((CraftingIndexComponent) component).index;
  }

  public List<ItemMaterialComponent> getMaterials() {
    return getComponents(ItemMaterialComponent.class);
  }
}


/*  HELM_T1(
    new CraftableBuilder()
      .setItemType(ItemCategory.HELM)
      .setItemTier(ItemTier.BASIC)
      .setItemIDs(new int[]{ItemID.GAUNTLET_HELMET_T1, ItemID.GAUNTLET_HELMET_T1_HM})
      .addMaterials(ResourceItem.SHARD, 40)
      .addMaterials(ResourceItem.ORE, 1)
      .addMaterials(ResourceItem.BARK, 1)
      .addMaterials(ResourceItem.FIBRE, 1)
      .build(),
    null
  ),
  HELM_T2(
    new CraftableBuilder()
      .setItemType(ItemCategory.HELM)
      .setItemTier(ItemTier.ATTUNED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_HELMET_T2, ItemID.GAUNTLET_HELMET_T2_HM})
      .addMaterials(ResourceItem.SHARD, 60)
      .addMaterials(ResourceItem.ORE, 1)
      .addMaterials(ResourceItem.BARK, 1)
      .addMaterials(ResourceItem.FIBRE, 1)
      .build(),
    HELM_T1
  ),
  HELM_T3(
    new CraftableBuilder()
      .setItemType(ItemCategory.HELM)
      .setItemTier(ItemTier.PERFECTED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_HELMET_T3, ItemID.GAUNTLET_HELMET_T3_HM})
      .addMaterials(ResourceItem.SHARD, 80)
      .addMaterials(ResourceItem.ORE, 2)
      .addMaterials(ResourceItem.BARK, 2)
      .addMaterials(ResourceItem.FIBRE, 2)
      .build(),
    HELM_T2
  ),
  BODY_T1(
    new CraftableBuilder()
      .setItemType(ItemCategory.BODY)
      .setItemTier(ItemTier.BASIC)
      .setItemIDs(new int[]{ItemID.GAUNTLET_CHESTPLATE_T1, ItemID.GAUNTLET_CHESTPLATE_T1_HM})
      .addMaterials(ResourceItem.SHARD, 40)
      .addMaterials(ResourceItem.ORE, 1)
      .addMaterials(ResourceItem.BARK, 1)
      .addMaterials(ResourceItem.FIBRE, 1)
      .build(),
    null
  ),
  BODY_T2(
    new CraftableBuilder()
      .setItemType(ItemCategory.BODY)
      .setItemTier(ItemTier.ATTUNED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_CHESTPLATE_T2, ItemID.GAUNTLET_CHESTPLATE_T2_HM})
      .addMaterials(ResourceItem.SHARD, 60)
      .addMaterials(ResourceItem.ORE, 1)
      .addMaterials(ResourceItem.BARK, 1)
      .addMaterials(ResourceItem.FIBRE, 1)
      .build(),
    BODY_T1
  ),
  BODY_T3(
    new CraftableBuilder()
      .setItemType(ItemCategory.BODY)
      .setItemTier(ItemTier.PERFECTED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_CHESTPLATE_T3, ItemID.GAUNTLET_CHESTPLATE_T3_HM})
      .addMaterials(ResourceItem.SHARD, 80)
      .addMaterials(ResourceItem.ORE, 2)
      .addMaterials(ResourceItem.BARK, 2)
      .addMaterials(ResourceItem.FIBRE, 2)
      .build(),
    BODY_T2
  ),
  LEGS_T1(
    new CraftableBuilder()
      .setItemType(ItemCategory.LEGS)
      .setItemTier(ItemTier.BASIC)
      .setItemIDs(new int[]{ItemID.GAUNTLET_PLATELEGS_T1, ItemID.GAUNTLET_PLATELEGS_T1_HM})
      .addMaterials(ResourceItem.SHARD, 40)
      .addMaterials(ResourceItem.ORE, 1)
      .addMaterials(ResourceItem.BARK, 1)
      .addMaterials(ResourceItem.FIBRE, 1)
      .build(),
    null
  ),
  LEGS_T2(
    new CraftableBuilder()
      .setItemType(ItemCategory.LEGS)
      .setItemTier(ItemTier.ATTUNED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_PLATELEGS_T2, ItemID.GAUNTLET_PLATELEGS_T2_HM})
      .addMaterials(ResourceItem.SHARD, 60)
      .addMaterials(ResourceItem.ORE, 1)
      .addMaterials(ResourceItem.BARK, 1)
      .addMaterials(ResourceItem.FIBRE, 1)
      .build(),
    LEGS_T1
  ),
  LEGS_T3(
    new CraftableBuilder()
      .setItemType(ItemCategory.LEGS)
      .setItemTier(ItemTier.PERFECTED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_PLATELEGS_T3, ItemID.GAUNTLET_PLATELEGS_T3_HM})
      .addMaterials(ResourceItem.SHARD, 80)
      .addMaterials(ResourceItem.ORE, 2)
      .addMaterials(ResourceItem.BARK, 2)
      .addMaterials(ResourceItem.FIBRE, 2)
      .build(),
    LEGS_T2
  ),
  MELEE_T1(
    new CraftableBuilder()
      .setItemType(ItemCategory.MELEE)
      .setItemTier(ItemTier.BASIC)
      .setItemIDs(new int[]{ItemID.GAUNTLET_MELEE_T1, ItemID.GAUNTLET_MELEE_T1_HM})
      .addMaterials(ResourceItem.SHARD, 40)
      .addMaterials(ResourceItem.WEAPON_FRAME, 1)
      .build(),
    null
  ),
  MELEE_T2(
    new CraftableBuilder()
      .setItemType(ItemCategory.MELEE)
      .setItemTier(ItemTier.ATTUNED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_MELEE_T2, ItemID.GAUNTLET_MELEE_T2_HM})
      .addMaterials(ResourceItem.SHARD, 60)
      .build(),
    MELEE_T1
  ),
  MELEE_T3(
    new CraftableBuilder()
      .setItemType(ItemCategory.MELEE)
      .setItemTier(ItemTier.PERFECTED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_MELEE_T3, ItemID.GAUNTLET_MELEE_T3_HM})
      .addMaterials(ResourceItem.SHARD, 80)
      .addMaterials(ResourceItem.MELEE_COMPONENT, 1)
      .build(),
    MELEE_T2
  ),
  RANGED_T1(
    new CraftableBuilder()
      .setItemType(ItemCategory.RANGED)
      .setItemTier(ItemTier.BASIC)
      .setItemIDs(new int[]{ItemID.GAUNTLET_RANGED_T1, ItemID.GAUNTLET_RANGED_T1_HM})
      .addMaterials(ResourceItem.SHARD, 40)
      .addMaterials(ResourceItem.WEAPON_FRAME, 1)
      .build(),
    null
  ),
  RANGED_T2(
    new CraftableBuilder()
      .setItemType(ItemCategory.RANGED)
      .setItemTier(ItemTier.ATTUNED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_RANGED_T2, ItemID.GAUNTLET_RANGED_T2_HM})
      .addMaterials(ResourceItem.SHARD, 60)
      .build(),
    RANGED_T1
  ),
  RANGED_T3(
    new CraftableBuilder()
      .setItemType(ItemCategory.RANGED)
      .setItemTier(ItemTier.PERFECTED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_RANGED_T3, ItemID.GAUNTLET_RANGED_T3_HM})
      .addMaterials(ResourceItem.SHARD, 80)
      .addMaterials(ResourceItem.RANGED_COMPONENT, 1)
      .build(),
    RANGED_T2
  ),
  MAGIC_T1(
    new CraftableBuilder()
      .setItemType(ItemCategory.MAGIC)
      .setItemTier(ItemTier.BASIC)
      .setItemIDs(new int[]{ItemID.GAUNTLET_MAGIC_T1, ItemID.GAUNTLET_MAGIC_T1_HM})
      .addMaterials(ResourceItem.SHARD, 40)
      .addMaterials(ResourceItem.WEAPON_FRAME, 1)
      .build(),
    null
  ),
  MAGIC_T2(
    new CraftableBuilder()
      .setItemType(ItemCategory.MAGIC)
      .setItemTier(ItemTier.ATTUNED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_MAGIC_T2, ItemID.GAUNTLET_MAGIC_T2_HM})
      .addMaterials(ResourceItem.SHARD, 60)
      .build(),
    MAGIC_T1
  ),
  MAGIC_T3(
    new CraftableBuilder()
      .setItemType(ItemCategory.MAGIC)
      .setItemTier(ItemTier.PERFECTED)
      .setItemIDs(new int[]{ItemID.GAUNTLET_MAGIC_T3, ItemID.GAUNTLET_MAGIC_T3_HM})
      .addMaterials(ResourceItem.SHARD, 80)
      .addMaterials(ResourceItem.MAGIC_COMPONENT, 1)
      .build(),
    MAGIC_T2
  ),
  TELEPORT_CRYSTAL(
    new CraftableBuilder()
      .setItemType(ItemCategory.TELEPORT_CRYSTAL)
      .setItemIDs(new int[]{ItemID.GAUNTLET_TELEPORT_CRYSTAL, ItemID.GAUNTLET_TELEPORT_CRYSTAL_HM})
      .addMaterials(ResourceItem.SHARD, 40)
      .build(),
    null
  ),
  VIAL(
    new CraftableBuilder()
      .setItemType(ItemCategory.VIAL)
      .setItemIDs(new int[]{ItemID.GAUNTLET_VIAL_EMPTY, ItemID.GAUNTLET_VIAL_WATER, ItemID.GAUNTLET_POTION_UNFINISHED, ItemID.GAUNTLET_POTION_1, ItemID.GAUNTLET_POTION_2, ItemID.GAUNTLET_POTION_3, ItemID.GAUNTLET_POTION_4})
      .addMaterials(ResourceItem.SHARD, 10)
      .build(),
    null
  ),
  COMBO_FOOD(
    new CraftableBuilder()
      .setItemType(ItemCategory.COMBO_FOOD)
      .setItemIDs(new int[]{ItemID.GAUNTLET_COMBO_FOOD, ItemID.GAUNTLET_COMBO_FOOD_HM})
      .addMaterials(ResourceItem.SHARD, 10)
      .addMaterials(ResourceItem.FOOD, 1)
      .build(),
    null
  ),
  ESCAPE_CRYSTAL(
    new CraftableBuilder()
      .setItemType(ItemCategory.ESCAPE_CRYSTAL)
      .setItemIDs(new int[]{ItemID.GAUNTLET_ESCAPE_CRYSTAL, ItemID.GAUNTLET_ESCAPE_CRYSTAL_HM})
      .addMaterials(ResourceItem.SHARD, 200)
      .build(),
    null
  ); */