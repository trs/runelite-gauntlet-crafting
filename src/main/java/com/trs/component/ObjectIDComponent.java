package com.trs.component;

import com.trs.type.IItemComponent;

public class ObjectIDComponent implements IItemComponent {

  public int[] objectIDs;

  public ObjectIDComponent(int... objectIDs) {
    this.objectIDs = objectIDs;
  }

  public boolean hasObjectID(int objectID) {
    for (var id : objectIDs) {
      if (id == objectID) {
        return true;
      }
    }
    return false;
  }
}
