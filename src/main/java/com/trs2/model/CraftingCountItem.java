package com.trs2.model;

import java.util.function.Function;

import com.trs2.GauntletPluginConfig;

public enum CraftingCountItem {
  TeleportCrystal(
    CraftingItem.TELEPORT_CRYSTAL,
    GauntletPluginConfig::craftingTeleportCount
  ),
  Vial(
    CraftingItem.VIAL,
    GauntletPluginConfig::craftingPotionCount
  ),
  Paddlefish(
    CraftingItem.PADDLEFISH,
    GauntletPluginConfig::craftingCrystalPaddlefishCount
  ),
  EscapeCrystal(
    CraftingItem.ESCAPE_CRYSTAL,
    GauntletPluginConfig::craftingEscapeCrystalCount
  );

  private final CraftingItem item;
  private final Function<GauntletPluginConfig, Integer> configGetter;

  CraftingCountItem(CraftingItem item, Function<GauntletPluginConfig, Integer> configGetter) {
    this.configGetter = configGetter;
    this.item = item;
  }

  public static CraftingCountItem fromIndex(int index) {
    switch (index) {
      case 0: return TeleportCrystal;
      case 1: return Vial;
      case 8: return Paddlefish;
      case 9: return EscapeCrystal;
    }
    return null;
  }

  public int getCraftingSetting(GauntletPluginConfig config) {
    return configGetter != null ? configGetter.apply(config) : 0;
  }

  public boolean hasItemID(int itemID) {
    for (int id : item.getItemIDs()) {
      if (id == itemID) return true;
    }
    return false;
  }

  public CraftingMaterial[] getMaterials() {
    return item.getMaterials();
  }
}
