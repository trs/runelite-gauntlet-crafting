package com.trs.component;

import com.trs.type.IComponent;
import com.trs.type.IEntity;

public class EntityPointerComponent implements IComponent {
  public IEntity entity;

  public EntityPointerComponent(IEntity entity) {
    this.entity = entity;
  }
}
