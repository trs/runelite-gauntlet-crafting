package com.trs.system;

import com.google.inject.Inject;
import javax.inject.Singleton;

import com.trs.component.CollectPatternComponent;
import com.trs.GauntletPluginDatabase;
import com.trs.type.IEntity;
import com.trs.type.IItemComponent;

import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.ChatMessageType;
import net.runelite.client.util.Text;

@Singleton
public class ResourceCollectorSystem extends AbstractSystem {
  private final EventBus eventBus;

  @Inject
  public ResourceCollectorSystem(EventBus eventBus) {
    super(CollectPatternComponent.class);
    this.eventBus = eventBus;
  }

  @Override
  public void startUp() {
    eventBus.register(this);
  }

  @Override
  public void shutDown() {
    eventBus.unregister(this);
  }

  @Subscribe
	void onChatMessage(final ChatMessage event)
	{
		var type = event.getType();
		if (type == ChatMessageType.SPAM || type == ChatMessageType.GAMEMESSAGE)
		{
			var message = Text.removeTags(event.getMessage());

      for (IEntity<IItemComponent> entity : getEntities()) {
        var components = entity.getComponents(CollectPatternComponent.class);
        if (components.isEmpty()) continue;
  
        for (CollectPatternComponent component : components) {
          var matcher = component.collectPattern.matcher(message);
          if (matcher.matches()) {
            var quantity = 1;
            try {
              quantity = Integer.parseInt(matcher.group("quantity"));
            } catch (IllegalArgumentException e) {}
            
            GauntletPluginDatabase.collectedResources.put(entity, GauntletPluginDatabase.collectedResources.getOrDefault(entity, 0) + quantity);
          }
        }
      }
		}
	}
}
