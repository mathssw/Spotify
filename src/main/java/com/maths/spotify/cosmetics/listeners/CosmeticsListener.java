package com.maths.spotify.cosmetics.listeners;

import com.maths.spotify.cosmetics.pets.PetManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CosmeticsListener implements Listener {
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (PetManager.getInstance().hasPet(player)) {
            PetManager.getInstance().updatePetLocation(player);
        }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        if (PetManager.getInstance().hasPet(player)) {
            PetManager.getInstance().removePet(player);
        }
    }
}