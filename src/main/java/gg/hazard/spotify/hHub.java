package gg.hazard.spotify;

import co.aikar.commands.BukkitCommandManager;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.PacketEventsAPI;
import com.google.gson.GsonBuilder;
import gg.hazard.spotify.board.HubBoard;
import gg.hazard.spotify.commands.CoreCommand;
import gg.hazard.spotify.configs.ScoreboardConfig;
import gg.hazard.spotify.configs.TablistConfig;
import gg.hazard.spotify.extra.nametags.HubNametagProvider;
import gg.hazard.spotify.extra.nametags.LunarClientNametagProvider;
import gg.hazard.spotify.extra.nametags.impl.NameTagHandler;
import gg.hazard.spotify.extra.skin.SkinAPI;
import gg.hazard.spotify.extra.tablist.TablistHandler;
import gg.hazard.spotify.extra.tablist.adapter.impl.HubTablistAdapter;
import gg.hazard.spotify.listeners.FunListener;
import gg.hazard.spotify.listeners.HubListener;
import gg.hazard.spotify.listeners.ItemListener;
import gg.hazard.spotify.listeners.PreventionListener;
import gg.hazard.spotify.utils.menu.ButtonListener;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class hHub extends JavaPlugin {
    @Getter
    private static hHub instance;

    private PacketEventsAPI<?> packetEventsAPI;
    @Getter
    private NameTagHandler nameTagHandler;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));

        this.packetEventsAPI = PacketEvents.getAPI();
        this.packetEventsAPI.load();
    }

    @Override
    public void onEnable() {
        instance = this;
        new TablistConfig(this);
        new ScoreboardConfig(this);
        new HubBoard(this);
        registerListeners();

        SkinAPI skinAPI = new SkinAPI(this, new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .create());

        TablistHandler tablistHandler = new TablistHandler(this);
        tablistHandler.init(this.packetEventsAPI);
        tablistHandler.setupSkinCache(skinAPI);
        tablistHandler.registerAdapter(new HubTablistAdapter(), 2L);

        this.nameTagHandler = new NameTagHandler(this);
        nameTagHandler.registerAdapter(new HubNametagProvider());

        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new CoreCommand());
    }

    @Override
    public void onDisable() {
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PreventionListener(), this);
        getServer().getPluginManager().registerEvents(new HubListener(), this);
        getServer().getPluginManager().registerEvents(new ItemListener(), this);
        getServer().getPluginManager().registerEvents(new ButtonListener(), this);
        getServer().getPluginManager().registerEvents(new FunListener(), this);
        getServer().getPluginManager().registerEvents(new LunarClientNametagProvider(), this);
    }
}
