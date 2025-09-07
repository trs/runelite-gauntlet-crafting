package com.trs2.model;

import com.trs2.data.ResourceObjectIDs;

public enum ResourceObject {
  ROCK(ResourceObjectIDs.Rocks),
  TREE(ResourceObjectIDs.Trees),
  POND(ResourceObjectIDs.Ponds),
  HERB(ResourceObjectIDs.Herbs),
  FIBRE(ResourceObjectIDs.Fibres);

  private final int[] objectIDs;

  ResourceObject(int[] objectIDs) {
    this.objectIDs = objectIDs;
  }

  public int[] getObjectIDs() {
    return objectIDs;
  }
}
