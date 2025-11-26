package com.trs.model;

public enum CraftingCountItem {
  TeleportCrystal(
    CraftingItem.TELEPORT_CRYSTAL
  ),
  Vial(
    CraftingItem.VIAL
  ),
  Paddlefish(
    CraftingItem.PADDLEFISH
  ),
  EscapeCrystal(
    CraftingItem.ESCAPE_CRYSTAL
  );

  private final CraftingItem item;

  CraftingCountItem(CraftingItem item) {
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
