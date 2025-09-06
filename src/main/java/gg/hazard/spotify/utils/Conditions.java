package gg.hazard.spotify.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Conditions {
    public static boolean canBuild(Player player) {
        return player.hasPermission("hub.build") && player.getGameMode() == GameMode.CREATIVE;
    }
}
