package com.trs.entity;

import com.trs.type.IGameComponent;
import com.trs.type.IEntity;

// Corresponds to resources spawned in the game
public class GameEntity implements IEntity<IGameComponent> {
  private final IGameComponent[] components;

  public GameEntity(IGameComponent... components) {
    this.components = components;
  }

  public IGameComponent[] getComponents() {
    return components;
  }
}