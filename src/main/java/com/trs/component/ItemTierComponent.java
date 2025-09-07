package com.trs.component;

import com.trs.entity.CraftableItem;
import com.trs.model.ItemTier;
import com.trs.type.ICraftableItemComponent;

public class ItemTierComponent implements ICraftableItemComponent {
  public ItemTier tier;
  public CraftableItem upgradesFrom;

  public ItemTierComponent(ItemTier tier, CraftableItem upgradesFrom) {
    this.tier = tier;
    this.upgradesFrom = upgradesFrom;
  }
}
