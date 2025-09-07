package com.trs.component;

import com.trs.type.IResourceItemComponent;

public class ResourceObjectComponent implements IResourceItemComponent {

  public int[] objectIDs;

  public ResourceObjectComponent(int... objectIDs) {
    this.objectIDs = objectIDs;
  }
}
