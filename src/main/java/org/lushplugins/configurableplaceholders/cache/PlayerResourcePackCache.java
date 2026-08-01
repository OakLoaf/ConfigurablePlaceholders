package org.lushplugins.configurableplaceholders.cache;

import org.lushplugins.configurableplaceholders.ConfigurablePlaceholders;

import java.util.HashSet;
import java.util.UUID;

public class PlayerResourcePackCache {
    private final HashSet<UUID> playersWithPack = new HashSet<>();

    public boolean hasPack(UUID uuid) {
        return playersWithPack.contains(uuid);
    }

    public void cache(UUID uuid) {
        playersWithPack.add(uuid);

        if (ConfigurablePlaceholders.getInstance().getConfigManager().isDebugEnabled()) {
            ConfigurablePlaceholders.getInstance().getLogger().info("DEBUG >> Added '" + uuid + "' to resource pack users list");
        }
    }

    public void invalidate(UUID uuid) {
        playersWithPack.remove(uuid);

        if (ConfigurablePlaceholders.getInstance().getConfigManager().isDebugEnabled()) {
            ConfigurablePlaceholders.getInstance().getLogger().info("DEBUG >> Removed '" + uuid + "' from resource pack users list");
        }
    }
}
