package com.trs.component;

import java.util.regex.Pattern;

import com.trs.type.IComponent;

public class CollectPatternComponent implements IComponent {
  public Pattern collectPattern;

  public CollectPatternComponent(String collectPattern) {
    this.collectPattern = Pattern.compile(collectPattern);
  }

  public Integer match(String message) {
    var matcher = collectPattern.matcher(message);
    if (matcher.matches()) {
      var quantity = matcher.group("quantity");
      var result = quantity == null ? 1 : Integer.parseInt(quantity);
      return result;
    }
    return null;
  }
}
