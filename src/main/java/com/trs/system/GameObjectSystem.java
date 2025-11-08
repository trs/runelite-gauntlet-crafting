package com.trs.system;

import com.trs.component.ObjectIDComponent;
import com.trs.component.EntityPointerComponent;
import com.trs.component.GameObjectComponent;
import com.trs.entity.GameEntity;
import com.trs.GauntletPluginDatabase;
import com.trs.entity.ItemEntity;
import com.trs.service.LocationService;

import javax.inject.Singleton;
import javax.inject.Inject;

import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameObjectDespawned;

@Singleton
public class GameObjectSystem extends AbstractSystem {
  private final EventBus eventBus;
  private final LocationService locationService;

  @Inject
  public GameObjectSystem(EventBus eventBus, LocationService locationService) {
    super(ObjectIDComponent.class);
    this.eventBus = eventBus;
    this.locationService = locationService;
  }
  
  @Override
  public void startUp() {
    eventBus.register(this);
  }

  @Override
  public void shutDown() {
    eventBus.unregister(this);
  }

  @Subscribe
	public void onGameObjectSpawned(final GameObjectSpawned event)
	{
    if (!locationService.isInGauntletMaze()) return;

    var gameObject = event.getGameObject();
    
    for (var itemEntity : getEntities(ItemEntity.values())) {
      var objectIDComponents = itemEntity.getComponents(ObjectIDComponent.class);
      for (var objectIDComponent : objectIDComponents) {
        if (objectIDComponent.hasObjectID(gameObject.getId())) {

          var gameEntity = new GameEntity(
            new GameObjectComponent(gameObject),
            new EntityPointerComponent(itemEntity)
          );

          GauntletPluginDatabase.spawnedResources.put(GameObjectComponent.getIdentifier(gameObject), gameEntity);

          return;
        }
      }
    }
  }

  @Subscribe
  public void onGameObjectDespawned(final GameObjectDespawned event)
  {
    if (!locationService.isInGauntletMaze()) return;

    var gameObject = event.getGameObject();

    GauntletPluginDatabase.spawnedResources.remove(GameObjectComponent.getIdentifier(gameObject));
  }
}
