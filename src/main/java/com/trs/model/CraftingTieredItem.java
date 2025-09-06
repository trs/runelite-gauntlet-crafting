package com.trs.model;

import com.trs.GauntletPluginConfig;
import java.util.function.Function;

public enum CraftingTieredItem {
  Helm(
    CraftingItem.HELM_T1, CraftingItem.HELM_T2, CraftingItem.HELM_T3,
    GauntletPluginConfig::craftingOptionHelm),
  Body(
    CraftingItem.BODY_T1, CraftingItem.BODY_T2, CraftingItem.BODY_T3,
    GauntletPluginConfig::craftingOptionBody),
  Legs(
    CraftingItem.LEGS_T1, CraftingItem.LEGS_T2, CraftingItem.LEGS_T3,
    GauntletPluginConfig::craftingOptionLegs),
  Melee(
    CraftingItem.MELEE_T1, CraftingItem.MELEE_T2, CraftingItem.MELEE_T3,
    GauntletPluginConfig::craftingOptionMelee),
  Magic(
    CraftingItem.MAGIC_T1, CraftingItem.MAGIC_T2, CraftingItem.MAGIC_T3,
    GauntletPluginConfig::craftingOptionMagic),
  Ranged(
    CraftingItem.RANGED_T1, CraftingItem.RANGED_T2, CraftingItem.RANGED_T3,
    GauntletPluginConfig::craftingOptionRanged);

  private final CraftingItem itemBasic;
  private final CraftingItem itemAttuned;
  private final CraftingItem itemPerfected;
  private final Function<GauntletPluginConfig, CraftingSetting> configGetter;

  CraftingTieredItem(CraftingItem itemBasic, CraftingItem itemAttuned, CraftingItem itemPerfected,
               Function<GauntletPluginConfig, CraftingSetting> configGetter) {
    this.itemBasic = itemBasic;
    this.itemAttuned = itemAttuned;
    this.itemPerfected = itemPerfected;
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

  public int[] getBasicTierItemIDs() {
    return itemBasic.getItemIDs();
  }

  public int[] getAttunedTierItemIDs() {
    return itemAttuned.getItemIDs();
  }

  public int[] getPerfectedTierItemIDs() {
    return itemPerfected.getItemIDs();
  }

  public CraftingMaterial[] getBasicTierMaterials() {
    return itemBasic.getMaterials();
  }

  public CraftingMaterial[] getAttunedTierMaterials() {
    return itemAttuned.getMaterials();
  }

  public CraftingMaterial[] getPerfectedTierMaterials() {
    return itemPerfected.getMaterials();
  }
}
