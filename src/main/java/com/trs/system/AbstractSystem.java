package com.trs.system;

import com.trs.type.IComponent;
import com.trs.type.IEntity;

public abstract class AbstractSystem {
  protected Class<? extends IComponent> c1 = null;
  protected Class<? extends IComponent> c2 = null;
  protected Class<? extends IComponent> c3 = null;
  protected Class<? extends IComponent> c4 = null;
  
  public AbstractSystem(Class<? extends IComponent> componentClass) {
    this.c1 = componentClass;
  }

  public AbstractSystem(Class<? extends IComponent> componentClass, Class<? extends IComponent> componentClass1) {
    this.c1 = componentClass;
    this.c2 = componentClass1;
  }

  public AbstractSystem(Class<? extends IComponent> componentClass, Class<? extends IComponent> componentClass1, Class<? extends IComponent> componentClass2) {
    this.c1 = componentClass;
    this.c2 = componentClass1;
    this.c3 = componentClass2;
  }

  public AbstractSystem(Class<? extends IComponent> componentClass, Class<? extends IComponent> componentClass1, Class<? extends IComponent> componentClass2, Class<? extends IComponent> componentClass3) {
    this.c1 = componentClass;
    this.c2 = componentClass1;
    this.c3 = componentClass2;
    this.c4 = componentClass3;
  }

  public void startUp() {}
  
  public void shutDown() {}

  @SafeVarargs
  protected final IEntity[] findEntities(IEntity[] entities, Class<? extends IComponent>... componentClasses) {
    return java.util.Arrays.stream(entities)
      .filter(entity -> {
        return java.util.Arrays.stream(componentClasses).allMatch(componentClass -> hasComponent(entity, componentClass));
      })
      .toArray(IEntity[]::new);
  }

  protected IEntity[] getEntities(IEntity[] entities) {
    return java.util.Arrays.stream(entities)
      .filter(entity -> {
        return hasComponent(entity, c1)
          && hasComponent(entity, c2)
          && hasComponent(entity, c3)
          && hasComponent(entity, c4);
      })
      .toArray(IEntity[]::new);
  }

  private boolean hasComponent(IEntity entity, Class<? extends IComponent> componentClass) {
    if (componentClass == null) return true;
    return !entity.getComponents(componentClass).isEmpty();
  }
}
