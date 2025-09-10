package com.trs;

import com.google.inject.Provides;
import com.trs.service.OverlayService;
import com.trs.service.LocationService;
import com.trs.system.ResourceCollectorSystem;
import com.trs.system.ResourceCalculatorSystem;
import com.trs.system.GameObjectSystem;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(name = "Gauntlet Crafting")
public class GauntletPlugin extends Plugin {
  public static final int VARBIT_MAZE = 9178;
  public static final int VARBIT_BOSS = 9177;

	@Inject private OverlayService overlayService;
	@Inject private LocationService locationService;
	@Inject private GameObjectSystem gameObjectSystem;
	@Inject private ResourceCollectorSystem resourceCollectorSystem;
	@Inject private ResourceCalculatorSystem resourceCalculatorSystem;

	@Override
	protected void startUp() throws Exception
	{
		overlayService.startUp();
		gameObjectSystem.startUp();
		locationService.startUp();
		resourceCollectorSystem.startUp();
		resourceCalculatorSystem.startUp();
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayService.shutDown();
		gameObjectSystem.shutDown();
		locationService.shutDown();
		resourceCollectorSystem.shutDown();
		resourceCalculatorSystem.shutDown();
	}

	@Provides
	GauntletPluginConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GauntletPluginConfig.class);
	}
}