package com.trs.model;

public enum CraftingTieredItem {
  Helm(
    CraftingItem.HELM_T1, CraftingItem.HELM_T2, CraftingItem.HELM_T3),
  Body(
    CraftingItem.BODY_T1, CraftingItem.BODY_T2, CraftingItem.BODY_T3),
  Legs(
    CraftingItem.LEGS_T1, CraftingItem.LEGS_T2, CraftingItem.LEGS_T3),
  Melee(
    CraftingItem.MELEE_T1, CraftingItem.MELEE_T2, CraftingItem.MELEE_T3),
  Magic(
    CraftingItem.MAGIC_T1, CraftingItem.MAGIC_T2, CraftingItem.MAGIC_T3),
  Ranged(
    CraftingItem.RANGED_T1, CraftingItem.RANGED_T2, CraftingItem.RANGED_T3);

  private final CraftingItem itemBasic;
  private final CraftingItem itemAttuned;
  private final CraftingItem itemPerfected;

  CraftingTieredItem(CraftingItem itemBasic, CraftingItem itemAttuned, CraftingItem itemPerfected) {
    this.itemBasic = itemBasic;
    this.itemAttuned = itemAttuned;
    this.itemPerfected = itemPerfected;
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
