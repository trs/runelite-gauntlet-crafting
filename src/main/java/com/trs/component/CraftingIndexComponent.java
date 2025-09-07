package com.trs.component;

import com.trs.model.CraftingIndex;
import com.trs.type.ICraftableItemComponent;

public class CraftingIndexComponent implements ICraftableItemComponent {
  public CraftingIndex index;

  public CraftingIndexComponent(CraftingIndex index) {
    this.index = index;
  }

  public int getValue() {
    return index.value;
  }
}
