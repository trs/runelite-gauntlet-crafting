package com.trs.overlay;

import java.awt.*;
import javax.inject.Inject;

import com.trs.GauntletPluginConfig;
import com.trs.model.CraftingState;
import com.trs.model.CraftingCompleteBehaviour;
import com.trs.service.CraftingService;
import com.trs.service.LocationService;

import net.runelite.api.Client;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class CraftingOverlay extends Overlay {
  private static final String ACTION_TEXT = "What would you like to make?";
  private static final int[] CRAFTING_OPTION_WIDGETS = {
    InterfaceID.Skillmulti.A,
    InterfaceID.Skillmulti.B,
    InterfaceID.Skillmulti.C,
    InterfaceID.Skillmulti.D,
    InterfaceID.Skillmulti.E,
    InterfaceID.Skillmulti.F,
    InterfaceID.Skillmulti.G,
    InterfaceID.Skillmulti.H,
    InterfaceID.Skillmulti.I,
    InterfaceID.Skillmulti.J,
    InterfaceID.Skillmulti.K,
    InterfaceID.Skillmulti.L,
    InterfaceID.Skillmulti.M,
    InterfaceID.Skillmulti.N,
    InterfaceID.Skillmulti.O,
    InterfaceID.Skillmulti.P,
    InterfaceID.Skillmulti.Q,
    InterfaceID.Skillmulti.R,
  };

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

    // if (!isCrafting()) return null;

    Widget root = client.getWidget(InterfaceID.Skillmulti.BOTTOM);
    if (root == null || root.isHidden()) {
        return null;
    }

    for (int i = 0; i < CRAFTING_OPTION_WIDGETS.length; i++) {
      Widget childWidget = client.getWidget(CRAFTING_OPTION_WIDGETS[i]);
      if (childWidget == null || childWidget.isHidden()) continue;

      var craftingState = service.getCraftingState(i);
      renderOverlay(graphics2D, childWidget, craftingState);

      if (craftingState == CraftingState.COMPLETE) {
        CraftingCompleteBehaviour behaviour = config.craftingCompleteBehaviour();
        switch (behaviour) {
          case DISABLE:
            childWidget.clearActions();
            childWidget.revalidate();
            break;
          case HIDE:
            childWidget.setHidden(true);
            childWidget.revalidate();
            break;
          case DEFAULT:
            break;
        }
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
        case MISSING_MATERIALS:
          graphics.setColor(config.craftingHighlightMissingMaterialsColor());
          graphics.fill(bounds);
          graphics.setColor(config.craftingHighlightMissingMaterialsOutlineColor());
          graphics.setStroke(new BasicStroke(config.craftingHighlightMissingMaterialsStroke()));
          graphics.draw(bounds);
          break;
        case SKIP:
          break;
      }
    }
  }

  private boolean isCrafting() {
    Widget childWidget = client.getWidget(InterfaceID.Skillmulti.TITLE);
    return childWidget != null
      && childWidget.getText() != null
      && childWidget.getText().equalsIgnoreCase(ACTION_TEXT);
  }
}
