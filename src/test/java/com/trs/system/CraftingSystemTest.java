package com.trs.system;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.trs.GauntletPluginConfig;
import com.trs.service.ConfigService;
import com.trs.model.CraftingIndex;
import com.trs.model.CraftingState;
import com.trs.model.ItemTier;

import net.runelite.api.Client;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;

@RunWith(MockitoJUnitRunner.class)
public class CraftingSystemTest {

    @Mock
    private Client client;
    
    @Mock
    private GauntletPluginConfig config;
    
    @Mock
    private ConfigService settingsService;
    
    @Mock
    private ItemContainer inventoryContainer;
    
    @Mock
    private ItemContainer equipmentContainer;
    
    private CraftingSystem craftingSystem;
    
    @Before
    public void setUp() {
        settingsService = new ConfigService(config);
        craftingSystem = new CraftingSystem(settingsService, client);
        
        // Default mock setup - using lenient to avoid unnecessary stubbing warnings
        lenient().when(client.getItemContainer(InventoryID.INV)).thenReturn(inventoryContainer);
        lenient().when(client.getItemContainer(InventoryID.WORN)).thenReturn(equipmentContainer);
        lenient().when(inventoryContainer.getItems()).thenReturn(new Item[0]);
        lenient().when(equipmentContainer.getItems()).thenReturn(new Item[0]);
    }

    @Test
    public void shouldReturnSkipWhenTierSettingIsNone() {
        // Given
        when(config.craftingOptionMelee()).thenReturn(ItemTier.NONE);

        // When
        CraftingState result = craftingSystem.getCraftingState(CraftingIndex.MELEE);

        // Then
        assertEquals(CraftingState.SKIP, result);
    }
    
    @Test
    public void shouldReturnNotEnoughMaterialsForBasicTier() {
        // Given
        when(config.craftingOptionHelm()).thenReturn(ItemTier.BASIC);

        // When
        CraftingState result = craftingSystem.getCraftingState(CraftingIndex.HELM);

        // Then
        assertEquals(CraftingState.NOT_ENOUGH_MATERIALS, result);
    }
    
    @Test
    public void shouldReturnNotEnoughMaterialsForAttunedTier() {
        // Given
        when(config.craftingOptionHelm()).thenReturn(ItemTier.ATTUNED);

        // When
        CraftingState result = craftingSystem.getCraftingState(CraftingIndex.HELM);

        // Then
        assertEquals(CraftingState.NOT_ENOUGH_MATERIALS, result);
    }
    
    @Test
    public void shouldReturnNotEnoughMaterialsForPerfectedTier() {
        // Given
        when(config.craftingOptionHelm()).thenReturn(ItemTier.PERFECTED);

        // When
        CraftingState result = craftingSystem.getCraftingState(CraftingIndex.HELM);

        // Then
        assertEquals(CraftingState.NOT_ENOUGH_MATERIALS, result);
    }
    
    @Test
    public void shouldReturnToCraftForAttunedTierWithBasicMaterials() {
        // Given
        when(config.craftingOptionHelm()).thenReturn(ItemTier.ATTUNED);
        
        Item[] items = new Item[]{
            createMockItem(ItemID.GAUNTLET_CRYSTAL_SHARD, 40),
            createMockItem(ItemID.GAUNTLET_ORE, 1),
            createMockItem(ItemID.GAUNTLET_BARK, 1),
            createMockItem(ItemID.GAUNTLET_FIBRE, 1)
        };
        when(inventoryContainer.getItems()).thenReturn(items);

        // When
        CraftingState result = craftingSystem.getCraftingState(CraftingIndex.HELM);

        // Then
        assertEquals(CraftingState.TO_CRAFT, result);
    }
    
    @Test
    public void shouldReturnToCraftForPerfectedTierWithAttunedMaterials() {
        // Given
        when(config.craftingOptionHelm()).thenReturn(ItemTier.PERFECTED);
        
        Item[] items = new Item[]{
            createMockItem(ItemID.GAUNTLET_CRYSTAL_SHARD, 60),
            createMockItem(ItemID.GAUNTLET_ORE, 1),
            createMockItem(ItemID.GAUNTLET_BARK, 1),
            createMockItem(ItemID.GAUNTLET_FIBRE, 1)
        };
        when(inventoryContainer.getItems()).thenReturn(items);

        when(equipmentContainer.contains(ItemID.GAUNTLET_HELMET_T1)).thenReturn(true);

        // When
        CraftingState result = craftingSystem.getCraftingState(CraftingIndex.HELM);

        // Then
        assertEquals(CraftingState.TO_CRAFT, result);
    }
    
    @Test
    public void shouldReturnToCraftForPerfectedTierWithoutAttunedMaterials() {
        // Given
        when(config.craftingOptionHelm()).thenReturn(ItemTier.PERFECTED);
        
        Item[] items = new Item[]{
            createMockItem(ItemID.GAUNTLET_CRYSTAL_SHARD, 40),
            createMockItem(ItemID.GAUNTLET_ORE, 1),
            createMockItem(ItemID.GAUNTLET_BARK, 1),
            createMockItem(ItemID.GAUNTLET_FIBRE, 1)
        };
        when(inventoryContainer.getItems()).thenReturn(items);

        // When
        CraftingState result = craftingSystem.getCraftingState(CraftingIndex.HELM);

        // Then
        assertEquals(CraftingState.TO_CRAFT, result);
    }
    
    @Test
    public void shouldReturnNotEnoughMaterialsForPerfectedTierWithoutAttunedMaterials() {
        // Given
        when(config.craftingOptionHelm()).thenReturn(ItemTier.PERFECTED);
        
        Item[] items = new Item[]{
            createMockItem(ItemID.GAUNTLET_CRYSTAL_SHARD, 30), // Not enough shards
            createMockItem(ItemID.GAUNTLET_ORE, 1),
            createMockItem(ItemID.GAUNTLET_BARK, 1),
            createMockItem(ItemID.GAUNTLET_FIBRE, 1)
        };
        when(inventoryContainer.getItems()).thenReturn(items);

        // Has basic helm
        when(equipmentContainer.contains(ItemID.GAUNTLET_HELMET_T1)).thenReturn(true);

        // When
        CraftingState result = craftingSystem.getCraftingState(CraftingIndex.HELM);

        // Then
        assertEquals(CraftingState.NOT_ENOUGH_MATERIALS, result);
    }
    
    @Test
    public void shouldReturnNotEnoughMaterialsForPerfectedTierWithBasicWeaponAndMaterials() {
        // Given
        when(config.craftingOptionMelee()).thenReturn(ItemTier.PERFECTED);
        
        Item[] items = new Item[]{
            createMockItem(ItemID.GAUNTLET_CRYSTAL_SHARD, 20),
            createMockItem(ItemID.GAUNTLET_GENERIC_COMPONENT, 1),
        };
        when(inventoryContainer.getItems()).thenReturn(items);

        when(equipmentContainer.contains(ItemID.GAUNTLET_MELEE_T2)).thenReturn(true);

        // When
        CraftingState result = craftingSystem.getCraftingState(CraftingIndex.MELEE);

        // Then
        assertEquals(CraftingState.NOT_ENOUGH_MATERIALS, result);
    }

    private Item createMockItem(int id, int quantity) {
        Item item = mock(Item.class);
        when(item.getId()).thenReturn(id);
        when(item.getQuantity()).thenReturn(quantity);
        return item;
    }
}
