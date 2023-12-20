package me.dave.configurableplaceholders.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.dave.configurableplaceholders.ConfigurablePlaceholders;
import me.dave.configurableplaceholders.placeholder.Placeholder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigPapiExpansion extends PlaceholderExpansion {

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        Placeholder placeholder = ConfigurablePlaceholders.getConfigManager().getPlaceholder(params);
        return placeholder != null ? placeholder.parse(player) : null;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "cp";
    }

    @Override
    public @NotNull String getAuthor() {
        return ConfigurablePlaceholders.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return ConfigurablePlaceholders.getInstance().getDescription().getVersion();
    }
}
