package gg.hazard.spotify.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import gg.hazard.spotify.configs.ScoreboardConfig;
import gg.hazard.spotify.configs.TablistConfig;
import gg.hazard.spotify.utils.CC;
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
