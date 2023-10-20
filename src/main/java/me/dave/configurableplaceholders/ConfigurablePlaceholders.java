package me.dave.configurableplaceholders;

import me.dave.configurableplaceholders.command.MainCmd;
import me.dave.configurableplaceholders.config.ConfigManager;
import me.dave.configurableplaceholders.hook.PlaceholderAPIExpansion;
import me.dave.configurableplaceholders.listener.MessageListener;
import me.dave.configurableplaceholders.util.ResourcePackChecker;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigurablePlaceholders extends JavaPlugin {
    private static PlaceholderAPIExpansion placeholderAPIExpansion;

    private static ConfigurablePlaceholders plugin;

    private static ConfigManager configManager;
    private static ResourcePackChecker resourcePackChecker;

    @Override
    public void onEnable() {
        plugin = this;

        configManager = new ConfigManager();
        configManager.reloadConfig();

        resourcePackChecker = new ResourcePackChecker();

        placeholderAPIExpansion = new PlaceholderAPIExpansion();
        placeholderAPIExpansion.register();

        getCommand("configurableplaceholders").setExecutor(new MainCmd());

        getServer().getPluginManager().registerEvents(resourcePackChecker, this);

        getServer().getMessenger().registerIncomingPluginChannel(plugin, "rp:plugin", new MessageListener());
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterIncomingPluginChannel(plugin);

        if (placeholderAPIExpansion != null) {
            placeholderAPIExpansion.unregister();
            placeholderAPIExpansion = null;
        }

        resourcePackChecker = null;
        configManager = null;

        plugin = null;
    }

    public static ConfigurablePlaceholders getInstance() {
        return plugin;
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public static ResourcePackChecker getResourcePackChecker() {
        return resourcePackChecker;
    }
}
