package com.trs.overlay;

import java.awt.*;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.trs.GauntletPluginDatabase;
import com.trs.GauntletPluginConfig;
// import com.trs.service.ResourceService;
import com.trs.service.LocationService;
import com.trs.entity.GameEntity;
import com.trs.component.GameObjectComponent;
import com.trs.component.ObjectItemComponent;

import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.Perspective;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class ResourceOverlay extends Overlay {
  private final Client client;
	private final ModelOutlineRenderer modelOutlineRenderer;
  private final GauntletPluginConfig config;
	// private final ResourceService resourceService;
  private final LocationService locationService;
  
  @Inject
  public ResourceOverlay(
    GauntletPluginConfig config,
    Client client,
    ModelOutlineRenderer modelOutlineRenderer,
    // ResourceService resourceService,
    LocationService locationService
  ) {
      super();
      this.client = client;
      this.config = config;
      this.modelOutlineRenderer = modelOutlineRenderer;
      // this.resourceService = resourceService;
      this.locationService = locationService;
      setPosition(OverlayPosition.DYNAMIC);
      setLayer(OverlayLayer.UNDER_WIDGETS);
      setPriority(.6F);
  }

  @Override
  public Dimension render(Graphics2D graphics2D) {
    if (!locationService.isInGauntletMaze()) return null;

    log.info("Collected resources: {}", GauntletPluginDatabase.collectedResources.size());

    for (var resource : GauntletPluginDatabase.collectedResources.entrySet()) {
      var calculatedCount = GauntletPluginDatabase.calculatedResources.getOrDefault(resource.getKey(), 0);
      log.info("Collected resource: {} = {} / {}", resource.getKey().toString(), resource.getValue(), calculatedCount);
    }
    
    for (GameEntity resource : GauntletPluginDatabase.spawnedResources.values()) {
      var gameObjectComponent = resource.getComponent(GameObjectComponent.class);
      if (gameObjectComponent == null) continue;

      var objectItemComponent = resource.getComponent(ObjectItemComponent.class);
      if (objectItemComponent == null) continue;

      var calculatedCount = GauntletPluginDatabase.calculatedResources.getOrDefault(objectItemComponent.itemEntity, 0);

      var collectedCount = GauntletPluginDatabase.collectedResources.getOrDefault(objectItemComponent.itemEntity, 0);

      if (collectedCount >= calculatedCount) continue;

      renderResource(graphics2D, gameObjectComponent.gameObject);
    }

    return null;
  }

  private void renderResource(Graphics2D graphics2D, GameObject gameObject) {
    modelOutlineRenderer.drawOutline(
      gameObject,
      1,
      new Color(0, 255, 255, 255),
      1
    );

    var polygon = Perspective.getCanvasTilePoly(client, gameObject.getLocalLocation());
    if (polygon != null)
    {
      OverlayUtil.renderPolygon(graphics2D, polygon, new Color(0, 255, 255, 255),
        new Color(0, 255, 255, 0), new BasicStroke(1));
    }
  }
}
