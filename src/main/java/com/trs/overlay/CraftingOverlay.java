package com.trs.overlay;

import java.awt.*;
import javax.inject.Inject;

import com.trs.GauntletPluginConfig;
import com.trs.model.CraftingState;
import com.trs.service.CraftingService;
import com.trs.service.LocationService;

import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class CraftingOverlay extends Overlay {
  private static final int CRAFTING_WIDGET = 270;
  private static final int CRAFTING_TEXT_WIDGET = 5;
  private static final int CRAFTING_FIRST_OPTION_WIDGET = 14;
  private static final int CRAFTING_OPTION_COUNT = 10;
  private static final String ACTION_TEXT = "What would you like to make?";

  private final Client client;
  private final CraftingService service;
  private final GauntletPluginConfig config;
  private final LocationService locationService;

  @Inject
  public CraftingOverlay(CraftingService service, GauntletPluginConfig config, LocationService locationService, Client client) {
      super();
      this.client = client;
      this.service = service;
      this.config = config;
      this.locationService = locationService;
      setPosition(OverlayPosition.DYNAMIC);
      setLayer(OverlayLayer.ABOVE_WIDGETS);
      setPriority(.6F);
  }

  @Override
  public Dimension render(Graphics2D graphics2D) {
    if (!locationService.isInGauntlet()) return null;

    if (!isCrafting()) return null;

    Widget root = client.getWidget(CRAFTING_WIDGET, 0);
    if (root == null || root.isHidden()) {
        return null;
    }

    for (int i = 0; i < CRAFTING_OPTION_COUNT; i++) {
      Widget childWidget = client.getWidget(CRAFTING_WIDGET, CRAFTING_FIRST_OPTION_WIDGET + i);
      if (childWidget != null && !childWidget.isHidden()) {
        var craftingState = service.getCraftingState(i);
        renderOverlay(graphics2D, childWidget, craftingState);
      }
    }

    return null;
  }

  private void renderOverlay(Graphics2D graphics, Widget childWidget, CraftingState craftingState) {
    Rectangle bounds = childWidget.getBounds();
    if (bounds != null) {
      switch (craftingState) {
        case INCOMPLETE:
          graphics.setColor(config.craftingHighlightColor());
          graphics.fill(bounds);
          graphics.setColor(config.craftingHighlightOutlineColor());
          graphics.setStroke(new BasicStroke(config.craftingHighlightStroke()));
          graphics.draw(bounds);
          break;
        case COMPLETE:
          graphics.setColor(config.craftingHighlightCompleteColor());
          graphics.fill(bounds);
          graphics.setColor(config.craftingHighlightCompleteOutlineColor());
          graphics.setStroke(new BasicStroke(config.craftingHighlightCompleteStroke()));
          graphics.draw(bounds);
          break;
        case SKIP:
          break;
      }
    }
  }

  private boolean isCrafting() {
    Widget childWidget = client.getWidget(CRAFTING_WIDGET, CRAFTING_TEXT_WIDGET);
    return childWidget != null
      && childWidget.getText() != null
      && childWidget.getText().equalsIgnoreCase(ACTION_TEXT);
  }
}
