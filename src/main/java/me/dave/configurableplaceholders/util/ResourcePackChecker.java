package me.dave.configurableplaceholders.util;

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
    }

    public void removePlayer(UUID uuid) {
        playersWithRP.remove(uuid);
    }

    public boolean hasResourcePack(UUID uuid) {
        return playersWithRP.contains(uuid);
    }
}
