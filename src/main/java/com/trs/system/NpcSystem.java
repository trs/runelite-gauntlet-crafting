package com.trs.system;

import com.trs.component.NpcComponent;
import com.trs.component.NpcCategoryComponent;
import com.trs.component.ItemIDComponent;
import com.trs.component.EntityPointerComponent;
import com.trs.GauntletPluginDatabase;
import com.trs.entity.GameEntity;
import com.trs.entity.NpcEntity;
import com.trs.service.LocationService;

import javax.inject.Singleton;
import javax.inject.Inject;

import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.NpcDespawned;

@Singleton
public class NpcSystem extends AbstractSystem {
  private final EventBus eventBus;
  private final LocationService locationService;

  @Inject
  public NpcSystem(EventBus eventBus, LocationService locationService) {
    super(ItemIDComponent.class, NpcCategoryComponent.class);
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
	public void onNpcSpawned(final NpcSpawned event)
	{
    if (!locationService.isInGauntletMaze()) return;

    var npc = event.getNpc();

    for (var npcEntity : getEntities(NpcEntity.values())) {
      var identifierComponent = npcEntity.getComponent(ItemIDComponent.class);
      
      if (identifierComponent.hasIdentifier(npc.getId())) {
        var gameEntity = new GameEntity(
          new NpcComponent(npc),
          new EntityPointerComponent(npcEntity)
        );

        GauntletPluginDatabase.spawnedNpcs.put(NpcComponent.getIdentifier(npc), gameEntity);
        break;
      }
    }
  }

  @Subscribe
  public void onNpcDespawned(final NpcDespawned event)
  {
    if (!locationService.isInGauntletMaze()) return;

    var npc = event.getNpc();

    GauntletPluginDatabase.spawnedNpcs.remove(NpcComponent.getIdentifier(npc));
  }
}
