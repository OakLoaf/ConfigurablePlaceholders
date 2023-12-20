package me.dave.configurableplaceholders.placeholder;

import me.dave.configurableplaceholders.ConfigurablePlaceholders;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.Nullable;

public class Placeholder {
    private String content;
    private JavaPlaceholder javaPlaceholder = null;
    private BedrockPlaceholder bedrockPlaceholder = null;

    public Placeholder() {}

    public Placeholder(String content) {
        this.content = content;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Nullable
    public JavaPlaceholder getJavaPlaceholder() {
        return javaPlaceholder;
    }

    public void setJavaPlaceholder(@Nullable JavaPlaceholder javaPlaceholder) {
        this.javaPlaceholder = javaPlaceholder;
    }

    @Nullable
    public BedrockPlaceholder getBedrockPlaceholder() {
        return bedrockPlaceholder;
    }

    public void setBedrockPlaceholder(@Nullable BedrockPlaceholder bedrockPlaceholder) {
        this.bedrockPlaceholder = bedrockPlaceholder;
    }

    public String parse(@Nullable Player player) {
        if (player == null) {
            return this.content;
        }

        if (javaPlaceholder != null || bedrockPlaceholder != null) {
            // Checks if the player is a bedrock player
            if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
                if (bedrockPlaceholder != null) {
                    return bedrockPlaceholder.content();
                }
            } else {
                if (javaPlaceholder != null) {
                    String content = javaPlaceholder.content();
                    String rp = javaPlaceholder.rp();
                    String noRp = javaPlaceholder.noRp();
                    boolean hasPack = ConfigurablePlaceholders.getResourcePackChecker().hasResourcePack(player.getUniqueId());

                    if (rp != null && hasPack) {
                        return rp;
                    } else if (noRp != null && !hasPack) {
                        return noRp;
                    } else {
                        return content != null ? content : this.content;
                    }
                }
            }
        }

        return this.content;
    }
}
