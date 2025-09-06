package com.trs.model;

import com.trs.GauntletPluginConfig;
import net.runelite.api.gameval.ItemID;
import java.util.function.Function;

public enum CraftingTieredItem {
  Helm(
    ItemID.GAUNTLET_HELMET_T1, ItemID.GAUNTLET_HELMET_T2, ItemID.GAUNTLET_HELMET_T3,
    ItemID.GAUNTLET_HELMET_T1_HM, ItemID.GAUNTLET_HELMET_T2_HM, ItemID.GAUNTLET_HELMET_T3_HM,
    GauntletPluginConfig::craftingOptionHelm),
  Body(
    ItemID.GAUNTLET_CHESTPLATE_T1, ItemID.GAUNTLET_CHESTPLATE_T2, ItemID.GAUNTLET_CHESTPLATE_T3,
    ItemID.GAUNTLET_CHESTPLATE_T1_HM, ItemID.GAUNTLET_CHESTPLATE_T2_HM, ItemID.GAUNTLET_CHESTPLATE_T3_HM,
    GauntletPluginConfig::craftingOptionBody),
  Legs(
    ItemID.GAUNTLET_PLATELEGS_T1, ItemID.GAUNTLET_PLATELEGS_T2, ItemID.GAUNTLET_PLATELEGS_T3,
    ItemID.GAUNTLET_PLATELEGS_T1_HM, ItemID.GAUNTLET_PLATELEGS_T2_HM, ItemID.GAUNTLET_PLATELEGS_T3_HM,
    GauntletPluginConfig::craftingOptionLegs),
  Melee(
    ItemID.GAUNTLET_MELEE_T1, ItemID.GAUNTLET_MELEE_T2, ItemID.GAUNTLET_MELEE_T3,
    ItemID.GAUNTLET_MELEE_T1_HM, ItemID.GAUNTLET_MELEE_T2_HM, ItemID.GAUNTLET_MELEE_T3_HM,
    GauntletPluginConfig::craftingOptionMelee),
  Magic(
    ItemID.GAUNTLET_MAGIC_T1, ItemID.GAUNTLET_MAGIC_T2, ItemID.GAUNTLET_MAGIC_T3,
    ItemID.GAUNTLET_MAGIC_T1_HM, ItemID.GAUNTLET_MAGIC_T2_HM, ItemID.GAUNTLET_MAGIC_T3_HM,
    GauntletPluginConfig::craftingOptionMagic),
  Ranged(
    ItemID.GAUNTLET_RANGED_T1, ItemID.GAUNTLET_RANGED_T2, ItemID.GAUNTLET_RANGED_T3,
    ItemID.GAUNTLET_RANGED_T1_HM, ItemID.GAUNTLET_RANGED_T2_HM, ItemID.GAUNTLET_RANGED_T3_HM,
    GauntletPluginConfig::craftingOptionRanged);

  private final int crystalBasic;
  private final int crystalAttuned;
  private final int crystalPerfected;
  private final int corruptedBasic;
  private final int corruptedAttuned;
  private final int corruptedPerfected;
  private final Function<GauntletPluginConfig, CraftingSetting> configGetter;

  CraftingTieredItem(int crystalBasic, int crystalAttuned, int crystalPerfected,
               int corruptedBasic, int corruptedAttuned, int corruptedPerfected,
               Function<GauntletPluginConfig, CraftingSetting> configGetter) {
    this.crystalBasic = crystalBasic;
    this.crystalAttuned = crystalAttuned;
    this.crystalPerfected = crystalPerfected;
    this.corruptedBasic = corruptedBasic;
    this.corruptedAttuned = corruptedAttuned;
    this.corruptedPerfected = corruptedPerfected;
    this.configGetter = configGetter;
  }

  public static CraftingTieredItem fromIndex(int index) {
    switch (index) {
      case 2: return Helm;
      case 3: return Body;
      case 4: return Legs;
      case 5: return Melee;
      case 6: return Magic;
      case 7: return Ranged;
    }
    return null;
  }

  public CraftingSetting getCraftingSetting(GauntletPluginConfig config) {
    return configGetter != null ? configGetter.apply(config) : CraftingSetting.NONE;
  }

  public int[] getBasicTierItems() {
    return new int[]{crystalBasic, corruptedBasic};
  }

  public int[] getAttunedTierItems() {
    return new int[]{crystalAttuned, corruptedAttuned};
  }

  public int[] getPerfectedTierItems() {
    return new int[]{crystalPerfected, corruptedPerfected};
  }
}
