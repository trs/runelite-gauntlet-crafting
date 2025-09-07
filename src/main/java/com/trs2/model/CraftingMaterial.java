package com.trs2.model;

import java.util.Set;
import java.util.HashSet;

public class CraftingMaterial {
  public final Set<Integer> itemIDs;
  public final int quantity;

  CraftingMaterial(int[] itemIDs, int quantity) {
    this.itemIDs = new HashSet<>();
    for (int itemID : itemIDs) {
      this.itemIDs.add(itemID);
    }
    this.quantity = quantity;
  }

  public boolean hasItemID(int itemID) {
    return itemIDs.contains(itemID);
  }
}
