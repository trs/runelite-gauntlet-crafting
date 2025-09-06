package com.trs;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import com.trs.service.OverlayService;

@Slf4j
@PluginDescriptor(name = "Gauntlet Crafting")
public class GauntletPlugin extends Plugin
{
	@Inject private OverlayService overlayService;

	@Override
	protected void startUp() throws Exception
	{
		overlayService.registerOverlays();
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayService.unregisterOverlays();
	}

	@Provides
	GauntletPluginConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GauntletPluginConfig.class);
	}
}