package org.lushplugins.configurableplaceholders.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.lushplugins.configurableplaceholders.ConfigurablePlaceholders;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        ConfigurablePlaceholders.getInstance().getPlayerPackCache().invalidate(event.getPlayer().getUniqueId());
    }
}
