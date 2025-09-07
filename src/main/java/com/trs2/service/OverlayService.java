package com.trs2.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.trs2.overlay.CraftingOverlay;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@Singleton
public class OverlayService {
    @Inject private OverlayManager overlayManager;

    @Inject private CraftingOverlay craftingOverlay;

    public OverlayService() {}

    public void registerOverlays() {
        overlayManager.add(craftingOverlay);
    }

    public void unregisterOverlays() {
        overlayManager.remove(craftingOverlay);
    }
}