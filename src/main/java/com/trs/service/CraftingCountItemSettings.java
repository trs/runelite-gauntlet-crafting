package com.trs.service;

import javax.inject.Singleton;
import javax.inject.Inject;

import com.trs.GauntletPluginConfig;
import com.trs.model.CraftingCountItem;

@Singleton
public class CraftingCountItemSettings {
  private final GauntletPluginConfig config;

  @Inject
  public CraftingCountItemSettings(GauntletPluginConfig config) {
    this.config = config;
  }

  public int getCraftingCount(CraftingCountItem craftingItem) {
    switch (craftingItem) {
      case TeleportCrystal:
        return config.craftingTeleportCount();
      case Vial:
        return config.craftingPotionCount();
      case Paddlefish:
        return config.craftingCrystalPaddlefishCount();
      case EscapeCrystal:
        return config.craftingEscapeCrystalCount();
      default:
        return 0;
    }
  }
}
