package com.trs.overlay;

import java.awt.*;
import javax.inject.Inject;

import com.trs.GauntletPluginConfig;
import com.trs.service.LocationService;
import com.trs.service.ResourceService;
import com.trs.entity.ResourceItem;

import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.Perspective;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

public class ResourceOverlay extends Overlay {
  private final Client client;
	private final ModelOutlineRenderer modelOutlineRenderer;
  private final GauntletPluginConfig config;
  private final LocationService locationService;
	private final ResourceService resourceService;
  
  @Inject
  public ResourceOverlay(
    GauntletPluginConfig config,
    Client client,
    LocationService locationService,
    ModelOutlineRenderer modelOutlineRenderer,
    ResourceService resourceService
  ) {
      super();
      this.client = client;
      this.config = config;
      this.locationService = locationService;
      this.modelOutlineRenderer = modelOutlineRenderer;
      this.resourceService = resourceService;
      setPosition(OverlayPosition.DYNAMIC);
      setLayer(OverlayLayer.UNDER_WIDGETS);
      setPriority(.6F);
  }

  @Override
  public Dimension render(Graphics2D graphics2D) {
    // if (!locationService.isInGauntletMaze()) return null;

    for (GameObject resource : resourceService.getSpawnedResources()) {
      renderResource(graphics2D, resource);
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
      new Color(0, 255, 255, 255), new BasicStroke(1));
    }
  }
}
