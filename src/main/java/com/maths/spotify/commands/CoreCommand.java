package com.maths.spotify.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import com.maths.spotify.configs.ScoreboardConfig;
import com.maths.spotify.configs.TablistConfig;
import com.maths.spotify.utils.CC;
import org.bukkit.entity.Player;

@CommandAlias("hubcore")
public class CoreCommand extends BaseCommand {
    @Subcommand("reload")
    public static void onReload(Player player) {
        ScoreboardConfig.INSTANCE.reload();
        TablistConfig.INSTANCE.reload();
        player.sendMessage(CC.translate("&aSuccessfully reloaded Scoreboard & Tablist config."));
    }
}
