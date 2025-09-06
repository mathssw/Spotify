package com.maths.spotify.configs;


import org.bukkit.plugin.java.JavaPlugin;
import com.maths.spotify.utils.config.creator.ConfigCreator;
import com.maths.spotify.utils.config.creator.annotation.Header;
import com.maths.spotify.utils.config.creator.annotation.Path;

import java.util.Arrays;
import java.util.List;

@Header(lines = {
        "",
        "hHub",
        "Tablist configuration file.",
        "",
        "Available heads for the tab:",
        " - DARK_GRAY",
        " - TWITTER_HEAD",
        " - STORE_HEAD",
        " - VIEWER_HEAD (player's head)",
        " - CONSOLE_HEAD",
        " - PING_HEAD",
        " - DISCORD_HEAD",
        " - WEBSITE_HEAD",
        " - INFO_HEAD",
        ""
})
public class TablistConfig extends ConfigCreator {

    public static TablistConfig INSTANCE;

    @Path(id = "config.header")
    public static List<String> TABLIST_HEADER = Arrays.asList(
            "&f",
            "&a&lHyperMC Network",
            "&a&n60% OFF&f available at &d&ostore.hypermc.net",
            ""
    );

    @Path(id = "config.footer")
    public static List<String> TABLIST_FOOTER = Arrays.asList(
            "&f",
            "&aDiscord&7: &fdiscord.gg/Hypermc",
            ""
    );

    @Path(id = "config.queue")
    public static String NOT_IN_QUEUE = "&cNot in queue.";

    @Path(id = "entries.left")
    public static List<String> TABLIST_LEFT = Arrays.asList(
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "TWITTER_HEAD;&aTwitter",
            "DARK_GRAY;&7&ox.com/hazard",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;&aHCFactions (I)",
            "DARK_GRAY;Status&7: &cOffline",
            "DARK_GRAY;Online&7: &d%bungee_HCFactions%/350",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "STORE_HEAD;&aStore",
            "DARK_GRAY;&7&ostore.hypermc.net",
            "DARK_GRAY;"
    );

    @Path(id = "entries.middle")
    public static List<String> TABLIST_MIDDLE = Arrays.asList(
            "DARK_GRAY;",
            "DARK_GRAY;&a&lHazard Network",
            "DARK_GRAY;Online&7: &d%bungee_total%/1,000",
            "DARK_GRAY;",
            "VIEWER_HEAD;&aProfile",
            "DARK_GRAY;Name&7: %name%",
            "DARK_GRAY;Ban&7: %rank%",
            "DARK_GRAY;Coins&7: &d0 ⛁",
            "DARK_GRAY;",
            "CONSOLE_HEAD;&aGamemodes",
            "DARK_GRAY;",
            "DARK_GRAY;&aKitMap (II)",
            "DARK_GRAY;Status&7: &aOnline",
            "DARK_GRAY;Online&7: &d%bungee_Kitmap%/350",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "PING_HEAD;&aConnect using",
            "DARK_GRAY;&7&ohypermc.net",
            "DARK_GRAY;"
    );

    @Path(id = "entries.right")
    public static List<String> TABLIST_RIGHT = Arrays.asList(
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DISCORD_HEAD;&aDiscord",
            "DARK_GRAY;&7&odiscord.gg/hazardgg",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;&aPractice (I)",
            "DARK_GRAY;Status&7: &aOnline",
            "DARK_GRAY;Online&7: &d%bungee_Practice%/350",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "WEBSITE_HEAD;&aWebsite",
            "DARK_GRAY;&7&owww.hypermc.net",
            "DARK_GRAY;"
    );

    @Path(id = "entries.far-right")
    public static List<String> TABLIST_FAR_RIGHT = Arrays.asList(
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "INFO_HEAD;&a&lNEWS!",
            "DARK_GRAY;&7Welcome to the",
            "DARK_GRAY;&cCh&fri&cst&fma&cs &e&oUpdate",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;",
            "DARK_GRAY;"
    );

    public TablistConfig(JavaPlugin plugin) {
        super(plugin, "providers/tablist.yml");

        INSTANCE = this;
    }
}
