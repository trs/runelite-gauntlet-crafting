package com.trs.component;

import com.trs.type.IGameComponent;

import net.runelite.api.GameObject;

public class GameObjectComponent implements IGameComponent {
  public GameObject gameObject;

  public GameObjectComponent(GameObject gameObject) {
    this.gameObject = gameObject;
  }

  public static int getIdentifier(GameObject gameObject) {
    return gameObject.getWorldLocation().hashCode();
  }
}
