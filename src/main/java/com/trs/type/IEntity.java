package com.trs.type;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface IEntity {
  IComponent[] getComponents();
  
  default <U extends IComponent> U getComponent(Class<U> componentClass) {
    for (IComponent component : getComponents()) {
      if (componentClass.isInstance(component)) {
        return componentClass.cast(component);
      }
    }
    return null;
  }
  
  default <U extends IComponent> List<U> getComponents(Class<U> componentClass) {
    var matchingComponents = new ArrayList<U>();
    for (IComponent component : getComponents()) {
      if (componentClass.isInstance(component)) {
        matchingComponents.add(componentClass.cast(component));
      }
    }
    return matchingComponents;
  }

  default <U extends IComponent> List<U> getComponents(Class<U> componentClass, Predicate<U> filter) {
    var matchingComponents = new ArrayList<U>();
    for (IComponent component : getComponents()) {
      if (componentClass.isInstance(component)) {
        var castComponent = componentClass.cast(component);
        if (filter.test(castComponent)) {
          matchingComponents.add(castComponent);
        }
      }
    }
    return matchingComponents;
  }
}
