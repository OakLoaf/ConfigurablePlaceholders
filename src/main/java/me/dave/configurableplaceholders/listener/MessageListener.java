package me.dave.configurableplaceholders.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.dave.configurableplaceholders.ConfigurablePlaceholders;
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

            if (packName.equals("mainpack")) {
                ConfigurablePlaceholders.getResourcePackChecker().addPlayer(player.getUniqueId());
            } else {
                ConfigurablePlaceholders.getResourcePackChecker().removePlayer(player.getUniqueId());
            }
        }
    }
}
