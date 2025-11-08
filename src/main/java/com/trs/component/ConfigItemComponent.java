package com.trs.component;

import java.util.function.Function;

import com.trs.GauntletPluginConfig;
import com.trs.type.IComponent;
import com.trs.model.ConfigType;

public class ConfigItemComponent<T> implements IComponent {
  public final Function<GauntletPluginConfig, T> configGetter;
  public final ConfigType configType;
  
  public ConfigItemComponent(ConfigType configType, Function<GauntletPluginConfig, T> configGetter) {
    this.configGetter = configGetter;
    this.configType = configType;
  }
}
