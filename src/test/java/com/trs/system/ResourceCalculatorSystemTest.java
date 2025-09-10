package com.trs.system;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.trs.GauntletPluginConfig;
import com.trs.model.ItemTier;
import com.trs.entity.ItemEntity;
import com.trs.type.IEntity;
import com.trs.service.ConfigService;

import net.runelite.client.eventbus.EventBus;

@RunWith(MockitoJUnitRunner.class)
public class ResourceCalculatorSystemTest {
  @Mock
  private EventBus eventBus;

  @Mock
  private GauntletPluginConfig config;

  private ConfigService configService;

  private ResourceCalculatorSystem resourceCalculatorSystem;

  @Before
  public void setUp() {
    configService = new ConfigService(config);
    resourceCalculatorSystem = new ResourceCalculatorSystem(eventBus, configService);
  }

  @Test
  public void shouldCalculateIndividualHelmMaterials() {
    var materials = resourceCalculatorSystem.calculateMaterials(ItemEntity.HELM_T1, 1);

    assertEquals((Integer) 40, materials.get(ItemEntity.CRYSTAL_SHARD));
    assertEquals((Integer) 1, materials.get(ItemEntity.ORE));
    assertEquals((Integer) 1, materials.get(ItemEntity.BARK));
    assertEquals((Integer) 1, materials.get(ItemEntity.FIBRE));
  }

  @Test
  public void shouldCalculateAllMaterialsForBasicTier() {
    when(config.craftingOptionHelm()).thenReturn(ItemTier.BASIC);

    @SuppressWarnings("unchecked")
    var materials = resourceCalculatorSystem.calculateAllMaterials(new IEntity[] {
      ItemEntity.HELM_T1,
      ItemEntity.HELM_T2,
      ItemEntity.HELM_T3
    });

    assertEquals((Integer) 40, materials.get(ItemEntity.CRYSTAL_SHARD));
    assertEquals((Integer) 1, materials.get(ItemEntity.ORE));
    assertEquals((Integer) 1, materials.get(ItemEntity.BARK));
    assertEquals((Integer) 1, materials.get(ItemEntity.FIBRE));
  }

  @Test
  public void shouldCalculateAllMaterialsForPerfectedTier() {
    when(config.craftingOptionMelee()).thenReturn(ItemTier.PERFECTED);

    @SuppressWarnings("unchecked")
    var materials = resourceCalculatorSystem.calculateAllMaterials(new IEntity[] {
      ItemEntity.MELEE_T1,
      ItemEntity.MELEE_T2,
      ItemEntity.MELEE_T3
    });

    assertEquals((Integer) 80, materials.get(ItemEntity.CRYSTAL_SHARD));
    assertEquals((Integer) 1, materials.get(ItemEntity.WEAPON_FRAME));
    assertEquals((Integer) 1, materials.get(ItemEntity.MELEE_COMPONENT));
  }

  @Test
  public void shouldCalculateAllMaterialsForPotion() {
    when(config.craftingPotionCount()).thenReturn(2);

    @SuppressWarnings("unchecked")
    var materials = resourceCalculatorSystem.calculateAllMaterials(new IEntity[] {
      ItemEntity.POTION
    });

    assertEquals((Integer) 2, materials.get(ItemEntity.HERB));
    assertEquals((Integer) 2, materials.get(ItemEntity.VIAL));
    assertEquals((Integer) 40, materials.get(ItemEntity.CRYSTAL_SHARD));
  }
}
