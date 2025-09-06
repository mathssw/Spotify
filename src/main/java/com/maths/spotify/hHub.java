package com.maths.spotify;

import co.aikar.commands.BukkitCommandManager;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.PacketEventsAPI;
import com.google.gson.GsonBuilder;
import com.maths.spotify.board.HubBoard;
import com.maths.spotify.commands.CoreCommand;
import com.maths.spotify.configs.ScoreboardConfig;
import com.maths.spotify.configs.TablistConfig;
import com.maths.spotify.extra.nametags.HubNametagProvider;
import com.maths.spotify.extra.nametags.LunarClientNametagProvider;
import com.maths.spotify.extra.nametags.impl.NameTagHandler;
import com.maths.spotify.extra.skin.SkinAPI;
import com.maths.spotify.extra.tablist.TablistHandler;
import com.maths.spotify.extra.tablist.adapter.impl.HubTablistAdapter;
import com.maths.spotify.listeners.FunListener;
import com.maths.spotify.listeners.HubListener;
import com.maths.spotify.listeners.ItemListener;
import com.maths.spotify.listeners.PreventionListener;
import com.maths.spotify.utils.menu.ButtonListener;
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
        
        registerCosmeticsCommands(commandManager);
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
        getServer().getPluginManager().registerEvents(new com.maths.spotify.cosmetics.listeners.CosmeticsListener(), this);
    }
    
    private void registerCosmeticsCommands(BukkitCommandManager commandManager) {
        commandManager.registerCommand(new com.maths.spotify.cosmetics.CosmeticsCommand());
        commandManager.registerCommand(new com.maths.spotify.cosmetics.pets.PetCommand());
        commandManager.registerCommand(new com.maths.spotify.cosmetics.particles.ParticleCommand());
        commandManager.registerCommand(new com.maths.spotify.cosmetics.armor.ArmorCommand());
    }
}
