package com.trs2.service;

import lombok.extern.slf4j.Slf4j;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.trs2.GauntletPluginConfig;
import com.trs2.model.CraftingCountItem;
import com.trs2.model.CraftingMaterial;
import com.trs2.model.CraftingSetting;
import com.trs2.model.CraftingState;
import com.trs2.model.CraftingTieredItem;

import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;

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
    
    boolean hasTier3 = hasAnyItem(craftingItem.getPerfectedTierItemIDs());
    boolean hasTier2 = hasTier3 || hasAnyItem(craftingItem.getAttunedTierItemIDs());
    boolean hasTier1 = hasTier2 || hasAnyItem(craftingItem.getBasicTierItemIDs());

    if (craftingSetting == CraftingSetting.BASIC) {
      if (hasTier1) {
        return CraftingState.COMPLETE;
      } else {
        if (hasMaterials(craftingItem.getBasicTierMaterials())) {
          return CraftingState.INCOMPLETE;
        }
      }

      return CraftingState.MISSING_MATERIALS;
    }

    if (craftingSetting == CraftingSetting.ATTUNED) {
      if (hasTier2) {
        return CraftingState.COMPLETE;
      }
      else if (hasTier1) {
        if (hasMaterials(craftingItem.getAttunedTierMaterials())) {
          return CraftingState.INCOMPLETE;
        }
      }
      else {
        if (hasMaterials(craftingItem.getBasicTierMaterials())) {
          return CraftingState.INCOMPLETE;
        }
      }

      return CraftingState.MISSING_MATERIALS;
    }

    if (craftingSetting == CraftingSetting.PERFECTED) {
      if (hasTier3) {
        return CraftingState.COMPLETE;
      }
      else if (hasTier2) {
        if (hasMaterials(craftingItem.getPerfectedTierMaterials())) {
          return CraftingState.INCOMPLETE;
        }
      }
      else if (hasTier1) {
        if (hasMaterials(craftingItem.getAttunedTierMaterials())) {
          return CraftingState.INCOMPLETE;
        }
      }
      else {
        if (hasMaterials(craftingItem.getBasicTierMaterials())) {
          return CraftingState.INCOMPLETE;
        }
      }

      return CraftingState.MISSING_MATERIALS;      
    }

    return CraftingState.SKIP;    
  }

  private CraftingState getCountCraftingState(CraftingCountItem craftingItem) {
    int craftingCountTarget = craftingItem.getCraftingSetting(config);

    int count = 0;
    ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
    if (inventory != null) {
      for (var item : inventory.getItems()) {
        if (craftingItem.hasItemID(item.getId())) {
          count += item.getQuantity();
        }
      }
    }

    if (count >= craftingCountTarget) {
      return CraftingState.COMPLETE;
    }

    if (!hasMaterials(craftingItem.getMaterials())) {
      return CraftingState.MISSING_MATERIALS;
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

  private boolean hasMaterials(CraftingMaterial[] materials) {
    ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
    if (inventory == null) return false;

    for (CraftingMaterial material : materials) {
      int quantity = 0;
      for (var item : inventory.getItems()) {
        if (material.hasItemID(item.getId())) {
          quantity += item.getQuantity();
        }
      }

      if (quantity < material.quantity) return false;
    }
    return true;
  };
}
