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
      case Helm:
        return config.craftingOptionHelm();
      case Body:
        return config.craftingOptionBody();
      case Legs:
        return config.craftingOptionLegs();
      default:
        return CraftingSetting.NONE;
    }
  }

  public boolean considerWithMaterials(CraftingTieredItem craftingItem) {
    switch (craftingItem) {
      case Ranged:
      case Magic:
      case Melee:
        return config.craftingOptionWeaponWithMaterial();
      case Helm:
      case Body:
      case Legs:
        return config.craftingOptionArmorWithMaterial();
      default:
        return false;
    }
  }
}
