package org.lushplugins.configurableplaceholders.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.lushplugins.configurableplaceholders.ConfigurablePlaceholders;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class MessageListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte[] message) {
        if (!channel.equals("rp:plugin")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("packChange")) {
            String playerName = in.readUTF();
            long uuidMostSignificantBits = in.readLong();
            long uuidLeastSignificantBits = in.readLong();
            String packName = in.readUTF();

            if (ConfigurablePlaceholders.getInstance().getConfigManager().isDebugEnabled()) {
                ConfigurablePlaceholders.getInstance().getLogger().info("DEBUG >> Received 'packChange' for '" + player.getUniqueId() + "' (" + playerName + ") using pack: '" + packName +"'");
            }

            if (packName.equals("mainpack")) {
                ConfigurablePlaceholders.getInstance().getPlayerPackCache().cache(player.getUniqueId());
            } else {
                ConfigurablePlaceholders.getInstance().getPlayerPackCache().invalidate(player.getUniqueId());
            }
        } else if (subchannel.equals("clearPack")) {
            String playerName = in.readUTF();

            if (ConfigurablePlaceholders.getInstance().getConfigManager().isDebugEnabled()) {
                ConfigurablePlaceholders.getInstance().getLogger().info("DEBUG >> Received 'clearPack' for '" + player.getUniqueId() + "' (" + playerName + ")");
            }

            ConfigurablePlaceholders.getInstance().getPlayerPackCache().invalidate(player.getUniqueId());
        }
    }
}
