package com.trs.component;

import com.trs.type.IComponent;

import net.runelite.api.GameObject;

public class GameObjectComponent implements IComponent {
  public GameObject gameObject;

  public GameObjectComponent(GameObject gameObject) {
    this.gameObject = gameObject;
  }

  public static int getIdentifier(GameObject gameObject) {
    return gameObject.getWorldLocation().hashCode();
  }
}
