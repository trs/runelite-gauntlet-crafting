package com.trs.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.eventbus.EventBus;

import com.trs.overlay.CraftingOverlay;
import com.trs.overlay.ResourceOverlay;

@Slf4j
@Singleton
public class OverlayService extends AbstractService {
  private final EventBus eventBus;
  private final OverlayManager overlayManager;
  private final CraftingOverlay craftingOverlay;
  private final ResourceOverlay resourceOverlay;

  @Inject
  public OverlayService(EventBus eventBus, OverlayManager overlayManager, CraftingOverlay craftingOverlay, ResourceOverlay resourceOverlay) {
    this.eventBus = eventBus;
    this.overlayManager = overlayManager;
    this.craftingOverlay = craftingOverlay;
    this.resourceOverlay = resourceOverlay;
  }

  @Override
  public void startUp() {
    eventBus.register(this);
    overlayManager.add(craftingOverlay);
    overlayManager.add(resourceOverlay);
  }

  @Override
  public void shutDown() {
    eventBus.unregister(this);
    overlayManager.remove(craftingOverlay);
    overlayManager.remove(resourceOverlay);
  }
}