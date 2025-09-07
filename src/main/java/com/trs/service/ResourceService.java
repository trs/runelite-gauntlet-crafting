package com.trs.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

// import com.trs.entity.ResourceItem;

import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameTick;
import net.runelite.api.GameObject;
import net.runelite.client.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class ResourceService {
  private final Set<GameObject> spawnedResources = new HashSet<>();
  private int tickCount = 0;

  public Set<GameObject> getSpawnedResources() {
    return spawnedResources;
  }

  @Inject
  public ResourceService() {
    log.info("=== ResourceService initialized - ready to receive events ===");
  }

  @Subscribe
	public void onGameObjectSpawned(final GameObjectSpawned event)
	{
    var gameObject = event.getGameObject();
    log.debug("GameObjectSpawned event received - Object ID: {}, Location: {}", 
        gameObject.getId(), gameObject.getWorldLocation());
    
    // Track all objects for debugging
    spawnedResources.add(gameObject);

    // // Check if this is a Gauntlet resource object
    // for (ResourceItem resourceItem : ResourceItem.values()) {
    //   var objectIDs = resourceItem.getObjectIDs();
    //   if (objectIDs == null) continue;
    //   for (int objectID : objectIDs) {
    //     if (objectID == gameObject.getId()) {
    //      log.info("Gauntlet resource spawned: {} (Object ID: {})", resourceItem, objectID);
    //      break; // Found the resource, no need to continue inner loop
    //     }
    //   }
    // }
  }

  @Subscribe
  public void onGameObjectDespawned(final GameObjectDespawned event)
  {
    var gameObject = event.getGameObject();
    log.debug("GameObjectDespawned event received - Object ID: {}, Location: {}", 
        gameObject.getId(), gameObject.getWorldLocation());
    
    boolean wasRemoved = spawnedResources.remove(gameObject);
    if (!wasRemoved) {
      log.debug("Object was not in our tracked set: {}", gameObject.getId());
    }
    
    // Check if this was a Gauntlet resource object
    // for (ResourceItem resourceItem : ResourceItem.values()) {
    //   var objectIDs = resourceItem.getObjectIDs();
    //   if (objectIDs == null) continue;
    //   for (int objectID : objectIDs) {
    //     if (objectID == gameObject.getId()) {
    //      log.info("Gauntlet resource despawned: {} (Object ID: {})", resourceItem, objectID);
    //      break; // Found the resource, no need to continue inner loop
    //     }
    //   }
    // }
  }

  @Subscribe
  public void onGameTick(final GameTick event)
  {
    tickCount++;
    // Log first few ticks immediately to verify events work
    if (tickCount <= 5) {
      log.info("=== GAUNTLET PLUGIN EVENT WORKING === GameTick #{}: tracked objects: {}", 
          tickCount, spawnedResources.size());
    }
    // Then log every 100 ticks
    else if (tickCount % 100 == 0) {
      log.info("GameTick event working - tick count: {}, tracked objects: {}", 
          tickCount, spawnedResources.size());
    }
  }
}
