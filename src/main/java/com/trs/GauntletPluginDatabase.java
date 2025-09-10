package com.trs;

import java.util.HashMap;

import com.trs.entity.GameEntity;
import com.trs.type.IEntity;
import com.trs.type.IItemComponent;

public class GauntletPluginDatabase {
  public static HashMap<Integer, GameEntity> spawnedResources = new HashMap<>();

  public static HashMap<IEntity<IItemComponent>, Integer> calculatedResources = new HashMap<>();

  public static HashMap<IEntity<IItemComponent>, Integer> collectedResources = new HashMap<>();
}
