package com.trs.component;

import com.trs.type.IGameComponent;
import com.trs.type.IEntity;
import com.trs.type.IItemComponent;

public class ObjectItemComponent implements IGameComponent {
  public IEntity<IItemComponent> itemEntity;

  public ObjectItemComponent(IEntity<IItemComponent> itemEntity) {
    this.itemEntity = itemEntity;
  }
}
