package me.dave.configurableplaceholders.util;

import me.dave.configurableplaceholders.ConfigurablePlaceholders;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashSet;
import java.util.UUID;

public class ResourcePackChecker implements Listener {
    private final HashSet<UUID> playersWithRP = new HashSet<>();

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        playersWithRP.remove(event.getPlayer().getUniqueId());
    }

    public void addPlayer(UUID uuid) {
        playersWithRP.add(uuid);

        if (ConfigurablePlaceholders.getConfigManager().isDebugEnabled()) {
            ConfigurablePlaceholders.getInstance().getLogger().info("DEBUG >> Added '" + uuid + "' to resource pack users list");
        }
    }

    public void removePlayer(UUID uuid) {
        playersWithRP.remove(uuid);

        if (ConfigurablePlaceholders.getConfigManager().isDebugEnabled()) {
            ConfigurablePlaceholders.getInstance().getLogger().info("DEBUG >> Removed '" + uuid + "' from resource pack users list");
        }
    }

    public boolean hasResourcePack(UUID uuid) {
        return playersWithRP.contains(uuid);
    }
}
