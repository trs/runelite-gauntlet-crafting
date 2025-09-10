package com.trs.system;

import com.trs.component.ItemCategoryComponent;
import com.trs.component.MaterialComponent;
import com.trs.component.ItemIDComponent;
import com.trs.service.ConfigService;
import com.trs.type.IEntity;
import com.trs.type.IItemComponent;
import com.trs.model.CraftingIndex;
import com.trs.model.CraftingState;
import com.trs.model.ItemTier;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.runelite.api.Client;
import net.runelite.api.ItemContainer;
import net.runelite.api.gameval.InventoryID;

@Singleton
public class CraftingSystem extends AbstractSystem {
  private final ConfigService settingsService;
  private final Client client;

  @Inject
  public CraftingSystem(ConfigService settingsService, Client client) {
    super(
      ItemCategoryComponent.class,
      MaterialComponent.class,
      ItemIDComponent.class
    );
    this.settingsService = settingsService;
    this.client = client;
  }

  public CraftingState getCraftingState(CraftingIndex index) {
    var entity = getCraftingEntity(index);
    if (entity == null) return CraftingState.SKIP;

    var itemCategory = entity.getComponent(ItemCategoryComponent.class);
    if (itemCategory.tier != null) {
      return getTieredCraftingState(entity);
    }

    return getCountCraftingState(entity);
  }

  private CraftingState getTieredCraftingState(IEntity<IItemComponent> entity) {
    // var tierSetting = entity.getComponent(ItemCategoryComponent.class).tier;
    var itemCategory = entity.getComponent(ItemCategoryComponent.class);
    var tierSetting = settingsService.getItemCategoryTierSetting(itemCategory.category);

    if (tierSetting == ItemTier.NONE) return CraftingState.SKIP;

    if (tierSetting == ItemTier.BASIC) {
      if (hasItem(entity)) return CraftingState.CRAFTED;
      if (hasMaterials(entity)) return CraftingState.TO_CRAFT;
      return CraftingState.NOT_ENOUGH_MATERIALS;
    }

    if (tierSetting == ItemTier.ATTUNED) {
      if (hasItem(entity)) return CraftingState.CRAFTED;
      if (hasMaterials(entity) && hasItem(ItemCategoryComponent.getUpgradesFrom(entity))) return CraftingState.TO_CRAFT;

      // Check basic tier
      var basicEntity = ItemCategoryComponent.getUpgradesFrom(entity);
      if (!hasItem(basicEntity) && hasMaterials(basicEntity)) return CraftingState.TO_CRAFT;
      return CraftingState.NOT_ENOUGH_MATERIALS;
    }

    if (tierSetting == ItemTier.PERFECTED) {
      if (hasItem(entity)) return CraftingState.CRAFTED;
      if (hasMaterials(entity) && hasItem(ItemCategoryComponent.getUpgradesFrom(entity))) return CraftingState.TO_CRAFT;

      // Check attuned tier
      var attunedEntity = ItemCategoryComponent.getUpgradesFrom(entity);
      var basicEntity = ItemCategoryComponent.getUpgradesFrom(attunedEntity);
      if (!hasItem(attunedEntity) && hasMaterials(attunedEntity) && hasItem(basicEntity)) return CraftingState.TO_CRAFT;

      // Check basic tier
      if (!hasItem(attunedEntity) && !hasItem(basicEntity) && hasMaterials(basicEntity)) return CraftingState.TO_CRAFT;
      return CraftingState.NOT_ENOUGH_MATERIALS;
    }

    return CraftingState.SKIP;
  }

  private CraftingState getCountCraftingState(IEntity<IItemComponent> entity) {
    var itemCategory = entity.getComponent(ItemCategoryComponent.class);
    int craftingCountTarget = settingsService.getItemCategoryQuantitySetting(itemCategory.category);
    if (craftingCountTarget == 0) return CraftingState.CRAFTED;

    var itemIDs = entity.getComponent(ItemIDComponent.class).itemIDs;

    int count = 0;
    ItemContainer inventory = client.getItemContainer(InventoryID.INV);
    if (inventory != null) {
      for (var item : inventory.getItems()) {
    
        for (var itemID : itemIDs) {
          if (itemID == item.getId()) {
            count += item.getQuantity();
            break;
          }
        }
      }
    }

    if (count >= craftingCountTarget) {
      return CraftingState.CRAFTED;
    }

    if (!hasMaterials(entity)) {
      return CraftingState.NOT_ENOUGH_MATERIALS;
    }

    return CraftingState.TO_CRAFT;
  }


  private IEntity<IItemComponent> getCraftingEntity(CraftingIndex index) {
    for (var entity : getEntities()) {
      var itemCategory = entity.getComponent(ItemCategoryComponent.class);
      var entityIndex = CraftingIndex.getCraftingIndex(itemCategory.category);
      if (entityIndex == index && itemCategory.tier == settingsService.getItemCategoryTierSetting(itemCategory.category)) {
        return entity;
      }
    }
    return null;
  }

  private boolean hasItem(IEntity<IItemComponent> entity) {
    ItemContainer inventory = client.getItemContainer(InventoryID.INV);
    ItemContainer equipment = client.getItemContainer(InventoryID.WORN);

    var itemIDs = entity.getComponent(ItemIDComponent.class).itemIDs;

    for (int itemID : itemIDs) {
      if (inventory != null && inventory.contains(itemID)) return true;
      if (equipment != null && equipment.contains(itemID)) return true;
    }
    return false;
  }
  
  private boolean hasMaterials(IEntity<IItemComponent> entity) {
    ItemContainer inventory = client.getItemContainer(InventoryID.INV);
    if (inventory == null) return false;

    var materials = entity.getComponents(MaterialComponent.class);

    for (var material : materials) {
      var resourceIdentifier = material.resource.getComponent(ItemIDComponent.class);
      if (resourceIdentifier == null) continue;

      int quantity = 0;
      for (var item : inventory.getItems()) {

        for (var itemID : resourceIdentifier.itemIDs) {
          if (itemID == item.getId()) {
            quantity += item.getQuantity();
            break;
          }
        }
      }
      
      if (quantity < material.quantity) {
        return false;
      }
    }
    return true;
  }
}
