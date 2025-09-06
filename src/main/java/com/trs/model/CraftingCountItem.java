package com.trs.model;

import com.trs.GauntletPluginConfig;
import net.runelite.api.gameval.ItemID;
import java.util.function.Function;
import java.util.Set;
import java.util.HashSet;

public enum CraftingCountItem {
  TeleportCrystal(
    new int[] {
      ItemID.GAUNTLET_TELEPORT_CRYSTAL,
      ItemID.GAUNTLET_TELEPORT_CRYSTAL_HM,
    },
    GauntletPluginConfig::craftingTeleportCount
  ),
  Vial(
    new int[] {
      ItemID.GAUNTLET_VIAL_EMPTY,
      ItemID.GAUNTLET_VIAL_WATER,
      ItemID.GAUNTLET_POTION_UNFINISHED,
      ItemID.GAUNTLET_POTION_1,
      ItemID.GAUNTLET_POTION_2,
      ItemID.GAUNTLET_POTION_3,
      ItemID.GAUNTLET_POTION_4
    },
    GauntletPluginConfig::craftingPotionCount
  ),
  Paddlefish(
    new int[] {
      ItemID.GAUNTLET_COMBO_FOOD,
      ItemID.GAUNTLET_COMBO_FOOD_HM
    },
    GauntletPluginConfig::craftingCrystalPaddlefishCount
  ),
  EscapeCrystal(
    new int[] {
      ItemID.GAUNTLET_ESCAPE_CRYSTAL,
      ItemID.GAUNTLET_ESCAPE_CRYSTAL_HM
    },
    GauntletPluginConfig::craftingEscapeCrystalCount
  );

  private final Set<Integer> items;
  private final Function<GauntletPluginConfig, Integer> configGetter;

  CraftingCountItem(int[] items, Function<GauntletPluginConfig, Integer> configGetter) {
    this.configGetter = configGetter;
    this.items = new HashSet<>();
    for (int item : items) {
        this.items.add(item);
    }
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
    return items.contains(itemID);
  }
}
