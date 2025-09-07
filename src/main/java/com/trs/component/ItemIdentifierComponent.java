package com.trs.component;

import com.trs.type.ICraftableItemComponent;

public class ItemIdentifierComponent implements ICraftableItemComponent {
  public int[] itemIDs;

  public ItemIdentifierComponent(int... itemIDs) {
    this.itemIDs = itemIDs;
  }
}
