package com.trs.component;

import com.trs.type.IComponent;
import com.trs.model.NpcCategory;

public class NpcCategoryComponent implements IComponent {
  public NpcCategory category;

  public NpcCategoryComponent(NpcCategory category) {
    this.category = category;
  }
  
}
