package com.maths.spotify.configs;

import com.maths.spotify.utils.config.creator.ConfigCreator;
import com.maths.spotify.utils.config.creator.annotation.Header;
import com.maths.spotify.utils.config.creator.annotation.Path;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

@Header(lines = {
        "",
        "hHub",
        "Tablist configuration file.",
        "",
        "go fuck urself matt"
})
public class ScoreboardConfig extends ConfigCreator {
    public static ScoreboardConfig INSTANCE;

    @Path(id = "config.title")
    public static List<String> SCOREBOARD_TITLE = Arrays.asList(
            "&c&lHyperMC"
    );
    @Path(id = "config.footer")
    public static List<String> SCOREBOARD_FOOTER = Arrays.asList(
            "&7lhypermc.net"
    );
    @Path(id = "boards.lobby")
    public static List<String> SCOREBOARD_LOBBY = Arrays.asList(
            "%line%",
            "&a&lServer",
            "&d» &7Network: &f%global_count%",
            "&7",
            "&a&lYour Stats",
            "&d» &7Name: &f%name%",
            "&d» &7Rank: &f%rank%",
            " ",
            "%queue%",
            "%footer%",
            "%line%"
    );
    @Path(id = "boards.queue")
    public static List<String> SCOREBOARD_QUEUE = Arrays.asList(
            "&a&lQueue &7[%queue_name%]",
            "&d» &7Position: #%queue_pos% of %queue_total%",
            " "
    );

    public ScoreboardConfig(JavaPlugin plugin) {
        super(plugin, "providers/scoreboard.yml");
        INSTANCE = this;
    }
}
