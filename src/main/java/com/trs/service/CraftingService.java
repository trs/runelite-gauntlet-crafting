package com.trs.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.trs.GauntletPluginConfig;
import com.trs.entity.CraftableItem;
import com.trs.model.CraftingState;
import com.trs.model.CraftingIndex;
import com.trs.model.ItemTier;

import net.runelite.api.Client;
import net.runelite.api.ItemContainer;
import net.runelite.api.gameval.InventoryID;

@Singleton
public class CraftingService {
  private final Client client;
  private final GauntletPluginConfig config;

  @Inject
  public CraftingService(Client client, GauntletPluginConfig config) {
    this.client = client;
    this.config = config;
  }

  public CraftingState getCraftingState(CraftingIndex index) {
    var craftable = getCraftableItemsByIndex(index);
    if (craftable == null) return CraftingState.SKIP;

    if (craftable.getTier() != null) {
      return getTieredCraftingState(craftable);
    }
    
    return getCountCraftingState(craftable);
  }

  private CraftingState getTieredCraftingState(CraftableItem craftable) {
    var tierSetting = craftable.getTierSetting(config);

    if (tierSetting == ItemTier.NONE) return CraftingState.SKIP;

    if (tierSetting == ItemTier.BASIC) {
      if (hasCraftable(craftable)) return CraftingState.CRAFTED;
      if (hasMaterials(craftable)) return CraftingState.TO_CRAFT;
      return CraftingState.NOT_ENOUGH_MATERIALS;
    }

    if (tierSetting == ItemTier.ATTUNED) {
      if (hasCraftable(craftable)) return CraftingState.CRAFTED;
      if (hasMaterials(craftable) && hasCraftable(craftable.getUpgradesFrom())) return CraftingState.TO_CRAFT;

      // Check basic tier
      var basicCraftable = craftable.getUpgradesFrom();
      if (!hasCraftable(basicCraftable) && hasMaterials(basicCraftable)) return CraftingState.TO_CRAFT;
      return CraftingState.NOT_ENOUGH_MATERIALS;
    }

    if (tierSetting == ItemTier.PERFECTED) {
      if (hasCraftable(craftable)) return CraftingState.CRAFTED;
      if (hasMaterials(craftable) && hasCraftable(craftable.getUpgradesFrom())) return CraftingState.TO_CRAFT;

      // Check attuned tier
      var attunedCraftable = craftable.getUpgradesFrom();
      if (!hasCraftable(attunedCraftable) && hasMaterials(attunedCraftable) && hasCraftable(attunedCraftable.getUpgradesFrom())) return CraftingState.TO_CRAFT;

      // Check basic tier
      var basicCraftable = attunedCraftable.getUpgradesFrom();
      if (!hasCraftable(basicCraftable) && hasMaterials(basicCraftable)) return CraftingState.TO_CRAFT;
      return CraftingState.NOT_ENOUGH_MATERIALS;
    }

    return CraftingState.SKIP;
  }

  private CraftingState getCountCraftingState(CraftableItem craftable) {
    int craftingCountTarget = craftable.getQuantitySetting(config);

    int count = 0;
    ItemContainer inventory = client.getItemContainer(InventoryID.INV);
    if (inventory != null) {
      for (var item : inventory.getItems()) {
        if (craftable.hasItemID(item.getId())) {
          count += item.getQuantity();
        }
      }
    }

    if (count >= craftingCountTarget) {
      return CraftingState.CRAFTED;
    }

    if (!hasMaterials(craftable)) {
      return CraftingState.NOT_ENOUGH_MATERIALS;
    }

    return CraftingState.TO_CRAFT;
  }

  private CraftableItem getCraftableItemsByIndex(CraftingIndex index) {
    for (CraftableItem craftable : CraftableItem.values()) {
      if (craftable.getIndex().equals(index) && (craftable.getTier() == null || craftable.getTier() == craftable.getTierSetting(config))) {
        return craftable;
      }
    }
    return null;
  }

  private boolean hasCraftable(CraftableItem craftable) {
    if (craftable == null) return false;

    ItemContainer inventory = client.getItemContainer(InventoryID.INV);
    ItemContainer equipment = client.getItemContainer(InventoryID.WORN);

    for (int itemID : craftable.getItemIDs()) {
      if (inventory != null && inventory.contains(itemID)) return true;
      if (equipment != null && equipment.contains(itemID)) return true;
    }
    return false;
  }

  private boolean hasMaterials(CraftableItem craftable) {
    ItemContainer inventory = client.getItemContainer(InventoryID.INV);
    if (inventory == null) return false;

    for (var material : craftable.getMaterials()) {
      int quantity = 0;
      for (var item : inventory.getItems()) {
        if (material.resource.hasResourceIdentifier(item.getId())) {
          quantity += item.getQuantity();
        }
      }
      if (quantity < material.quantity) {
        return false;
      }
    }
    return true;
  }
}
