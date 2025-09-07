package com.trs.component;

import com.trs.type.IResourceItemComponent;

public class ResourceIdentifierComponent implements IResourceItemComponent {
  public int[] itemIDs;

  public ResourceIdentifierComponent(int... itemIDs) {
    this.itemIDs = itemIDs;
  }
}
