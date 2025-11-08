package com.trs.overlay;

import java.awt.*;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import com.trs.GauntletPluginDatabase;
import com.trs.entity.GameEntity;
import com.trs.component.NpcComponent;
import com.trs.service.LocationService;

import net.runelite.client.ui.overlay.Overlay;

@Singleton
@Slf4j
public class NpcOverlay extends Overlay {

  private final LocationService locationService;

  @Inject
  public NpcOverlay(LocationService locationService) {
    this.locationService = locationService;
  }

  @Override
  public Dimension render(Graphics2D graphics2D) {
    if (!locationService.isInGauntletMaze() && !locationService.isInGauntletBoss()) return null;

    for (var gameEntity : GauntletPluginDatabase.spawnedNpcs.values()) {
      renderGameEntity(graphics2D, gameEntity);
    }

    return null;
  }

  private void renderGameEntity(Graphics2D graphics2D, GameEntity gameEntity) { 
    var npcComponent = gameEntity.getComponent(NpcComponent.class);

    var npc = npcComponent.npc;
    var composition = npc.getTransformedComposition();
    if (composition == null || !composition.isInteractible()) return;

    

    // var polygon = Perspective.getCanvasTilePoly(client, npc.getLocalLocation());
    // if (polygon != null) {
    //   OverlayUtil.renderPolygon(graphics2D, polygon, outlineColor, fillColor, new BasicStroke(config.resourceOverlayTileOutlineWidth()));
    // }
  }
}
