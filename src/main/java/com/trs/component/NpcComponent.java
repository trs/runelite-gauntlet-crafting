package com.trs.component;

import com.trs.type.IComponent;

import net.runelite.api.NPC;

public class NpcComponent implements IComponent {
  public NPC npc;

  public NpcComponent(NPC npc) {
    this.npc = npc;
  }

  public static int getIdentifier(NPC npc) {
    return npc.hashCode();
  }
}
