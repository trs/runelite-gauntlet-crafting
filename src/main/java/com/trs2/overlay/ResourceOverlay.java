package com.trs2.overlay;

import java.awt.*;
import javax.inject.Inject;

import com.trs.GauntletPluginConfig;
import com.trs.service.LocationService;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class ResourceOverlay extends Overlay {
  private final Client client;
  private final GauntletPluginConfig config;
  private final LocationService locationService;
  
  @Inject
  public ResourceOverlay(GauntletPluginConfig config, Client client, LocationService locationService) {
      super();
      this.client = client;
      this.config = config;
      this.locationService = locationService;
      setPosition(OverlayPosition.DYNAMIC);
      setLayer(OverlayLayer.ABOVE_WIDGETS);
      setPriority(.6F);
  }

  @Override
  public Dimension render(Graphics2D graphics2D) {
    if (!locationService.isInGauntletMaze()) return null;

    // for (int objectID : ResourceObject.ROCK.getObjectIDs()) {
    //   renderResourceObject(graphics2D, objectID, ResourceObject.ROCK);
    // }

    // for (int objectID : ResourceObject.TREE.getObjectIDs()) {
    //   renderResourceObject(graphics2D, objectID, ResourceObject.TREE);
    // }

    // for (int objectID : ResourceObject.POND.getObjectIDs()) {
    //   renderResourceObject(graphics2D, objectID, ResourceObject.POND);
    // }

    // for (int objectID : ResourceObject.HERB.getObjectIDs()) {
    //   renderResourceObject(graphics2D, objectID, ResourceObject.HERB);
    // }

    // for (int objectID : ResourceObject.FIBRE.getObjectIDs()) {
    //   renderResourceObject(graphics2D, objectID, ResourceObject.FIBRE);
    // }

    return null;
  }

  // private void renderResourceObject(Graphics2D graphics2D, int objectID, ResourceObject object) {

  // }
}
