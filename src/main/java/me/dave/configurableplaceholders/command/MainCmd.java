package me.dave.configurableplaceholders.command;

import me.dave.chatcolorhandler.ChatColorHandler;
import me.dave.configurableplaceholders.ConfigurablePlaceholders;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainCmd implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("configurableplaceholders.reload")) {
                    ChatColorHandler.sendMessage(sender, ConfigurablePlaceholders.getConfigManager().getMessage("no-permissions"));
                    return true;
                }
                ConfigurablePlaceholders.getConfigManager().reloadConfig();

                ChatColorHandler.sendMessage(sender, ConfigurablePlaceholders.getConfigManager().getMessage("reload"));
                return true;
            }
        }

        PluginDescriptionFile pluginDescription = ConfigurablePlaceholders.getInstance().getDescription();
        ChatColorHandler.sendMessage(sender, "&#a8e1ffYou are currently running &#58b1e0" + pluginDescription.getName() + " &#a8e1ffversion &#58b1e0" + pluginDescription.getVersion());
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tabComplete = new ArrayList<>();
        List<String> wordCompletion = new ArrayList<>();
        boolean wordCompletionSuccess = false;

        if (args.length == 1) {
            if (sender.hasPermission("configurableplaceholders.reload")) {
                tabComplete.add("reload");
            }
        }

        for (String currTab : tabComplete) {
            int currArg = args.length - 1;
            if (currTab.startsWith(args[currArg])) {
                wordCompletion.add(currTab);
                wordCompletionSuccess = true;
            }
        }

        return wordCompletionSuccess ? wordCompletion : tabComplete;
    }
}
