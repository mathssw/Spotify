package gg.hazard.spotify.extra.skin.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import gg.hazard.spotify.extra.skin.CachedSkin;
import gg.hazard.spotify.extra.skin.SkinAPI;

/**
 * <p>
 * This Project is property of Refine Development.<br>
 * Copyright © 2024, All Rights Reserved.<br>
 * Redistribution of this Project is not allowed.<br>
 * </p>
 *
 * @author Drizzy
 * @version SkinAPI
 * @since 9/28/2024
 */

@RequiredArgsConstructor
public class SkinListener implements Listener {

    private final SkinAPI api;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        CachedSkin cachedSkin = api.getSkin(player.getName());
        if (cachedSkin == null) {
            api.registerPlayer(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        api.clearPlayer(player);
    }
}

