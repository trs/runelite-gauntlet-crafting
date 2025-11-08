package com.trs.entity;

import com.trs.type.IComponent;
import com.trs.type.IEntity;

// Corresponds to resources spawned in the game
public class GameEntity implements IEntity {
  private final IComponent[] components;

  public GameEntity(IComponent... components) {
    this.components = components;
  }

  public IComponent[] getComponents() {
    return components;
  }
}