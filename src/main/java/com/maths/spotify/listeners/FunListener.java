package com.maths.spotify.listeners;

import com.maths.spotify.hHub;
import com.maths.spotify.utils.ParticleEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class FunListener implements Listener {
    @EventHandler
    public void doubleJumpMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE)) return;
        if (!((LivingEntity) player).isOnGround())
            if (player.getAllowFlight()) return;

        boolean onGround = ((Entity)player).isOnGround();
        if (player.hasMetadata("DOUBLE_JUMPED") && onGround) {
            player.removeMetadata("DOUBLE_JUMPED", hHub.getInstance());
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void doubleJumpFlight(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE)) return;

        e.setCancelled(true);
        player.setFlying(false);
        player.setAllowFlight(false);

        if (player.hasMetadata("BOOSTED")) {
            player.removeMetadata("BOOSTED", hHub.getInstance());
        }

        if (!player.hasMetadata("DOUBLE_JUMPED")) {
            player.setMetadata("DOUBLE_JUMPED", new FixedMetadataValue(hHub.getInstance(), true));
            final Location loc = player.getLocation();
            final double otherBoost = 2.5;
            final Sound sound = Sound.PISTON_EXTEND;
            final Vector vector = loc.getDirection().multiply(otherBoost).setY(1.75);
            player.setVelocity(vector);
            player.playSound(loc, sound, 2,2);
            ParticleEffect.CLOUD.display(0 ,-0.5F, 0, 0.05F, 25, loc, player);
        }
    }

    @EventHandler
    public void playerSneak(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            Player player = e.getPlayer();

            if (player.getGameMode().equals(GameMode.CREATIVE)) return;

            boolean onGround = ((Entity)player).isOnGround();
            if (!player.hasMetadata("BOOSTED") && !onGround) {
                player.setMetadata("BOOSTED", new FixedMetadataValue(hHub.getInstance(), true));
                final Location loc = player.getLocation();
                final double otherBoost = 3;
                final Sound sound = Sound.BAT_TAKEOFF;
                final Vector vector = loc.getDirection().multiply(otherBoost);
                player.setVelocity(vector);
                player.playSound(loc, sound, 2,2);
                ParticleEffect.FLAME.display(0 ,-0.5F, 0, 0.05F, 25, loc, player);
            }
        }
    }
}
