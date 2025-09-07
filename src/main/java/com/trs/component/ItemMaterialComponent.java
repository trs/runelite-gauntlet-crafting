package com.trs.component;

import com.trs.entity.ResourceItem;
import com.trs.type.ICraftableItemComponent;

public class ItemMaterialComponent implements ICraftableItemComponent {
  public ResourceItem resource;
  public int quantity;

  public ItemMaterialComponent(ResourceItem material, int quantity) {
    this.resource = material;
    this.quantity = quantity;
  }
}
