package com.trs.service;

import javax.inject.Singleton;
import javax.inject.Inject;

import com.trs.model.CraftingTieredItem;
import com.trs.model.CraftingSetting;
import com.trs.GauntletPluginConfig;

@Singleton
public class CraftingItemSettings {
  private final GauntletPluginConfig config;

  @Inject
  public CraftingItemSettings(GauntletPluginConfig config) {
    this.config = config;
  }

  public CraftingSetting getCraftingSetting(CraftingTieredItem craftingItem) {
    switch (craftingItem) {
      case Ranged:
        return config.craftingOptionRanged();
      case Magic:
        return config.craftingOptionMagic();
      case Melee:
        return config.craftingOptionMelee();
      default:
        return CraftingSetting.NONE;
    }
  }

  public boolean considerWithMaterials(CraftingTieredItem craftingItem) {
    switch (craftingItem) {
      case Ranged:
        return config.craftingOptionRangedWithMaterial();
      case Magic:
        return config.craftingOptionMagicWithMaterial();
      case Melee:
        return config.craftingOptionMeleeWithMaterial();
      default:
        return false;
    }
  }
}
