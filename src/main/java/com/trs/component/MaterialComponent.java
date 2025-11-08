package com.trs.component;

import com.trs.type.IComponent;
import com.trs.type.IEntity;

public class MaterialComponent implements IComponent {
  public IEntity resource;
  public int quantity;

  public MaterialComponent(IEntity material, int quantity) {
    this.resource = material;
    this.quantity = quantity;
  }
}
