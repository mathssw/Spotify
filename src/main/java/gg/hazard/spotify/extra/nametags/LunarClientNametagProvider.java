package gg.hazard.spotify.extra.nametags;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.nametag.Nametag;
import com.lunarclient.apollo.module.nametag.NametagModule;
import com.lunarclient.apollo.recipients.Recipients;
import gg.hazard.spotify.hHub;
import gg.hazard.spotify.utils.CC;
import gg.hazard.spotify.utils.QueueUtils;
import io.github.retrooper.packetevents.adventure.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LunarClientNametagProvider implements Listener {
    public LunarClientNametagProvider() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(hHub.getInstance(), () -> {
            Iterator var1 = Bukkit.getOnlinePlayers().iterator();

            while(var1.hasNext()) {
                Player onlinePlayer = (Player)var1.next();
                Bukkit.getOnlinePlayers().forEach((player) -> {
                    Apollo.getModuleManager().getModule(NametagModule.class).overrideNametag(Recipients.ofEveryone(), onlinePlayer.getUniqueId(), Nametag.builder().lines(this.fetchNametag(onlinePlayer, player)).build());
                    });
            }

        }, 0L, 40L);
    }

    public List<Component> fetchNametag(Player target, Player viewer) {
        List<Component> nameTag = new ArrayList();
        if (QueueUtils.isInQueue(target)) {
            nameTag.add(LegacyComponentSerializer.legacyAmpersand().deserialize(CC.replace(target, "&7[&e%queue-name% #%queue-pos% of %queue-length%&7]")));
        }
        nameTag.add(LegacyComponentSerializer.legacyAmpersand().deserialize(CC.replace(target, "&8[%rank%&8]")));
        nameTag.add(LegacyComponentSerializer.legacyAmpersand().deserialize(CC.replace(target, "%color_name%")));
        Collections.reverse(nameTag);
        return nameTag;
    }
}