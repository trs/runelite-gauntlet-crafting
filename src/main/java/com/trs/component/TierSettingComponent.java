package com.trs.component;

import java.util.function.Function;

import com.trs.GauntletPluginConfig;
import com.trs.model.ItemTier;
import com.trs.type.ICraftableItemComponent;

public class TierSettingComponent implements ICraftableItemComponent {
  public Function<GauntletPluginConfig, ItemTier> configGetter;

  public TierSettingComponent(Function<GauntletPluginConfig, ItemTier> configGetter) {
    this.configGetter = configGetter;
  }

  public ItemTier getTier(GauntletPluginConfig config) {
    return configGetter != null ? configGetter.apply(config) : null;
  }
}
