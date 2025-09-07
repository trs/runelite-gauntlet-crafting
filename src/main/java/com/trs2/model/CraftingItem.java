package com.trs2.model;

import java.util.Set;

import com.trs2.data.GauntletItems;

import java.util.HashSet;

import net.runelite.api.gameval.ItemID;

public enum CraftingItem {
  HELM_T1(
    new int[] {
      ItemID.GAUNTLET_HELMET_T1,
      ItemID.GAUNTLET_HELMET_T1_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 40),
      new CraftingMaterial(GauntletItems.ORE_ITEM_IDS, 1),
      new CraftingMaterial(GauntletItems.BARK_ITEM_IDS, 1),
      new CraftingMaterial(GauntletItems.FIBRE_ITEM_IDS, 1)
    }
  ),
  HELM_T2(
    new int[] {
      ItemID.GAUNTLET_HELMET_T2,
      ItemID.GAUNTLET_HELMET_T2_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 60),
      new CraftingMaterial(GauntletItems.ORE_ITEM_IDS, 1),
      new CraftingMaterial(GauntletItems.BARK_ITEM_IDS, 1),
      new CraftingMaterial(GauntletItems.FIBRE_ITEM_IDS, 1)
    }
  ),
  HELM_T3(
    new int[] {
      ItemID.GAUNTLET_HELMET_T3,
      ItemID.GAUNTLET_HELMET_T3_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 80),
      new CraftingMaterial(GauntletItems.ORE_ITEM_IDS, 2),
      new CraftingMaterial(GauntletItems.BARK_ITEM_IDS, 2),
      new CraftingMaterial(GauntletItems.FIBRE_ITEM_IDS, 2)
    }
  ),
  
  BODY_T1(
    new int[] {
      ItemID.GAUNTLET_CHESTPLATE_T1,
      ItemID.GAUNTLET_CHESTPLATE_T1_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 40),
      new CraftingMaterial(GauntletItems.ORE_ITEM_IDS, 1),
      new CraftingMaterial(GauntletItems.BARK_ITEM_IDS, 1),
      new CraftingMaterial(GauntletItems.FIBRE_ITEM_IDS, 1)
    }
  ),
  BODY_T2(
    new int[] {
      ItemID.GAUNTLET_CHESTPLATE_T2,
      ItemID.GAUNTLET_CHESTPLATE_T2_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 60),
      new CraftingMaterial(GauntletItems.ORE_ITEM_IDS, 2),
      new CraftingMaterial(GauntletItems.BARK_ITEM_IDS, 2),
      new CraftingMaterial(GauntletItems.FIBRE_ITEM_IDS, 2)
    }
  ),
  BODY_T3(
    new int[] {
      ItemID.GAUNTLET_CHESTPLATE_T3,
      ItemID.GAUNTLET_CHESTPLATE_T3_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 80),
      new CraftingMaterial(GauntletItems.ORE_ITEM_IDS, 2),
      new CraftingMaterial(GauntletItems.BARK_ITEM_IDS, 2),
      new CraftingMaterial(GauntletItems.FIBRE_ITEM_IDS, 2)
    }
  ),

  LEGS_T1(
    new int[] {
      ItemID.GAUNTLET_PLATELEGS_T1,
      ItemID.GAUNTLET_PLATELEGS_T1_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 40),
      new CraftingMaterial(GauntletItems.ORE_ITEM_IDS, 1),
      new CraftingMaterial(GauntletItems.BARK_ITEM_IDS, 1),
      new CraftingMaterial(GauntletItems.FIBRE_ITEM_IDS, 1)
    }
  ),
  LEGS_T2(
    new int[] {
      ItemID.GAUNTLET_PLATELEGS_T2,
      ItemID.GAUNTLET_PLATELEGS_T2_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 60),
      new CraftingMaterial(GauntletItems.ORE_ITEM_IDS, 1),
      new CraftingMaterial(GauntletItems.BARK_ITEM_IDS, 1),
      new CraftingMaterial(GauntletItems.FIBRE_ITEM_IDS, 1)
    }
  ),
  LEGS_T3(
    new int[] {
      ItemID.GAUNTLET_PLATELEGS_T3,
      ItemID.GAUNTLET_PLATELEGS_T3_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 80),
      new CraftingMaterial(GauntletItems.ORE_ITEM_IDS, 2),
      new CraftingMaterial(GauntletItems.BARK_ITEM_IDS, 2),
      new CraftingMaterial(GauntletItems.FIBRE_ITEM_IDS, 2)
    }
  ),

  MELEE_T1(
    new int[] {
      ItemID.GAUNTLET_MELEE_T1,
      ItemID.GAUNTLET_MELEE_T1_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 20),
      new CraftingMaterial(GauntletItems.WEAPON_FRAME_ITEM_IDS, 1),
    }
  ),
  MELEE_T2(
    new int[] {
      ItemID.GAUNTLET_MELEE_T2,
      ItemID.GAUNTLET_MELEE_T2_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 60),
    }
  ),
  MELEE_T3(
    new int[] {
      ItemID.GAUNTLET_MELEE_T3,
      ItemID.GAUNTLET_MELEE_T3_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(new int[] { ItemID.GAUNTLET_MELEE_COMPONENT, ItemID.GAUNTLET_MELEE_COMPONENT_HM }, 1),
    }
  ),
  
  RANGED_T1(
    new int[] {
      ItemID.GAUNTLET_RANGED_T1,
      ItemID.GAUNTLET_RANGED_T1_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 20),
      new CraftingMaterial(GauntletItems.WEAPON_FRAME_ITEM_IDS, 1),
    }
  ),
  RANGED_T2(
    new int[] {
      ItemID.GAUNTLET_RANGED_T2,
      ItemID.GAUNTLET_RANGED_T2_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 60),
    }
  ),
  RANGED_T3(
    new int[] {
      ItemID.GAUNTLET_RANGED_T3,
      ItemID.GAUNTLET_RANGED_T3_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(new int[] { ItemID.GAUNTLET_RANGED_COMPONENT, ItemID.GAUNTLET_RANGED_COMPONENT_HM }, 1),
    }
  ),
  
  MAGIC_T1(
    new int[] {
      ItemID.GAUNTLET_MAGIC_T1,
      ItemID.GAUNTLET_MAGIC_T1_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 20),
      new CraftingMaterial(GauntletItems.WEAPON_FRAME_ITEM_IDS, 1),
    }
  ),
  MAGIC_T2(
    new int[] {
      ItemID.GAUNTLET_MAGIC_T2,
      ItemID.GAUNTLET_MAGIC_T2_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 60),
    }
  ),
  MAGIC_T3(
    new int[] {
      ItemID.GAUNTLET_MAGIC_T3,
      ItemID.GAUNTLET_MAGIC_T3_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(new int[] { ItemID.GAUNTLET_MAGIC_COMPONENT, ItemID.GAUNTLET_MAGIC_COMPONENT_HM }, 1),
    }
  ),

  TELEPORT_CRYSTAL(
    new int[] {
      ItemID.GAUNTLET_TELEPORT_CRYSTAL,
      ItemID.GAUNTLET_TELEPORT_CRYSTAL_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 40),
    }
  ),

  VIAL(
    new int[] {
      ItemID.GAUNTLET_VIAL_EMPTY,
      ItemID.GAUNTLET_VIAL_WATER,
      ItemID.GAUNTLET_POTION_UNFINISHED,
      ItemID.GAUNTLET_POTION_1,
      ItemID.GAUNTLET_POTION_2,
      ItemID.GAUNTLET_POTION_3,
      ItemID.GAUNTLET_POTION_4
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 10),
    }
  ),

  PADDLEFISH(
    new int[] {
      ItemID.GAUNTLET_COMBO_FOOD,
      ItemID.GAUNTLET_COMBO_FOOD_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(new int[] { ItemID.GAUNTLET_FOOD }, 1),
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 10),
    }
  ),

  ESCAPE_CRYSTAL(
    new int[] {
      ItemID.GAUNTLET_ESCAPE_CRYSTAL,
      ItemID.GAUNTLET_ESCAPE_CRYSTAL_HM
    }, 
    new CraftingMaterial[] {
      new CraftingMaterial(GauntletItems.CRYSTAL_SHARD_ITEM_IDS, 200),
    }
  );


  private final int[] itemIDs;
  private final CraftingMaterial[] materials;

  CraftingItem(int[] itemIDs, CraftingMaterial[] materials) {
    this.itemIDs = itemIDs;
    this.materials = materials;
  }

  public int[] getItemIDs() {
    return itemIDs;
  }

  public CraftingMaterial[] getMaterials() {
    return materials;
  }
}