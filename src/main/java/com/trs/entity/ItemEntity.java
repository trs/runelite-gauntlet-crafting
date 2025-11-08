package com.trs.entity;

import com.trs.type.IComponent;
import com.trs.component.ItemIDComponent;
import com.trs.component.ItemCategoryComponent;
import com.trs.component.MaterialComponent;
import com.trs.component.ObjectIDComponent;
import com.trs.component.CollectPatternComponent;
import com.trs.model.ItemCategory;
import com.trs.model.ItemTier;
import com.trs.type.IEntity;

import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.ObjectID;

public enum ItemEntity implements IEntity {
  // Resources
  ORE(
    new ItemCategoryComponent(ItemCategory.ORE),
    new ItemIDComponent(ItemID.GAUNTLET_ORE, ItemID.GAUNTLET_ORE_HM),
    new ObjectIDComponent(ObjectID.GAUNTLET_ROCK, ObjectID.GAUNTLET_ROCK_HM),
    new CollectPatternComponent("You manage to mine some ore\\.")
  ),
  BARK(
    new ItemCategoryComponent(ItemCategory.BARK),
    new ItemIDComponent(ItemID.GAUNTLET_BARK, ItemID.GAUNTLET_BARK_HM),
    new ObjectIDComponent(ObjectID.GAUNTLET_TREE, ObjectID.GAUNTLET_TREE_HM),
    new CollectPatternComponent("You get some bark\\.")
  ),
  FIBRE(
    new ItemCategoryComponent(ItemCategory.FIBRE),
    new ItemIDComponent(ItemID.GAUNTLET_FIBRE, ItemID.GAUNTLET_FIBRE_HM),
    new ObjectIDComponent(ObjectID.GAUNTLET_FIBRE, ObjectID.GAUNTLET_FIBRE_HM),
    new CollectPatternComponent("You pick some fibre from the plant\\.")
  ),
  FISH(
    new ItemCategoryComponent(ItemCategory.FISH),
    new ItemIDComponent(ItemID.GAUNTLET_RAW_FOOD),
    new ObjectIDComponent(ObjectID.GAUNTLET_POND, ObjectID.GAUNTLET_POND_HM),
    new CollectPatternComponent("You manage to catch a fish\\.")
  ),
  HERB(
    new ItemCategoryComponent(ItemCategory.HERB),
    new ItemIDComponent(ItemID.GAUNTLET_HERB, ItemID.GAUNTLET_HERB_HM),
    new ObjectIDComponent(ObjectID.GAUNTLET_HERB, ObjectID.GAUNTLET_HERB_HM),
    new CollectPatternComponent("You pick a herb from the roots\\.")
  ),
  // Materials
  CRYSTAL_SHARD(
    new ItemCategoryComponent(ItemCategory.CRYSTAL_SHARD),
    new ItemIDComponent(ItemID.GAUNTLET_CRYSTAL_SHARD, ItemID.GAUNTLET_CRYSTAL_SHARD_HM),
    new CollectPatternComponent("^.+ drop:\\s+((?<quantity>\\d+) x )?(?:Crystal|Corrupted) shards$"),
    new CollectPatternComponent("You find (?<quantity>\\d+) (?:crystal|corrupted) shards\\."),
    new CollectPatternComponent("You break down your (?:\\w+) into (?<quantity>\\d+) shards\\.")
  ),
  WEAPON_FRAME(
    new ItemCategoryComponent(ItemCategory.WEAPON_FRAME),
    new ItemIDComponent(ItemID.GAUNTLET_GENERIC_COMPONENT, ItemID.GAUNTLET_GENERIC_COMPONENT_HM),
    new CollectPatternComponent("^.+ drop:\\s+((?<quantity>\\d+) x )?Weapon frame$")
  ),
  MAGIC_COMPONENT(
    new ItemCategoryComponent(ItemCategory.MAGIC_COMPONENT),
    new ItemIDComponent(ItemID.GAUNTLET_MAGIC_COMPONENT, ItemID.GAUNTLET_MAGIC_COMPONENT_HM),
    new CollectPatternComponent("^.+ drop:\\s+((?<quantity>\\d+) x )?(?:Crystalline|Corrupted) bowstring$")
  ),
  RANGED_COMPONENT(
    new ItemCategoryComponent(ItemCategory.RANGED_COMPONENT),
    new ItemIDComponent(ItemID.GAUNTLET_RANGED_COMPONENT, ItemID.GAUNTLET_RANGED_COMPONENT_HM),
    new CollectPatternComponent("^.+ drop:\\s+((?<quantity>\\d+) x )?(?:Crystal|Corrupted) orb$")
  ),
  MELEE_COMPONENT(
    new ItemCategoryComponent(ItemCategory.MELEE_COMPONENT),
    new ItemIDComponent(ItemID.GAUNTLET_MELEE_COMPONENT, ItemID.GAUNTLET_MELEE_COMPONENT_HM),
    new CollectPatternComponent("^.+ drop:\\s+((?<quantity>\\d+) x )?(?:Crystal|Corrupted) spike$")
  ),
  FOOD(
    new ItemCategoryComponent(ItemCategory.FOOD),
    new ItemIDComponent(ItemID.GAUNTLET_FOOD),
    new MaterialComponent(ItemEntity.FISH, 1)
  ),
  // Crafting
  COMBO_FOOD(
    new ItemCategoryComponent(ItemCategory.COMBO_FOOD),
    new ItemIDComponent(
      ItemID.GAUNTLET_COMBO_FOOD,
      ItemID.GAUNTLET_COMBO_FOOD_HM
    ),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 10),
    new MaterialComponent(ItemEntity.FOOD, 1)
  ),
  ESCAPE_CRYSTAL(
    new ItemCategoryComponent(ItemCategory.ESCAPE_CRYSTAL),
    new ItemIDComponent(ItemID.GAUNTLET_ESCAPE_CRYSTAL, ItemID.GAUNTLET_ESCAPE_CRYSTAL_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 200)
  ),
  TELEPORT_CRYSTAL(
    new ItemCategoryComponent(ItemCategory.TELEPORT_CRYSTAL),
    new ItemIDComponent(ItemID.GAUNTLET_TELEPORT_CRYSTAL, ItemID.GAUNTLET_TELEPORT_CRYSTAL_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 40)
  ),
  VIAL(
    new ItemCategoryComponent(ItemCategory.VIAL),
    new ItemIDComponent(
      ItemID.GAUNTLET_VIAL_EMPTY,
      ItemID.GAUNTLET_VIAL_WATER
    ),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 10)
  ),
  POTION(
    new ItemCategoryComponent(ItemCategory.POTION),
    new ItemIDComponent(
      ItemID.GAUNTLET_POTION_1,
      ItemID.GAUNTLET_POTION_2,
      ItemID.GAUNTLET_POTION_3,
      ItemID.GAUNTLET_POTION_4
    ),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 10),
    new MaterialComponent(ItemEntity.VIAL, 1),
    new MaterialComponent(ItemEntity.HERB, 1)
  ),

  HELM_T1(
    new ItemCategoryComponent(ItemCategory.HELM, ItemTier.BASIC),
    new ItemIDComponent(ItemID.GAUNTLET_HELMET_T1, ItemID.GAUNTLET_HELMET_T1_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 40),
    new MaterialComponent(ItemEntity.ORE, 1),
    new MaterialComponent(ItemEntity.BARK, 1),
    new MaterialComponent(ItemEntity.FIBRE, 1)
  ),
  HELM_T2(
    new ItemCategoryComponent(ItemCategory.HELM, ItemTier.ATTUNED),
    new ItemIDComponent(ItemID.GAUNTLET_HELMET_T2, ItemID.GAUNTLET_HELMET_T2_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 60),
    new MaterialComponent(ItemEntity.ORE, 1),
    new MaterialComponent(ItemEntity.BARK, 1),
    new MaterialComponent(ItemEntity.FIBRE, 1)
  ),
  HELM_T3(
    new ItemCategoryComponent(ItemCategory.HELM, ItemTier.PERFECTED),
    new ItemIDComponent(ItemID.GAUNTLET_HELMET_T3, ItemID.GAUNTLET_HELMET_T3_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 80),
    new MaterialComponent(ItemEntity.ORE, 2),
    new MaterialComponent(ItemEntity.BARK, 2),
    new MaterialComponent(ItemEntity.FIBRE, 2)
  ),
  BODY_T1(
    new ItemCategoryComponent(ItemCategory.BODY, ItemTier.BASIC),
    new ItemIDComponent(ItemID.GAUNTLET_CHESTPLATE_T1, ItemID.GAUNTLET_CHESTPLATE_T1_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 40),
    new MaterialComponent(ItemEntity.ORE, 1),
    new MaterialComponent(ItemEntity.BARK, 1),
    new MaterialComponent(ItemEntity.FIBRE, 1)
  ),
  BODY_T2(
    new ItemCategoryComponent(ItemCategory.BODY, ItemTier.ATTUNED),
    new ItemIDComponent(ItemID.GAUNTLET_CHESTPLATE_T2, ItemID.GAUNTLET_CHESTPLATE_T2_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 60),
    new MaterialComponent(ItemEntity.ORE, 1),
    new MaterialComponent(ItemEntity.BARK, 1),
    new MaterialComponent(ItemEntity.FIBRE, 1)
  ),
  BODY_T3(
    new ItemCategoryComponent(ItemCategory.BODY, ItemTier.PERFECTED),
    new ItemIDComponent(ItemID.GAUNTLET_CHESTPLATE_T3, ItemID.GAUNTLET_CHESTPLATE_T3_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 80),
    new MaterialComponent(ItemEntity.ORE, 2),
    new MaterialComponent(ItemEntity.BARK, 2),
    new MaterialComponent(ItemEntity.FIBRE, 2)
  ),
  LEGS_T1(
    new ItemCategoryComponent(ItemCategory.LEGS, ItemTier.BASIC),
    new ItemIDComponent(ItemID.GAUNTLET_PLATELEGS_T1, ItemID.GAUNTLET_PLATELEGS_T1_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 40),
    new MaterialComponent(ItemEntity.ORE, 1),
    new MaterialComponent(ItemEntity.BARK, 1),
    new MaterialComponent(ItemEntity.FIBRE, 1)
  ),
  LEGS_T2(
    new ItemCategoryComponent(ItemCategory.LEGS, ItemTier.ATTUNED),
    new ItemIDComponent(ItemID.GAUNTLET_PLATELEGS_T2, ItemID.GAUNTLET_PLATELEGS_T2_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 60),
    new MaterialComponent(ItemEntity.ORE, 1),
    new MaterialComponent(ItemEntity.BARK, 1),
    new MaterialComponent(ItemEntity.FIBRE, 1)
  ),
  LEGS_T3(
    new ItemCategoryComponent(ItemCategory.LEGS, ItemTier.PERFECTED),
    new ItemIDComponent(ItemID.GAUNTLET_PLATELEGS_T3, ItemID.GAUNTLET_PLATELEGS_T3_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 80),
    new MaterialComponent(ItemEntity.ORE, 2),
    new MaterialComponent(ItemEntity.BARK, 2),
    new MaterialComponent(ItemEntity.FIBRE, 2)
  ),
  MELEE_T1(
    new ItemCategoryComponent(ItemCategory.MELEE, ItemTier.BASIC),
    new ItemIDComponent(ItemID.GAUNTLET_MELEE_T1, ItemID.GAUNTLET_MELEE_T1_HM),
    new MaterialComponent(ItemEntity.WEAPON_FRAME, 1),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 20)
  ),
  MELEE_T2(
    new ItemCategoryComponent(ItemCategory.MELEE, ItemTier.ATTUNED),
    new ItemIDComponent(ItemID.GAUNTLET_MELEE_T2, ItemID.GAUNTLET_MELEE_T2_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 60)
  ),
  MELEE_T3(
    new ItemCategoryComponent(ItemCategory.MELEE, ItemTier.PERFECTED),
    new ItemIDComponent(ItemID.GAUNTLET_MELEE_T3, ItemID.GAUNTLET_MELEE_T3_HM),
    new MaterialComponent(ItemEntity.MELEE_COMPONENT, 1)
  ),
  MAGIC_T1(
    new ItemCategoryComponent(ItemCategory.MAGIC, ItemTier.BASIC),
    new ItemIDComponent(ItemID.GAUNTLET_MAGIC_T1, ItemID.GAUNTLET_MAGIC_T1_HM),
    new MaterialComponent(ItemEntity.WEAPON_FRAME, 1),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 20)
  ),
  MAGIC_T2(
    new ItemCategoryComponent(ItemCategory.MAGIC, ItemTier.ATTUNED),
    new ItemIDComponent(ItemID.GAUNTLET_MAGIC_T2, ItemID.GAUNTLET_MAGIC_T2_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 60)
  ),
  MAGIC_T3(
    new ItemCategoryComponent(ItemCategory.MAGIC, ItemTier.PERFECTED),
    new ItemIDComponent(ItemID.GAUNTLET_MAGIC_T3, ItemID.GAUNTLET_MAGIC_T3_HM),
    new MaterialComponent(ItemEntity.MAGIC_COMPONENT, 1)
  ),
  RANGED_T1(
    new ItemCategoryComponent(ItemCategory.RANGED, ItemTier.BASIC),
    new ItemIDComponent(ItemID.GAUNTLET_RANGED_T1, ItemID.GAUNTLET_RANGED_T1_HM),
    new MaterialComponent(ItemEntity.WEAPON_FRAME, 1),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 20)
  ),
  RANGED_T2(
    new ItemCategoryComponent(ItemCategory.RANGED, ItemTier.ATTUNED),
    new ItemIDComponent(ItemID.GAUNTLET_RANGED_T2, ItemID.GAUNTLET_RANGED_T2_HM),
    new MaterialComponent(ItemEntity.CRYSTAL_SHARD, 60)
  ),  
  RANGED_T3(
    new ItemCategoryComponent(ItemCategory.RANGED, ItemTier.PERFECTED),
    new ItemIDComponent(ItemID.GAUNTLET_RANGED_T3, ItemID.GAUNTLET_RANGED_T3_HM),
    new MaterialComponent(ItemEntity.RANGED_COMPONENT, 1)
  );

  private final IComponent[] components;

  ItemEntity(IComponent... components) {
    this.components = components;
  }

  @Override
  public IComponent[] getComponents() {
    return components;
  }
}
