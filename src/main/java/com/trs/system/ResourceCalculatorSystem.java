package com.trs.system;

import java.util.HashMap;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.trs.GauntletPluginConfig;
import com.trs.GauntletPluginDatabase;
import com.trs.component.ItemCategoryComponent;
import com.trs.component.MaterialComponent;
import com.trs.entity.ItemEntity;
import com.trs.GauntletPlugin;
import com.trs.service.ConfigService;
import com.trs.type.IEntity;
import com.trs.type.IComponent;

import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.api.events.VarbitChanged;

@Singleton
public class ResourceCalculatorSystem extends AbstractSystem {
  private final EventBus eventBus;
  private final ConfigService settingsService;

  @Inject
  public ResourceCalculatorSystem(EventBus eventBus, ConfigService settingsService) {
    super(MaterialComponent.class, ItemCategoryComponent.class);
    this.eventBus = eventBus;
    this.settingsService = settingsService;
  }

  @Override
  public void startUp() {
    eventBus.register(this);
  }
  
  @Override
  public void shutDown() {
    eventBus.unregister(this);
  }

  public void calculateResources() {
    var materials = calculateAllMaterials(getEntities(ItemEntity.values()));

    for (var material : materials.entrySet()) {
      GauntletPluginDatabase.calculatedResources.put(material.getKey(), material.getValue());
    }
  }

  public HashMap<IEntity, Integer> calculateAllMaterials(IEntity[] entities) {
    var materials = new HashMap<IEntity, Integer>();

    for (IEntity entity : entities) {
      var itemCategory = entity.getComponent(ItemCategoryComponent.class);
      if (itemCategory == null) continue;

      var itemTierSetting = settingsService.getItemCategoryTierSetting(itemCategory.category);
      var itemQuantitySetting = settingsService.getItemCategoryQuantitySetting(itemCategory.category);

      if (itemTierSetting != null && itemCategory.tier.compareTo(itemTierSetting) > 0) continue;

      var entityMaterials = calculateMaterials(entity, itemTierSetting != null ? 1 :itemQuantitySetting);

      for (var material : entityMaterials.entrySet()) {
        var currentValue = materials.getOrDefault(material.getKey(), 0);
        materials.put(material.getKey(), currentValue + material.getValue());
      }
    }

    return materials;
  }

  public HashMap<IEntity, Integer> calculateMaterials(IEntity entity, int count) {
    var materials = new HashMap<IEntity, Integer>();
    if (count == 0) return materials;

    var materialComponents = entity.getComponents(MaterialComponent.class);
    for (var material : materialComponents) {
      var resource = material.resource;
      var quantity = material.quantity * count;

      materials.put(resource, materials.getOrDefault(resource, 0) + quantity);

      var resourceMaterials = calculateMaterials(resource, quantity);
      for (var resourceMaterial : resourceMaterials.entrySet()) {
        var currentValue = materials.getOrDefault(resourceMaterial.getKey(), 0);
        materials.put(resourceMaterial.getKey(), currentValue + resourceMaterial.getValue());
      }
    }
    
    return materials;
  }

  @Subscribe
  public void onVarbitChanged(VarbitChanged event) {
    if (event.getVarbitId() == GauntletPlugin.VARBIT_MAZE && event.getValue() == 1) {
      calculateResources();
    }
  }

	@Subscribe
	public void onConfigChanged(ConfigChanged event) {
		if (!event.getGroup().equals(GauntletPluginConfig.PLUGIN_GROUP_NAME)) return;

		calculateResources();
	}
}
