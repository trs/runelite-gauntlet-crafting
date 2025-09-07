package com.trs.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.trs.overlay.ResourceOverlay;

import net.runelite.client.ui.overlay.OverlayManager;

@Singleton
public class ResourceOverlayService {
    @Inject private OverlayManager overlayManager;

    @Inject private ResourceOverlay resourceOverlay;

    private boolean isRegistered = false;

    public void registerOverlays() {
        if (isRegistered) return;
        overlayManager.add(resourceOverlay);
        isRegistered = true;
    }

    public void unregisterOverlays() {
        if (!isRegistered) return;
        overlayManager.remove(resourceOverlay);
        isRegistered = false;
    }
}