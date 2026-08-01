package org.lushplugins.configurableplaceholders.config;

import org.lushplugins.configurableplaceholders.ConfigurablePlaceholders;
import org.lushplugins.configurableplaceholders.placeholder.BedrockPlaceholder;
import org.lushplugins.configurableplaceholders.placeholder.JavaPlaceholder;
import org.lushplugins.configurableplaceholders.placeholder.Placeholder;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.concurrent.ConcurrentHashMap;

public class ConfigManager {
    private final ConcurrentHashMap<String, Placeholder> placeholders = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> messages = new ConcurrentHashMap<>();
    private boolean debug = false;

    public ConfigManager() {
        ConfigurablePlaceholders.getInstance().saveDefaultConfig();
    }

    public void reloadConfig() {
        ConfigurablePlaceholders.getInstance().reloadConfig();
        FileConfiguration config = ConfigurablePlaceholders.getInstance().getConfig();

        ConfigurationSection placeholdersSection = config.getConfigurationSection("placeholders");
        if (placeholdersSection != null) {
            placeholdersSection.getValues(false).forEach((key, value) -> {
                if (value instanceof ConfigurationSection placeholderSection) {
                    String defaultContent = placeholderSection.getString("content");
                    Placeholder placeholder = new Placeholder(defaultContent);

                    if (placeholderSection.contains("java")) {
                        placeholder.setJavaPlaceholder(new JavaPlaceholder(placeholderSection.getString("java.default"), placeholderSection.getString("java.rp"), placeholderSection.getString("java.no-rp")));
                    }
                    if (placeholderSection.contains("bedrock")) {
                        placeholder.setBedrockPlaceholder(new BedrockPlaceholder(placeholderSection.getString("bedrock")));
                    }

                    placeholders.put(key, placeholder);
                } else {
                    placeholders.put(key, new Placeholder(placeholdersSection.getString(key)));
                }
            });
        }

        // Clears messages map
        messages.clear();
        // Checks if messages section exists
        ConfigurationSection messagesSection = config.getConfigurationSection("messages");
        if (messagesSection != null) {
            // Repopulates messages map
            messagesSection.getValues(false).forEach((key, value) -> messages.put(key, (String) value));
        }

        debug = config.getBoolean("debug");
    }

    public Placeholder getPlaceholder(String placeholder) {
        return placeholders.get(placeholder);
    }

    public String getMessage(String messageName) {
        String output = messages.getOrDefault(messageName, "");

        if (messages.containsKey("prefix")) {
            return output.replaceAll("%prefix%", messages.get("prefix"));
        } else {
            return output;
        }
    }

    public boolean isDebugEnabled() {
        return debug;
    }
}
