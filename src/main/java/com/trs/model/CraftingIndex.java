package com.trs.model;

import java.util.Map;
import java.util.HashMap;

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

  private static final Map<ItemCategory, CraftingIndex> craftingIndexMap = new HashMap<>(
    Map.ofEntries(
      Map.entry(ItemCategory.TELEPORT_CRYSTAL, CraftingIndex.TELEPORT_CRYSTAL),
      Map.entry(ItemCategory.VIAL, CraftingIndex.VIAL),
      Map.entry(ItemCategory.HELM, CraftingIndex.HELM),
      Map.entry(ItemCategory.BODY, CraftingIndex.BODY),
      Map.entry(ItemCategory.LEGS, CraftingIndex.LEGS),
      Map.entry(ItemCategory.MELEE, CraftingIndex.MELEE),
      Map.entry(ItemCategory.MAGIC, CraftingIndex.MAGIC),
      Map.entry(ItemCategory.RANGED, CraftingIndex.RANGED),
      Map.entry(ItemCategory.COMBO_FOOD, CraftingIndex.COMBO_FOOD),
      Map.entry(ItemCategory.ESCAPE_CRYSTAL, CraftingIndex.ESCAPE_CRYSTAL)
    )
  );

  public static CraftingIndex getCraftingIndex(ItemCategory itemCategory) {
    return craftingIndexMap.getOrDefault(itemCategory, null);
  }

  public static CraftingIndex getCraftingIndex(int index) {
    return CraftingIndex.values()[index];
  }
}
