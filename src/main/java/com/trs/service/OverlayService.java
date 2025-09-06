package com.trs.service;

import com.trs.overlay.CraftingOverlay;
import javax.inject.Inject;
import javax.inject.Singleton;
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