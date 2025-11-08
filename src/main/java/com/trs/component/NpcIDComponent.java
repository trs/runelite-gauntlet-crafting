package com.trs.component;

import com.trs.type.IComponent;

public class NpcIDComponent implements IComponent {
  public int[] ids;

  public NpcIDComponent(int... ids) {
    this.ids = ids;
  }

  public boolean hasIdentifier(int identifier) {
    for (var id : ids) {
      if (id == identifier) {
        return true;
      }
    }
    return false;
  }
}
