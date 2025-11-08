package com.trs.service;

import com.trs.GauntletPluginConfig;
import com.trs.model.ItemCategory;
import com.trs.model.ItemTier;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Color;

@Singleton
public class ConfigService {
  private final GauntletPluginConfig config;

  @Inject
  public ConfigService(GauntletPluginConfig config) {
    this.config = config;
  }

  public ItemTier getItemCategoryTierSetting(ItemCategory category) {
    switch (category) {
      case HELM:
        return config.craftingOptionHelm();
      case BODY:
        return config.craftingOptionBody();
      case LEGS:
        return config.craftingOptionLegs();
      case MELEE:
        return config.craftingOptionMelee();
      case MAGIC:
        return config.craftingOptionMagic();
      case RANGED:
        return config.craftingOptionRanged();
      default:
        return null;
    }
  }

  public int getItemCategoryQuantitySetting(ItemCategory category) {
    switch (category) {
      case TELEPORT_CRYSTAL:
        return config.craftingTeleportCount();
      case POTION:
        return config.craftingPotionCount();
      case VIAL:
        return config.craftingPotionCount();
      case FOOD:
        return config.craftingPaddlefishCount();
      case COMBO_FOOD:
        return config.craftingCrystalPaddlefishCount();
      case ESCAPE_CRYSTAL:
        return config.craftingEscapeCrystalCount();
      default:
        return 0;
    }
  }

  public Color getItemCategoryOutlineColor(ItemCategory category) {
    switch (category) {
      case HERB:
        return config.resourceOverlayHerbOutlineColor();
      case FISH:
        return config.resourceOverlayPondOutlineColor();
      case ORE:
        return config.resourceOverlayOreOutlineColor();
      case BARK:
        return config.resourceOverlayBarkOutlineColor();
      case FIBRE:
        return config.resourceOverlayFibreOutlineColor();
      default:
        return null;
    }
  }

  public Color getItemCategoryFillColor(ItemCategory category) {
    switch (category) {
      case HERB:
        return config.resourceOverlayHerbFillColor();
      case FISH:
        return config.resourceOverlayPondFillColor();
      case ORE:
        return config.resourceOverlayOreFillColor();
      case BARK:
        return config.resourceOverlayBarkFillColor();
      case FIBRE:
        return config.resourceOverlayFibreFillColor();
      default:
        return null;
    }
  }
}
