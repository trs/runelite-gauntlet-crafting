package com.trs.entity;

import com.trs.type.IEntity;
import com.trs.type.IComponent;
import com.trs.component.NpcCategoryComponent;
import com.trs.component.NpcIDComponent;
import com.trs.component.ConfigItemComponent;
import com.trs.model.NpcCategory;
import com.trs.model.ConfigType;

import net.runelite.api.gameval.NpcID;
import java.awt.Color;

public enum NpcEntity implements IEntity {
  SPIDER(
    new NpcCategoryComponent(NpcCategory.WEAK),
    new NpcIDComponent(NpcID.CRYSTAL_SPIDER, NpcID.CRYSTAL_SPIDER_HM),
    new ConfigItemComponent<Boolean>(ConfigType.ENABLED, config -> config.npcOverlayWeakEnabled())
  ),
  RAT(
    new NpcCategoryComponent(NpcCategory.WEAK),
    new NpcIDComponent(NpcID.CRYSTAL_RAT, NpcID.CRYSTAL_RAT_HM),
    new ConfigItemComponent<Boolean>(ConfigType.ENABLED, config -> config.npcOverlayWeakEnabled())
  ),
  BAT(
    new NpcCategoryComponent(NpcCategory.WEAK),
    new NpcIDComponent(NpcID.CRYSTAL_BAT, NpcID.CRYSTAL_BAT_HM),
    new ConfigItemComponent<Boolean>(ConfigType.ENABLED, config -> config.npcOverlayWeakEnabled())
  ),
  UNICORN(
    new NpcCategoryComponent(NpcCategory.STRONG),
    new NpcIDComponent(NpcID.CRYSTAL_UNICORN, NpcID.CRYSTAL_UNICORN_HM),
    new ConfigItemComponent<Boolean>(ConfigType.ENABLED, config -> config.npcOverlayStrongEnabled())
  ),
  SCORPION(
    new NpcCategoryComponent(NpcCategory.STRONG),
    new NpcIDComponent(NpcID.CRYSTAL_SCORPION, NpcID.CRYSTAL_SCORPION_HM),
    new ConfigItemComponent<Boolean>(ConfigType.ENABLED, config -> config.npcOverlayStrongEnabled())
  ),
  WOLF(
    new NpcCategoryComponent(NpcCategory.STRONG),
    new NpcIDComponent(NpcID.CRYSTAL_WOLF, NpcID.CRYSTAL_WOLF_HM),
    new ConfigItemComponent<Boolean>(ConfigType.ENABLED, config -> config.npcOverlayStrongEnabled())
  ),
  BEAR(
    new NpcCategoryComponent(NpcCategory.DEMI_BOSS),
    new NpcIDComponent(NpcID.CRYSTAL_BEAR, NpcID.CRYSTAL_BEAR_HM),
    new ConfigItemComponent<Boolean>(ConfigType.ENABLED, config -> config.npcOverlayDemiBossEnabled()),
    new ConfigItemComponent<Color>(ConfigType.COLOR_OUTLINE, config -> config.npcOverlayBearColor())
  ),
  DRAGON(
    new NpcCategoryComponent(NpcCategory.DEMI_BOSS),
    new NpcIDComponent(NpcID.CRYSTAL_DRAGON, NpcID.CRYSTAL_DRAGON_HM),
    new ConfigItemComponent<Boolean>(ConfigType.ENABLED, config -> config.npcOverlayDemiBossEnabled()),
    new ConfigItemComponent<Color>(ConfigType.COLOR_OUTLINE, config -> config.npcOverlayDragonColor())
  ),
  DARK_BEAST(
    new NpcCategoryComponent(NpcCategory.DEMI_BOSS),
    new NpcIDComponent(NpcID.CRYSTAL_DARK_BEAST, NpcID.CRYSTAL_DARK_BEAST_HM),
    new ConfigItemComponent<Boolean>(ConfigType.ENABLED, config -> config.npcOverlayDemiBossEnabled()),
    new ConfigItemComponent<Color>(ConfigType.COLOR_OUTLINE, config -> config.npcOverlayDarkBeastColor())
  ),
  HUNLIEF(
    new NpcCategoryComponent(NpcCategory.BOSS),
    new NpcIDComponent(
      NpcID.CRYSTAL_HUNLLEF_MELEE, NpcID.CRYSTAL_HUNLLEF_RANGED, NpcID.CRYSTAL_HUNLLEF_MAGIC, NpcID.CRYSTAL_HUNLLEF_DEATH, NpcID.CRYSTAL_HUNLLEF_CRYSTALS,
      NpcID.CRYSTAL_HUNLLEF_MELEE_HM, NpcID.CRYSTAL_HUNLLEF_RANGED_HM, NpcID.CRYSTAL_HUNLLEF_MAGIC_HM, NpcID.CRYSTAL_HUNLLEF_DEATH_HM, NpcID.CRYSTAL_HUNLLEF_CRYSTALS_HM
    )
  );
  
  private final IComponent[] components;

  NpcEntity(IComponent... components) {
    this.components = components;
  }

  @Override
  public IComponent[] getComponents() {
    return components;
  }
}
