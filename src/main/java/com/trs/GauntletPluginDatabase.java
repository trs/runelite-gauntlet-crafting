package com.trs;

import java.util.HashMap;

import com.trs.entity.GameEntity;
import com.trs.type.IEntity;
import com.trs.type.IComponent;

public class GauntletPluginDatabase {
  public static HashMap<Integer, GameEntity> spawnedResources = new HashMap<>();

  public static HashMap<IEntity, Integer> calculatedResources = new HashMap<>();

  public static HashMap<IEntity, Integer> collectedResources = new HashMap<>();

  public static HashMap<Integer, GameEntity> spawnedNpcs = new HashMap<>();
}
