package com.trs.model;

public enum CraftingIndex {
  TELEPORT_CRYSTAL(0),
  VIAL(1),
  HELM(2),
  BODY(3),
  LEGS(4),
  MELEE(5),
  MAGIC(6),
  RANGED(7),
  COMBO_FOOD(8),
  ESCAPE_CRYSTAL(9);

  public int value;

  CraftingIndex(int value) {
    this.value = value;
  }

  public static CraftingIndex fromIndex(int index) {
    for (CraftingIndex craftingIndex : CraftingIndex.values()) {
      if (craftingIndex.value == index) {
        return craftingIndex;
      }
    }
    return null;
  }
}
