package com.trs.system;

import com.trs.type.IItemComponent;
import com.trs.type.IEntity;
import com.trs.entity.ItemEntity;

public abstract class AbstractSystem {
  protected Class<? extends IItemComponent> c1 = null;
  protected Class<? extends IItemComponent> c2 = null;
  protected Class<? extends IItemComponent> c3 = null;
  protected Class<? extends IItemComponent> c4 = null;
  
  public AbstractSystem(Class<? extends IItemComponent> componentClass) {
    this.c1 = componentClass;
  }

  public AbstractSystem(Class<? extends IItemComponent> componentClass, Class<? extends IItemComponent> componentClass1) {
    this.c1 = componentClass;
    this.c2 = componentClass1;
  }

  public AbstractSystem(Class<? extends IItemComponent> componentClass, Class<? extends IItemComponent> componentClass1, Class<? extends IItemComponent> componentClass2) {
    this.c1 = componentClass;
    this.c2 = componentClass1;
    this.c3 = componentClass2;
  }

  public AbstractSystem(Class<? extends IItemComponent> componentClass, Class<? extends IItemComponent> componentClass1, Class<? extends IItemComponent> componentClass2, Class<? extends IItemComponent> componentClass3) {
    this.c1 = componentClass;
    this.c2 = componentClass1;
    this.c3 = componentClass2;
    this.c4 = componentClass3;
  }

  public void startUp() {}
  
  public void shutDown() {}

  @SafeVarargs
  @SuppressWarnings("unchecked")
  protected final IEntity<IItemComponent>[] findEntities(Class<? extends IItemComponent>... componentClasses) {
    return java.util.Arrays.stream(ItemEntity.values())
      .filter(entity -> {
        return java.util.Arrays.stream(componentClasses).allMatch(componentClass -> hasComponent(entity, componentClass));
      })
      .toArray(IEntity[]::new);
  }

  @SuppressWarnings("unchecked")
  protected IEntity<IItemComponent>[] getEntities() {
    return java.util.Arrays.stream(ItemEntity.values())
      .filter(entity -> {
        return hasComponent(entity, c1)
          && hasComponent(entity, c2)
          && hasComponent(entity, c3)
          && hasComponent(entity, c4);
      })
      .toArray(IEntity[]::new);
  }

  private boolean hasComponent(IEntity<IItemComponent> entity, Class<? extends IItemComponent> componentClass) {
    if (componentClass == null) return true;
    return !entity.getComponents(componentClass).isEmpty();
  }
}
