package com.trs;

import com.google.inject.Provides;
import com.trs.service.CraftingOverlayService;
import com.trs.service.LocationService;
import com.trs.service.ResourceCalculator;
import com.trs.service.ResourceService;
import com.trs.service.ResourceOverlayService;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.eventbus.EventBus;

@Slf4j
@PluginDescriptor(name = "Gauntlet Crafting")
public class GauntletPlugin extends Plugin
{
	@Inject private EventBus eventBus;
	@Inject private CraftingOverlayService craftingOverlayService;
	@Inject private ResourceOverlayService resourceOverlayService;
	@Inject private ResourceCalculator resourceCalculator;
	@Inject private LocationService locationService;
	@Inject private ResourceService resourceService;

	@Override
	protected void startUp() throws Exception
	{
		resourceOverlayService.registerOverlays();
		eventBus.register(resourceService);
	}

	@Override
	protected void shutDown() throws Exception
	{
		resourceOverlayService.unregisterOverlays();
		eventBus.unregister(resourceService);
	}

	@Provides
	GauntletPluginConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GauntletPluginConfig.class);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event) {
		if (!event.getGroup().equals(GauntletPluginConfig.PLUGIN_GROUP_NAME)) return;

		resourceCalculator.calculateResources();
	}

	@Subscribe
	public void onVarbitChanged(final VarbitChanged event)
	{
		if (locationService.isInGauntletMaze()) {
			resourceCalculator.calculateResources();
			craftingOverlayService.registerOverlays();
		} else {
			craftingOverlayService.unregisterOverlays();
		}
	}
}