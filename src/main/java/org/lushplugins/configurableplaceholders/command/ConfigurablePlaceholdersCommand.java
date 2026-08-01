package org.lushplugins.configurableplaceholders.command;

import org.bukkit.command.CommandSender;
import org.lushplugins.chatcolorhandler.paper.PaperColor;
import org.lushplugins.configurableplaceholders.ConfigurablePlaceholders;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@Command({"configurableplaceholders", "cplaceholders"})
@SuppressWarnings("unused")
public class ConfigurablePlaceholdersCommand {

    @Subcommand("reload")
    @CommandPermission("configurableplaceholders.reload")
    public void reload(CommandSender sender) {
        ConfigurablePlaceholders.getInstance().getConfigManager().reloadConfig();

        PaperColor.handler().sendMessage(sender, ConfigurablePlaceholders.getInstance().getConfigManager().getMessage("reload"));
    }
}
