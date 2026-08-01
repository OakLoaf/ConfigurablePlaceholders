package org.lushplugins.configurableplaceholders;

import org.lushplugins.configurableplaceholders.cache.PlayerResourcePackCache;
import org.lushplugins.configurableplaceholders.command.ConfigurablePlaceholdersCommand;
import org.lushplugins.configurableplaceholders.config.ConfigManager;
import org.lushplugins.configurableplaceholders.hook.ConfigPapiExpansion;
import org.lushplugins.configurableplaceholders.hook.FormatterPapiExpansion;
import org.lushplugins.configurableplaceholders.listener.MessageListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.lushplugins.configurableplaceholders.listener.PlayerListener;
import revxrsal.commands.bukkit.BukkitLamp;

public final class ConfigurablePlaceholders extends JavaPlugin {
    private static ConfigurablePlaceholders plugin;

    private final PlayerResourcePackCache playerPackCache = new PlayerResourcePackCache();
    private ConfigManager configManager;
    private ConfigPapiExpansion configPapiExpansion;
    private FormatterPapiExpansion formatterPapiExpansion;

    @Override
    public void onEnable() {
        plugin = this;

        configManager = new ConfigManager();
        configManager.reloadConfig();

        configPapiExpansion = new ConfigPapiExpansion();
        configPapiExpansion.register();

        formatterPapiExpansion = new FormatterPapiExpansion();
        formatterPapiExpansion.register();

        BukkitLamp.builder(this)
            .build()
            .register(new ConfigurablePlaceholdersCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getServer().getMessenger().registerIncomingPluginChannel(plugin, "rp:plugin", new MessageListener());
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterIncomingPluginChannel(plugin);

        if (configPapiExpansion != null) {
            configPapiExpansion.unregister();
            configPapiExpansion = null;
        }

        if (formatterPapiExpansion != null) {
            formatterPapiExpansion.unregister();
            formatterPapiExpansion = null;
        }
    }

    public PlayerResourcePackCache getPlayerPackCache() {
        return playerPackCache;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static ConfigurablePlaceholders getInstance() {
        return plugin;
    }
}
