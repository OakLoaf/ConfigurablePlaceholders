package org.lushplugins.configurableplaceholders.command;

import io.papermc.paper.plugin.configuration.PluginMeta;
import org.bukkit.command.CommandSender;
import org.lushplugins.chatcolorhandler.paper.PaperColor;
import org.lushplugins.configurableplaceholders.ConfigurablePlaceholders;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@SuppressWarnings("unused")
public class ConfigurablePlaceholdersCommand {

    @Command({"configurableplaceholders", "cplaceholders"})
    public void version(CommandSender sender) {
        PluginMeta pluginMeta = ConfigurablePlaceholders.getInstance().getPluginMeta();
        PaperColor.handler().sendMessage(sender, "&#a8e1ffYou are currently running &#58b1e0%s &#a8e1ffversion &#58b1e0%s"
            .formatted(pluginMeta.getName(), pluginMeta.getVersion()));
    }

    @Subcommand("reload")
    @CommandPermission("configurableplaceholders.reload")
    public void reload(CommandSender sender) {
        ConfigurablePlaceholders.getInstance().getConfigManager().reloadConfig();

        PaperColor.handler().sendMessage(sender, ConfigurablePlaceholders.getInstance().getConfigManager().getMessage("reload"));
    }
}
