package com.trs.component;

import com.trs.type.IItemComponent;

public class ItemIDComponent implements IItemComponent {
  public int[] itemIDs;

  public ItemIDComponent(int... itemIDs) {
    this.itemIDs = itemIDs;
  }
}
