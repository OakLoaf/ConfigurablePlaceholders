package org.lushplugins.configurableplaceholders.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.lushplugins.configurableplaceholders.ConfigurablePlaceholders;
import org.lushplugins.configurableplaceholders.placeholder.Placeholder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigPapiExpansion extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "cp";
    }

    @Override
    public @NotNull String getAuthor() {
        return ConfigurablePlaceholders.getInstance().getPluginMeta().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return ConfigurablePlaceholders.getInstance().getPluginMeta().getVersion();
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        Placeholder placeholder = ConfigurablePlaceholders.getInstance().getConfigManager().getPlaceholder(params);
        return placeholder != null ? placeholder.parse(player) : null;
    }
}
