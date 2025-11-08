package com.trs.overlay;

import java.awt.*;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.trs.GauntletPluginDatabase;
import com.trs.GauntletPluginConfig;
import com.trs.service.ConfigService;
import com.trs.service.LocationService;
import com.trs.entity.GameEntity;
import com.trs.component.GameObjectComponent;
import com.trs.component.EntityPointerComponent;
import com.trs.component.ItemCategoryComponent;

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
  private final GauntletPluginConfig config;
	private final ModelOutlineRenderer modelOutlineRenderer;
  private final LocationService locationService;
  private final ConfigService configService;
  
  @Inject
  public ResourceOverlay(
    Client client,
    ModelOutlineRenderer modelOutlineRenderer,
    GauntletPluginConfig config,
    ConfigService configService,
    LocationService locationService
  ) {
      super();
      this.client = client;
      this.modelOutlineRenderer = modelOutlineRenderer;
      this.config = config;
      this.configService = configService;
      this.locationService = locationService;
      setPosition(OverlayPosition.DYNAMIC);
      setLayer(OverlayLayer.UNDER_WIDGETS);
      setPriority(.6F);
  }

  @Override
  public Dimension render(Graphics2D graphics2D) {
    if (!locationService.isInGauntletMaze()) return null;

    for (GameEntity resource : GauntletPluginDatabase.spawnedResources.values()) {
      var entityPointer = resource.getComponent(EntityPointerComponent.class);
      if (entityPointer == null) continue;

      var calculatedCount = GauntletPluginDatabase.calculatedResources.getOrDefault(entityPointer.entity, 0);

      var collectedCount = GauntletPluginDatabase.collectedResources.getOrDefault(entityPointer.entity, 0);

      if (collectedCount >= calculatedCount) continue;

      renderResource(graphics2D, resource);
    }

    return null;
  }

  private void renderResource(Graphics2D graphics2D, GameEntity resource) {
    var gameObjectComponent = resource.getComponent(GameObjectComponent.class);
    var entityPointer = resource.getComponent(EntityPointerComponent.class);

    var gameObject = gameObjectComponent.gameObject;

    var itemCategory = entityPointer.entity.getComponent(ItemCategoryComponent.class);

    var outlineColor = configService.getItemCategoryOutlineColor(itemCategory.category);
    var fillColor = configService.getItemCategoryFillColor(itemCategory.category);

    if (outlineColor != null) {
      modelOutlineRenderer.drawOutline(
        gameObject,
        config.resourceOverlayHullOutlineWidth(),
        outlineColor,
        1
      );

      if (fillColor != null) {
        var polygon = Perspective.getCanvasTilePoly(client, gameObject.getLocalLocation());
        if (polygon != null)
        {
          OverlayUtil.renderPolygon(graphics2D, polygon, outlineColor, fillColor, new BasicStroke(config.resourceOverlayTileOutlineWidth()));
        }
      }
    }
  }
}
