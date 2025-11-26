package com.trs.service;

import lombok.extern.slf4j.Slf4j;
import javax.inject.Inject;
import javax.inject.Singleton;

import net.runelite.api.Client;
import net.runelite.api.ItemContainer;
import net.runelite.api.gameval.InventoryID;

import com.trs.model.CraftingMaterial;
import com.trs.model.CraftingTieredItem;
import com.trs.model.CraftingCountItem;
import com.trs.model.CraftingState;
import com.trs.model.CraftingSetting;

@Slf4j
@Singleton
public class CraftingService {

  private final Client client;
  private final CraftingItemSettings craftingSettingService;
  private final CraftingCountItemSettings craftingCountItemSettings;

  @Inject
  public CraftingService(Client client, CraftingItemSettings craftingSettingService, CraftingCountItemSettings craftingCountItemSettings) {
    this.client = client;
    this.craftingSettingService = craftingSettingService;
    this.craftingCountItemSettings = craftingCountItemSettings;
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
    CraftingSetting craftingSetting = craftingSettingService.getCraftingSetting(craftingItem);
    boolean considerWithMaterials = craftingSettingService.considerWithMaterials(craftingItem);

    if (craftingSetting == CraftingSetting.IGNORE) {
      return CraftingState.SKIP;
    }

    if (craftingSetting == CraftingSetting.NONE) {
      return CraftingState.COMPLETE;
    }
    
    boolean hasTier3 = hasAnyItem(craftingItem.getPerfectedTierItemIDs());
    boolean hasTier2 = hasTier3 || hasAnyItem(craftingItem.getAttunedTierItemIDs());
    boolean hasTier1 = hasTier2 || hasAnyItem(craftingItem.getBasicTierItemIDs());

    boolean hasTier3Materials = hasMaterials(craftingItem.getPerfectedTierMaterials());
    boolean hasTier2Materials = hasMaterials(craftingItem.getAttunedTierMaterials());
    boolean hasTier1Materials = hasMaterials(craftingItem.getBasicTierMaterials());

    if (craftingSetting == CraftingSetting.BASIC) {
      if (hasTier1) {
        return CraftingState.COMPLETE;
      } else if (hasTier1Materials) {
        return CraftingState.INCOMPLETE;
      }

      if (considerWithMaterials) return CraftingState.COMPLETE;

      return CraftingState.MISSING_MATERIALS;
    }

    if (craftingSetting == CraftingSetting.ATTUNED) {
      if (hasTier2) {
        return CraftingState.COMPLETE;
      }
      else if (hasTier1 && hasTier2Materials) {
        return CraftingState.INCOMPLETE;
      }
      else if (hasTier1Materials) {
        if (!considerWithMaterials) return CraftingState.INCOMPLETE;
        else if (hasTier2Materials) return CraftingState.INCOMPLETE;
      }

      if (considerWithMaterials) return CraftingState.COMPLETE;

      return CraftingState.MISSING_MATERIALS;
    }

    if (craftingSetting == CraftingSetting.PERFECTED) {
      if (hasTier3) {
        return CraftingState.COMPLETE;
      }

      if (hasTier2 && hasTier3Materials) {
        return CraftingState.INCOMPLETE;
      }
      else if (hasTier1 && hasTier2Materials) {
        if (!considerWithMaterials) return CraftingState.INCOMPLETE;
        else if (hasTier3Materials) return CraftingState.INCOMPLETE;
      }
      else if (hasTier1Materials) {
        if (!considerWithMaterials) return CraftingState.INCOMPLETE;
        else if (hasTier3Materials && hasTier2Materials) return CraftingState.COMPLETE;
      }

      if (considerWithMaterials) return CraftingState.COMPLETE;

      return CraftingState.MISSING_MATERIALS;
    }

    return CraftingState.SKIP;    
  }

  private CraftingState getCountCraftingState(CraftingCountItem craftingItem) {
    int craftingCountTarget = craftingCountItemSettings.getCraftingCount(craftingItem);

    int count = 0;
    ItemContainer inventory = client.getItemContainer(InventoryID.INV);
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
    ItemContainer equipment = client.getItemContainer(InventoryID.WORN);
    ItemContainer inventory = client.getItemContainer(InventoryID.INV);

    for (int itemID : itemIDs) {
      if (equipment != null && equipment.contains(itemID)) return true;
      if (inventory != null && inventory.contains(itemID)) return true;
    }
    return false;
  }

  private boolean hasMaterials(CraftingMaterial[] materials) {
    ItemContainer inventory = client.getItemContainer(InventoryID.INV);
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
