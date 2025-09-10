package com.trs.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import com.trs.GauntletPlugin;

import net.runelite.api.Client;

@Slf4j
@Singleton
public class LocationService extends AbstractService {

  private final Client client;

  @Inject
  public LocationService(Client client) {
    this.client = client;
  }

  public boolean isInGauntletMaze() {
    return client.getVarbitValue(GauntletPlugin.VARBIT_MAZE) == 1;
  }

  public boolean isInGauntletBoss() {
    return client.getVarbitValue(GauntletPlugin.VARBIT_BOSS) == 1;
  }
}
