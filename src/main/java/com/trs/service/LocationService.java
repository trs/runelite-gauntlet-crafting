package com.trs.service;

import lombok.extern.slf4j.Slf4j;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.api.Client;

@Slf4j
@Singleton
public class LocationService {
	private static final int VARBIT_MAZE = 9178;

  private final Client client;

  @Inject
  public LocationService(Client client) {
      this.client = client;
  }

	public boolean isInGauntlet() {
    return isInMaze();
	}

  public boolean isInMaze() {
    return client.getVarbitValue(VARBIT_MAZE) == 1;
  }
}
