package com.trs.component;

import com.trs.model.ItemCategory;
import com.trs.model.ItemTier;
import com.trs.type.IComponent;
import com.trs.type.IEntity;
import com.trs.entity.ItemEntity;

public class ItemCategoryComponent implements IComponent {
  public ItemCategory category;
  public ItemTier tier;

  public ItemCategoryComponent(ItemCategory category) {
    this.category = category;
    this.tier = null;
  }

  public ItemCategoryComponent(ItemCategory category, ItemTier tier) {
    this.category = category;
    this.tier = tier;
  }

  public static IEntity getUpgradesFrom(IEntity item) {
    var itemCategory = item.getComponent(ItemCategoryComponent.class);
    if (itemCategory.tier == null) return null;
    if (itemCategory.tier == ItemTier.NONE) return null;
    if (itemCategory.tier == ItemTier.BASIC) return null;

    var previousTier = ItemTier.BASIC;
    if (itemCategory.tier == ItemTier.ATTUNED) previousTier = ItemTier.BASIC;
    if (itemCategory.tier == ItemTier.PERFECTED) previousTier = ItemTier.ATTUNED;

    for (var entity : ItemEntity.values()) {
      var entityCategory = entity.getComponent(ItemCategoryComponent.class);
      if (entityCategory == null) continue;

      if (entityCategory.category != itemCategory.category) continue;
      if (entityCategory.tier != previousTier) continue;
      return entity;
    }
    return null;
  }
}
