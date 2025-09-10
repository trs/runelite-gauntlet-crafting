package com.trs;

import java.awt.Color;

import com.trs.model.ItemTier;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Alpha;

@ConfigGroup(GauntletPluginConfig.PLUGIN_GROUP_NAME)
public interface GauntletPluginConfig extends Config
{
	public static final String PLUGIN_GROUP_NAME = "gauntlet-crafting";

	@ConfigSection(
		position = 0,
		name = "Crafting Settings",
		description = "Pick what to items to craft"
	)
	String sectionCrafting = "sectionCrafting";
	
	@ConfigItem(
		position = 0,
		keyName = "craftingOptionRanged",
		name = "Ranged",
		description = "Choose tier of ranged weapon to craft",
		section = sectionCrafting
	)
	default ItemTier craftingOptionRanged() {
			return ItemTier.PERFECTED;
	}
	
	@ConfigItem(
		position = 1,
		keyName = "craftingOptionMagic",
		name = "Magic",
		description = "Choose tier of magic weapon to craft",
		section = sectionCrafting
	)
	default ItemTier craftingOptionMagic() {
			return ItemTier.PERFECTED;
	}

	@ConfigItem(
		position = 2,
		keyName = "craftingOptionMelee",
		name = "Melee",
		description = "Choose tier of melee weapon to craft",
		section = sectionCrafting
	)
	default ItemTier craftingOptionMelee() {
			return ItemTier.NONE;
	}

	@ConfigItem(
		position = 3,
		keyName = "craftingOptionHelm",
		name = "Helm",
		description = "Choose tier of helm to craft",
		section = sectionCrafting
	)
	default ItemTier craftingOptionHelm() {
			return ItemTier.BASIC;
	}

	@ConfigItem(
		position = 4,
		keyName = "craftingOptionBody",
		name = "Body",
		description = "Choose tier of body to craft",
		section = sectionCrafting
	)
	default ItemTier craftingOptionBody() {
			return ItemTier.BASIC;
	}

	@ConfigItem(
		position = 5,
		keyName = "craftingOptionLegs",
		name = "Legs",
		description = "Choose tier of legs to craft",
		section = sectionCrafting
	)
	default ItemTier craftingOptionLegs() {
			return ItemTier.BASIC;
	}

	@ConfigItem(
		position = 6,
		keyName = "craftingTeleportCount",
		name = "Teleport crystal",
		description = "Number of teleport crystals to craft",
		section = sectionCrafting
	)
	default int craftingTeleportCount() {
		return 1;
	}

	@ConfigItem(
		position = 7,
		keyName = "craftingPotionCount",
		name = "Potion",
		description = "Number of potions/vials to craft",
		section = sectionCrafting
	)
	default int craftingPotionCount() {
		return 2;
	}

	@ConfigItem(
		position = 8,
		keyName = "craftingPaddlefishCount",
		name = "Food",
		description = "Number of paddlefish to cook",
		section = sectionCrafting
	)
	default int craftingPaddlefishCount() {
		return 24;
	}

	@ConfigItem(
		position = 9,
		keyName = "craftingCrystalPaddlefishCount",
		name = "Combo food",
		description = "Number of crystal paddlefish to craft",
		section = sectionCrafting
	)
	default int craftingCrystalPaddlefishCount() {
		return 0;
	}

	@ConfigItem(
		position = 10,
		keyName = "craftingEscapeCrystalCount",
		name = "Escape crystal",
		description = "Number of escape crystals to craft",
		section = sectionCrafting
	)
	default int craftingEscapeCrystalCount() {
		return 0;
	}

	@ConfigSection(
		position = 1,
		name = "Resource Overlay",
		description = "Resource overlay settings"
	)
	String sectionResourceOverlay = "sectionResourceOverlay";

	@ConfigItem(
		position = 0,
		keyName = "resourceOverlayEnabled",
		name = "Enabled",
		description = "Enable resource overlay",
		section = sectionResourceOverlay
	)
	default boolean resourceOverlayEnabled() {
		return false;
	}

	@ConfigSection(
		position = 2,
		name = "Color Settings",
		description = "Pick colors for each overlay element"
	)
	String sectionColors = "sectionColors";

	@ConfigItem(
		position = 0,
		keyName = "craftingHighlightColor",
		name = "Craft highlight",
		description = "Choose the color used to highlight craftable options in the crafting interface",
		section = sectionColors
	)
	@Alpha
	default Color craftingHighlightColor() {
		return new Color(0, 255, 0, 0);
	}
	@ConfigItem(
		position = 1,
		keyName = "craftingHighlightOutlineColor",
		name = "Craft outline",
		description = "Choose the color used to outline craftable options in the crafting interface",
		section = sectionColors
	)
	@Alpha
	default Color craftingHighlightOutlineColor() {
		return new Color(0, 255, 0, 255);
	}
	
	@ConfigItem(
		position = 2,
		keyName = "craftingHighlightStroke",
		name = "Craft stroke",
		description = "Choose the stoke width used to highlight the crafting interface",
		section = sectionColors
	)
	default int craftingHighlightStroke() {
		return 2;
	}
	
	@ConfigItem(
		position = 3,
		keyName = "craftingHighlightMissingMaterialsColor",
		name = "Missing materials highlight",
		description = "Choose the color used to fill options that need materials in the crafting interface",
		section = sectionColors
	)
	@Alpha
	default Color craftingHighlightMissingMaterialsColor() {
		return new Color(64, 64, 64, 80);
	}
	@ConfigItem(
		position = 4,
		keyName = "craftingHighlightMissingMaterialsOutlineColor",
		name = "Missing materials outline",
		description = "Choose the color used to outline options that need materials in the crafting interface",
		section = sectionColors
	)
	@Alpha
	default Color craftingHighlightMissingMaterialsOutlineColor() {
		return new Color(255, 0, 0, 200);
	}
	
	@ConfigItem(
		position = 5,
		keyName = "craftingHighlightMissingMaterialsStroke",
		name = "Missing materials stroke",
		description = "Choose the stoke width used to highlight the crafting interface",
		section = sectionColors
	)
	default int craftingHighlightMissingMaterialsStroke() {
		return 1;
	}
	
	@ConfigItem(
		position = 6,
		keyName = "craftingHighlightCompleteColor",
		name = "Done highlight",
		description = "Choose the color used to fill completed options in the crafting interface",
		section = sectionColors
	)
	@Alpha
	default Color craftingHighlightCompleteColor() {
		return new Color(64, 64, 64, 210);
	}
	@ConfigItem(
		position = 7,
		keyName = "craftingHighlightCompleteOutlineColor",
		name = "Done outline",
		description = "Choose the color used to outline completed options in the crafting interface",
		section = sectionColors
	)
	@Alpha
	default Color craftingHighlightCompleteOutlineColor() {
		return new Color(64, 64, 64, 0);
	}
	
	@ConfigItem(
		position = 8,
		keyName = "craftingHighlightCompleteStroke",
		name = "Done stroke",
		description = "Choose the stoke width used to highlight the crafting interface",
		section = sectionColors
	)
	default int craftingHighlightCompleteStroke() {
		return 0;
	}
}