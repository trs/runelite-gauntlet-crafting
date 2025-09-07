package com.trs.service;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.trs.GauntletPluginConfig;
import com.trs.entity.CraftableItem;
import com.trs.model.ItemTier;
import com.trs.model.ItemCategory;
import com.trs.component.ItemMaterialComponent;
import com.trs.entity.ResourceItem;

@Singleton
public class ResourceCalculator {
  private final GauntletPluginConfig config;

  private final HashMap<ResourceItem, Integer> resources;

  @Inject
  public ResourceCalculator(GauntletPluginConfig config) {
    this.config = config;
    this.resources = new HashMap<>();
  }

  public void calculateResources() {
    addTieredCraftable(ItemCategory.HELM, config.craftingOptionHelm());
    addTieredCraftable(ItemCategory.BODY, config.craftingOptionBody());
    addTieredCraftable(ItemCategory.LEGS, config.craftingOptionLegs());
    addTieredCraftable(ItemCategory.MELEE, config.craftingOptionMelee());
    addTieredCraftable(ItemCategory.MAGIC, config.craftingOptionMagic());
    addTieredCraftable(ItemCategory.RANGED, config.craftingOptionRanged());

    addCountCraftable(ItemCategory.TELEPORT_CRYSTAL, config.craftingTeleportCount());
    addCountCraftable(ItemCategory.VIAL, config.craftingPotionCount());
    addCountCraftable(ItemCategory.FOOD, config.craftingPaddlefishCount());
    addCountCraftable(ItemCategory.COMBO_FOOD, config.craftingCrystalPaddlefishCount());
    addCountCraftable(ItemCategory.ESCAPE_CRYSTAL, config.craftingEscapeCrystalCount());
  }

  public int getResourceCount(ResourceItem resource) {
    return resources.getOrDefault(resource, 0);
  }

  private void addCountCraftable(ItemCategory category, int count) {
    if (count == 0) return;
    for (CraftableItem craftable : CraftableItem.values()) {
      if (craftable.getCategory() == category) {
        addCraftableMaterials(craftable, count);
      }
    }
  }

  private void addTieredCraftable(ItemCategory category, ItemTier tierSetting) {
    if (tierSetting == ItemTier.NONE) return;
    for (CraftableItem craftable : CraftableItem.values()) {
      if (craftable.getCategory() == category && craftable.getTier().compareTo(tierSetting) <= 0) {
        addCraftableMaterials(craftable, 1);
      }
    }
  }

  private void addCraftableMaterials(CraftableItem craftable, int count) {
    var materials = craftable.getMaterials();
    for (ItemMaterialComponent material : materials) {
      var resource = material.resource;
      var quantity = material.quantity * count;

      resources.put(resource, resources.getOrDefault(resource, 0) + quantity);
    }
  }
}
