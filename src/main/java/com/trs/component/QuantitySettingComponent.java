package com.trs.component;

import java.util.function.Function;

import com.trs.GauntletPluginConfig;
import com.trs.type.ICraftableItemComponent;

public class QuantitySettingComponent implements ICraftableItemComponent {
  public Function<GauntletPluginConfig, Integer> configGetter;

  public QuantitySettingComponent(Function<GauntletPluginConfig, Integer> configGetter) {
    this.configGetter = configGetter;
  }

  public Integer getQuantity(GauntletPluginConfig config) {
    return configGetter != null ? configGetter.apply(config) : null;
  }
}
