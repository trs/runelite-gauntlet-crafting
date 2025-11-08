package com.trs;

import java.awt.Color;

import com.trs.model.ItemTier;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Units;
import net.runelite.client.config.Range;

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

	// Resource Overlay Section

	@ConfigSection(
		position = 1,
		name = "Resource Overlay",
		description = "Resource overlay settings",
		closedByDefault = true
	)
	String sectionResourceOverlay = "sectionResourceOverlay";

	@ConfigItem(
		position = 0,
		keyName = "resourceOverlayOreEnabled",
		name = "Ores",
		description = "",
		section = sectionResourceOverlay
	)
	default boolean resourceOverlayOreEnabled() {
		return true;
	}
	
	@ConfigItem(
		position = 1,
		keyName = "resourceOverlayOreOutlineColor",
		name = "Ore outline color",
		description = "",
		section = sectionResourceOverlay
	)
	@Alpha
	default Color resourceOverlayOreOutlineColor() {
		return new Color(255, 0, 0, 255);
	}
	
	@ConfigItem(
		position = 2,
		keyName = "resourceOverlayOreFillColor",
		name = "Ore fill color",
		description = "",
		section = sectionResourceOverlay
	)
	@Alpha
	default Color resourceOverlayOreFillColor() {
		return new Color(255, 0, 0, 50);
	}

	@ConfigItem(
		position = 3,
		keyName = "resourceOverlayBarkEnabled",
		name = "Bark",
		description = "",
		section = sectionResourceOverlay
	)
	default boolean resourceOverlayBarkEnabled() {
		return true;
	}
	
	@ConfigItem(
		position = 4,
		keyName = "resourceOverlayBarkOutlineColor",
		name = "Bark outline color",
		description = "",
		section = sectionResourceOverlay
	)
	@Alpha
	default Color resourceOverlayBarkOutlineColor() {
		return new Color(0, 255, 0, 255);
	}
	
	@ConfigItem(
		position = 5,
		keyName = "resourceOverlayBarkFillColor",
		name = "Bark fill color",
		description = "",
		section = sectionResourceOverlay
	)
	@Alpha
	default Color resourceOverlayBarkFillColor() {
		return new Color(0, 255, 0, 50);
	}

	@ConfigItem(
		position = 6,
		keyName = "resourceOverlayFibreEnabled",
		name = "Fibre",
		description = "",
		section = sectionResourceOverlay
	)
	default boolean resourceOverlayFibreEnabled() {
		return true;
	}
	
	@ConfigItem(
		position = 7,
		keyName = "resourceOverlayFibreOutlineColor",
		name = "Fibre outline color",
		description = "",
		section = sectionResourceOverlay
	)
	@Alpha
	default Color resourceOverlayFibreOutlineColor() {
		return new Color(255, 255, 255, 255);
	}
	
	@ConfigItem(
		position = 8,
		keyName = "resourceOverlayFibreFillColor",
		name = "Fibre fill color",
		description = "",
		section = sectionResourceOverlay
	)
	@Alpha
	default Color resourceOverlayFibreFillColor() {
		return new Color(255, 255, 255, 50);
	}

	@ConfigItem(
		position = 9,
		keyName = "resourceOverlayHerbEnabled",
		name = "Herb",
		description = "",
		section = sectionResourceOverlay
	)
	default boolean resourceOverlayHerbEnabled() {
		return true;
	}
	
	@ConfigItem(
		position = 10,
		keyName = "resourceOverlayHerbOutlineColor",
		name = "Herb outline color",
		description = "",
		section = sectionResourceOverlay
	)
	@Alpha
	default Color resourceOverlayHerbOutlineColor() {
		return new Color(255, 255, 0, 255);
	}
	
	@ConfigItem(
		position = 11,
		keyName = "resourceOverlayHerbFillColor",
		name = "Herb fill color",
		description = "",
		section = sectionResourceOverlay
	)
	@Alpha
	default Color resourceOverlayHerbFillColor() {
		return new Color(255, 255, 0, 50);
	}

	@ConfigItem(
		position = 12,
		keyName = "resourceOverlayPondEnabled",
		name = "Pond",
		description = "",
		section = sectionResourceOverlay
	)
	default boolean resourceOverlayPondEnabled() {
		return true;
	}
	
	@ConfigItem(
		position = 13,
		keyName = "resourceOverlayPondOutlineColor",
		name = "Pond outline color",
		description = "",
		section = sectionResourceOverlay
	)
	@Alpha
	default Color resourceOverlayPondOutlineColor() {
		return new Color(0, 255, 255, 255);
	}
	
	@ConfigItem(
		position = 14,
		keyName = "resourceOverlayPondFillColor",
		name = "Pond fill color",
		description = "",
		section = sectionResourceOverlay
	)
	@Alpha
	default Color resourceOverlayPondFillColor() {
		return new Color(0, 255, 255, 50);
	}

	@Range(
		max = 2,
		min = 0
	)
	@ConfigItem(
		name = "Hull outline width",
		description = "Change the width of the resource hull outline.<br>0px width = disabled",
		position = 17,
		keyName = "resourceOverlayHullOutlineWidth",
		section = sectionResourceOverlay
	)
	@Units(Units.PIXELS)
	default int resourceOverlayHullOutlineWidth()
	{
		return 1;
	}

	@Range(
		max = 2,
		min = 0
	)
	@ConfigItem(
		name = "Tile outline width",
		description = "Change the width of the resource tile outline.<br>0px width = disabled",
		position = 15,
		keyName = "resourceOverlayTileOutlineWidth",
		section = sectionResourceOverlay
	)
	@Units(Units.PIXELS)
	default int resourceOverlayTileOutlineWidth()
	{
		return 1;
	}

	// Utility Overlay Section

	@ConfigSection(
		position = 1,
		name = "Utility Overlay",
		description = "Utility overlay settings",
		closedByDefault = true
	)
	String sectionUtilityOverlay = "sectionUtilityOverlay";

	@ConfigItem(
		position = 0,
		keyName = "utilityOverlayEnabled",
		name = "Enabled",
		description = "Enabling highlighting utilities in the starting room",
		section = sectionUtilityOverlay
	)
	default boolean utilityOverlayEnabled() {
		return true;
	}

	@ConfigItem(
		position = 1,
		keyName = "utilityOverlayOutlineColor",
		name = "Outline color",
		description = "",
		section = sectionUtilityOverlay
	)
	@Alpha
	default Color utilityOverlayOutlineColor() {
		return new Color(255, 0, 255, 255);
	}

	@Range(
		max = 2,
		min = 0
	)
	@ConfigItem(
		name = "Hull outline width",
		description = "Change the width of the utility hull outline.<br>0px width = disabled",
		position = 2,
		keyName = "utilityOverlayHullOutlineWidth",
		section = sectionUtilityOverlay
	)
	@Units(Units.PIXELS)
	default int utilityOverlayHullOutlineWidth()
	{
		return 1;
	}

	// NPC Overlay Section

	@ConfigSection(
		position = 2,
		name = "NPC Overlay",
		description = "NPC overlay settings",
		closedByDefault = true
	)
	String sectionNpcOverlay = "sectionNpcOverlay";

	@ConfigItem(
		position = 0,
		keyName = "npcOverlayDemiBossEnabled",
		name = "Highlight Demi Boss",
		description = "",
		section = sectionNpcOverlay
	)
	default boolean npcOverlayDemiBossEnabled() {
		return true;
	}

	@ConfigItem(
		position = 1,
		keyName = "npcOverlayDarkBeastColor",
		name = "Dark beast color",
		description = "",
		section = sectionNpcOverlay
	)
	@Alpha
	default Color npcOverlayDarkBeastColor() {
		return new Color(0, 255, 0, 255);
	}

	@ConfigItem(
		position = 2,
		keyName = "npcOverlayDragonColor",
		name = "Dragon color",
		description = "",
		section = sectionNpcOverlay
	)
	@Alpha
	default Color npcOverlayDragonColor() {
		return new Color(0, 0, 255, 255);
	}

	@ConfigItem(
		position = 3,
		keyName = "npcOverlayBearColor",
		name = "Bear color",
		description = "",
		section = sectionNpcOverlay
	)
	@Alpha
	default Color npcOverlayBearColor() {
		return new Color(255, 0, 0, 255);
	}

	@Range(
		max = 2,
		min = 0
	)
	@ConfigItem(
		name = "Hull outline width",
		description = "Change the width of the npc hull outline.<br>0px width = disabled",
		position = 4,
		keyName = "npcOverlayHullOutlineWidth",
		section = sectionNpcOverlay
	)
	@Units(Units.PIXELS)
	default int npcOverlayHullOutlineWidth()
	{
		return 1;
	}

	@ConfigItem(
		position = 5,
		keyName = "npcOverlayStrongEnabled",
		name = "Highlight Strong",
		description = "",
		section = sectionNpcOverlay
	)
	default boolean npcOverlayStrongEnabled() {
		return true;
	}

	@ConfigItem(
		position = 5,
		keyName = "npcOverlayWeakEnabled",
		name = "Highlight Weak",
		description = "",
		section = sectionNpcOverlay
	)
	default boolean npcOverlayWeakEnabled() {
		return true;
	}

	// Crafting Overlay Section

	@ConfigSection(
		position = 3,
		name = "Crafting Overlay",
		description = "Configure the crafting overlay",
		closedByDefault = true
	)
	String sectionCraftingOverlay = "sectionCraftingOverlay";

	@ConfigItem(
		position = 0,
		keyName = "craftingOverlayEnabled",
		name = "Enabled",
		description = "Enable crafting overlay",
		section = sectionCraftingOverlay
	)
	default boolean craftingOverlayEnabled() {
		return true;
	}

	@ConfigItem(
		position = 1,
		keyName = "craftingHighlightColor",
		name = "Craft highlight",
		description = "Choose the color used to highlight craftable options in the crafting interface",
		section = sectionCraftingOverlay
	)
	@Alpha
	default Color craftingHighlightColor() {
		return new Color(0, 255, 0, 0);
	}

	@ConfigItem(
		position = 2,
		keyName = "craftingHighlightOutlineColor",
		name = "Craft outline",
		description = "Choose the color used to outline craftable options in the crafting interface",
		section = sectionCraftingOverlay
	)
	@Alpha
	default Color craftingHighlightOutlineColor() {
		return new Color(0, 255, 0, 255);
	}
	
	@ConfigItem(
		position = 3,
		keyName = "craftingHighlightStroke",
		name = "Craft stroke",
		description = "Choose the stoke width used to highlight the crafting interface",
		section = sectionCraftingOverlay
	)
	default int craftingHighlightStroke() {
		return 2;
	}
	
	@ConfigItem(
		position = 4,
		keyName = "craftingHighlightMissingMaterialsColor",
		name = "Missing materials highlight",
		description = "Choose the color used to fill options that need materials in the crafting interface",
		section = sectionCraftingOverlay
	)
	@Alpha
	default Color craftingHighlightMissingMaterialsColor() {
		return new Color(64, 64, 64, 80);
	}
	@ConfigItem(
		position = 5,
		keyName = "craftingHighlightMissingMaterialsOutlineColor",
		name = "Missing materials outline",
		description = "Choose the color used to outline options that need materials in the crafting interface",
		section = sectionCraftingOverlay
	)
	@Alpha
	default Color craftingHighlightMissingMaterialsOutlineColor() {
		return new Color(255, 0, 0, 200);
	}
	
	@ConfigItem(
		position = 6,
		keyName = "craftingHighlightMissingMaterialsStroke",
		name = "Missing materials stroke",
		description = "Choose the stoke width used to highlight the crafting interface",
		section = sectionCraftingOverlay
	)
	default int craftingHighlightMissingMaterialsStroke() {
		return 1;
	}
	
	@ConfigItem(
		position = 7,
		keyName = "craftingHighlightCompleteColor",
		name = "Done highlight",
		description = "Choose the color used to fill completed options in the crafting interface",
		section = sectionCraftingOverlay
	)
	@Alpha
	default Color craftingHighlightCompleteColor() {
		return new Color(64, 64, 64, 210);
	}
	@ConfigItem(
		position = 8,
		keyName = "craftingHighlightCompleteOutlineColor",
		name = "Done outline",
		description = "Choose the color used to outline completed options in the crafting interface",
		section = sectionCraftingOverlay
	)
	@Alpha
	default Color craftingHighlightCompleteOutlineColor() {
		return new Color(64, 64, 64, 0);
	}
	
	@ConfigItem(
		position = 9,
		keyName = "craftingHighlightCompleteStroke",
		name = "Done stroke",
		description = "Choose the stoke width used to highlight the crafting interface",
		section = sectionCraftingOverlay
	)
	default int craftingHighlightCompleteStroke() {
		return 0;
	}
}