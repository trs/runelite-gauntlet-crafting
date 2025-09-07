package com.trs.component;

import com.trs.model.ItemCategory;
import com.trs.type.ICraftableItemComponent;

public class ItemCategoryComponent implements ICraftableItemComponent {
  public ItemCategory category;

  public ItemCategoryComponent(ItemCategory category) {
    this.category = category;
  }
}
