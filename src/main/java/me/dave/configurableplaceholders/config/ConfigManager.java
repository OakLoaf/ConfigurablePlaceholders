package me.dave.configurableplaceholders.config;

import me.dave.configurableplaceholders.ConfigurablePlaceholders;
import me.dave.configurableplaceholders.placeholder.BedrockPlaceholder;
import me.dave.configurableplaceholders.placeholder.JavaPlaceholder;
import me.dave.configurableplaceholders.placeholder.Placeholder;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ConfigManager {
    private final HashMap<String, Placeholder> placeholders = new HashMap<>();

    public ConfigManager() {
        ConfigurablePlaceholders.getInstance().saveDefaultConfig();
    }

    public void reloadConfig() {
        ConfigurablePlaceholders.getInstance().reloadConfig();
        FileConfiguration config = ConfigurablePlaceholders.getInstance().getConfig();

        ConfigurationSection placeholdersSection = config.getConfigurationSection("placeholders");
        placeholdersSection.getValues(false).forEach((key, value) -> {
            if (value instanceof ConfigurationSection placeholderSection) {
                String defaultContent = placeholderSection.getString("content");
                Placeholder placeholder = new Placeholder(defaultContent);

                JavaPlaceholder javaPlaceholder;
                String javaContent = placeholderSection.getString("java");
                if (javaContent != null) {
                    javaPlaceholder = new JavaPlaceholder(javaContent);
                } else {
                    ConfigurationSection javaSection = placeholderSection.getConfigurationSection("java");
                    if (javaSection != null) {
                        String defaultJavaContent = javaSection.getString("default");
                        String rp = javaSection.getString("rp");
                        String noRp = javaSection.getString("no-rp");
                        javaPlaceholder = new JavaPlaceholder(defaultJavaContent, rp, noRp);
                    } else {
                        javaPlaceholder = null;
                    }
                }
                placeholder.setJavaPlaceholder(javaPlaceholder);
                placeholder.setBedrockPlaceholder(new BedrockPlaceholder(config.getString("bedrock")));

                placeholders.put(key, placeholder);
            } else {
                placeholders.put(key, new Placeholder(placeholdersSection.getString(key)));
            }
        });
    }

    public Placeholder getPlaceholder(String placeholder) {
        return placeholders.get(placeholder);
    }
}
