package com.trs.type;

import java.util.ArrayList;
import java.util.List;

public interface IEntity<T> {
  T[] getComponents();
  
  default <U extends T> U getComponent(Class<U> componentClass) {
    for (T component : getComponents()) {
      if (componentClass.isInstance(component)) {
        return componentClass.cast(component);
      }
    }
    return null;
  }
  
  default <U extends T> List<U> getComponents(Class<U> componentClass) {
    var matchingComponents = new ArrayList<U>();
    for (T component : getComponents()) {
      if (componentClass.isInstance(component)) {
        matchingComponents.add(componentClass.cast(component));
      }
    }
    return matchingComponents;
  }
}
