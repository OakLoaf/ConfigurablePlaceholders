package me.dave.configurableplaceholders.util;

import me.dave.configurableplaceholders.ConfigurablePlaceholders;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import java.util.HashSet;
import java.util.UUID;

public class ResourcePackChecker implements Listener {
    private final HashSet<UUID> playersWithRP = new HashSet<>();

    @EventHandler
    public void onPlayerResourcePackStatusReturn(PlayerResourcePackStatusEvent event) {
        ConfigurablePlaceholders.getInstance().getLogger().info(event.getPlayer().getName() + " status received for resourcepack " + event.getStatus());
        if (event.getStatus() == PlayerResourcePackStatusEvent.Status.ACCEPTED) {
            playersWithRP.add(event.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        playersWithRP.remove(event.getPlayer().getUniqueId());
    }

    public boolean hasResourcePack(UUID uuid) {
        return playersWithRP.contains(uuid);
    }
}
