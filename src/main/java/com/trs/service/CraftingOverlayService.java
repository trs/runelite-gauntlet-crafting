package com.trs.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.trs.overlay.CraftingOverlay;

import net.runelite.client.ui.overlay.OverlayManager;

@Singleton
public class CraftingOverlayService {
    @Inject private OverlayManager overlayManager;

    @Inject private CraftingOverlay craftingOverlay;

    private boolean isRegistered = false;

    public void registerOverlays() {
        if (isRegistered) return;
        overlayManager.add(craftingOverlay);
        isRegistered = true;
    }

    public void unregisterOverlays() {
        if (!isRegistered) return;
        overlayManager.remove(craftingOverlay);
        isRegistered = false;
    }
}