package gg.hazard.spotify.extra.skin.player;

import org.bukkit.entity.Player;
import gg.hazard.spotify.extra.skin.CachedSkin;

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
public interface IPlayerAdapter {

    /**
     * Get the cached skin by the player.
     *
     * @param player {@link Player} Player to get the skin from.
     * @return {@link CachedSkin} CachedSkin
     */
    CachedSkin getByPlayer(Player player);

    /**
     * Register the skin for the player.
     *
     * @param player {@link Player} Player to register the skin for.
     */
    void registerSkin(Player player);

}
