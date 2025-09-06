package com.maths.spotify.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import com.maths.spotify.configs.ScoreboardConfig;
import com.maths.spotify.configs.TablistConfig;
import com.maths.spotify.utils.CC;
import org.bukkit.entity.Player;

@CommandAlias("hubcore|hub|hcore")
public class CoreCommand extends BaseCommand {
    
    @Subcommand("reload")
    public static void onReload(Player player) {
        if (!player.hasPermission("hub.admin")) {
            player.sendMessage(CC.translate("&cYou don't have permission to use this command!"));
            return;
        }
        
        ScoreboardConfig.INSTANCE.reload();
        TablistConfig.INSTANCE.reload();
        player.sendMessage(CC.translate("&a&lHub Core &8» &aSuccessfully reloaded all configurations!"));
    }
    
    @Subcommand("info")
    public static void onInfo(Player player) {
        player.sendMessage(CC.translate("&b&l&m                                          "));
        player.sendMessage(CC.translate("&b&lHub Core Information"));
        player.sendMessage(CC.translate("&b&l&m                                          "));
        player.sendMessage(CC.translate("&7Version: &a1.0.0"));
        player.sendMessage(CC.translate("&7Author: &amaths"));
        player.sendMessage(CC.translate("&7Features: &aCosmetics, Pets, Particles, Armor"));
        player.sendMessage(CC.translate("&7Commands: &a/cosmetics, /pet, /particles, /armor"));
        player.sendMessage(CC.translate("&b&l&m                                          "));
    }
}
