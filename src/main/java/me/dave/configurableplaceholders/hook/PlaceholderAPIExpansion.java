package me.dave.configurableplaceholders.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.dave.configurableplaceholders.ConfigurablePlaceholders;
import me.dave.configurableplaceholders.placeholder.BedrockPlaceholder;
import me.dave.configurableplaceholders.placeholder.JavaPlaceholder;
import me.dave.configurableplaceholders.placeholder.Placeholder;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderAPIExpansion extends PlaceholderExpansion {

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        Placeholder placeholder = ConfigurablePlaceholders.getConfigManager().getPlaceholder(params);
        if (placeholder != null) {
            if (player == null) {
                return placeholder.getContent();
            }

            JavaPlaceholder javaPlaceholder = placeholder.getJavaPlaceholder();
            BedrockPlaceholder bedrockPlaceholder = placeholder.getBedrockPlaceholder();

            if (javaPlaceholder != null || bedrockPlaceholder != null) {
                // Checks if the player is a bedrock player
                if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
                    if (bedrockPlaceholder != null) {
                        return bedrockPlaceholder.content();
                    }
                } else {
                    if (javaPlaceholder != null) {
                        String content = javaPlaceholder.getContent();
                        String rp = javaPlaceholder.getRp();
                        String noRp = javaPlaceholder.getNoRp();
                        boolean hasPack = ConfigurablePlaceholders.getResourcePackChecker().hasResourcePack(player.getUniqueId());

                        if (rp != null && hasPack) {
                            return rp;
                        } else if (noRp != null && !hasPack) {
                            return noRp;
                        } else {
                            return content != null ? content : placeholder.getContent();
                        }
                    }
                }
            }

            return placeholder.getContent();
        }

        return null;
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
