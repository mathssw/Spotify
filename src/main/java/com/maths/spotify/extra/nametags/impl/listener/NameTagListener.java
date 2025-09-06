package com.maths.spotify.extra.nametags.impl.listener;

import com.maths.spotify.hHub;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class NameTagListener implements Listener {

    private final hHub plugin = hHub.getInstance();

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().setMetadata("uhub-LoggedIn", new FixedMetadataValue(plugin, true));

        plugin.getNameTagHandler().initiatePlayer(event.getPlayer());
        plugin.getNameTagHandler().reloadPlayer(event.getPlayer());
        plugin.getNameTagHandler().reloadOthersFor(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.getPlayer().removeMetadata("uhub-LoggedIn", plugin);
        plugin.getNameTagHandler().getTeamMap().remove(event.getPlayer().getName());
    }

}