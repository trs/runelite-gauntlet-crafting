package com.trs.component;

import com.trs.type.IItemComponent;
import com.trs.type.IEntity;

public class MaterialComponent implements IItemComponent {
  public IEntity<IItemComponent> resource;
  public int quantity;

  public MaterialComponent(IEntity<IItemComponent> material, int quantity) {
    this.resource = material;
    this.quantity = quantity;
  }
}
