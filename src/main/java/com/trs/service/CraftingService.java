package com.trs.service;

import lombok.extern.slf4j.Slf4j;
import javax.inject.Inject;
import javax.inject.Singleton;

import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;

import com.trs.model.CraftingTieredItem;
import com.trs.model.CraftingCountItem;
import com.trs.model.CraftingState;
import com.trs.model.CraftingSetting;
import com.trs.GauntletPluginConfig;

@Slf4j
@Singleton
public class CraftingService {

  private final Client client;
  private final GauntletPluginConfig config;

  @Inject
  public CraftingService(GauntletPluginConfig config, Client client) {
    this.client = client;
    this.config = config;
  }

  public CraftingState getCraftingState(int index) {
    CraftingTieredItem craftingItem = CraftingTieredItem.fromIndex(index);

    if (craftingItem != null) {
      return getTieredCraftingState(craftingItem);
    } 
    
    CraftingCountItem craftingCountItem = CraftingCountItem.fromIndex(index);
    if (craftingCountItem != null) {
      return getCountCraftingState(craftingCountItem);
    }

    return CraftingState.SKIP;
  }

  private CraftingState getTieredCraftingState(CraftingTieredItem craftingItem) {
    CraftingSetting craftingSetting = craftingItem.getCraftingSetting(config);
    
    if (craftingSetting == CraftingSetting.NONE) {
      return CraftingState.COMPLETE;
    }

    boolean hasTier3 = hasAnyItem(craftingItem.getPerfectedTierItems());
    boolean hasTier2 = hasTier3 || hasAnyItem(craftingItem.getAttunedTierItems());
    boolean hasTier1 = hasTier2 || hasAnyItem(craftingItem.getBasicTierItems());

    switch (craftingSetting) {
      case BASIC:
        return hasTier1 ? CraftingState.COMPLETE : CraftingState.INCOMPLETE;
      case ATTUNED:
        return hasTier2 ? CraftingState.COMPLETE : CraftingState.INCOMPLETE;
      case PERFECTED:
        return hasTier3 ? CraftingState.COMPLETE : CraftingState.INCOMPLETE;
      default:
        return CraftingState.COMPLETE;
    }
  }

  private CraftingState getCountCraftingState(CraftingCountItem craftingItem) {
    int craftingCountTarget = craftingItem.getCraftingSetting(config);

    int count = 0;
    ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
    if (inventory != null) {
      for (var item : inventory.getItems()) {
        if (craftingItem.hasItemID(item.getId())) {
          count++;
        }
      }
    }

    if (count >= craftingCountTarget) {
      return CraftingState.COMPLETE;
    }
    return CraftingState.INCOMPLETE;
  }

  private boolean hasAnyItem(int[] itemIDs) {
    ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);
    ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);

    for (int itemID : itemIDs) {
      if (equipment != null && equipment.contains(itemID)) return true;
      if (inventory != null && inventory.contains(itemID)) return true;
    }
    return false;
  }
}
