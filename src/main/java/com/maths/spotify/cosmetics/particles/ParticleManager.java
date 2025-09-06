package com.maths.spotify.cosmetics.particles;

import com.maths.spotify.utils.CC;
import com.maths.spotify.utils.ParticleEffect;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ParticleManager {
    
    private static final ParticleManager instance = new ParticleManager();
    private final Map<UUID, ParticleData> activeParticles = new HashMap<>();
    
    public static ParticleManager getInstance() {
        return instance;
    }
    
    public void startParticleEffect(Player player, ParticleType particleType) {
        stopParticleEffect(player);
        
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                    activeParticles.remove(player.getUniqueId());
                    return;
                }
                
                displayParticle(player, particleType);
            }
        }.runTaskTimer(player.getServer().getPluginManager().getPlugin("hHub"), 0L, 10L);
        
        activeParticles.put(player.getUniqueId(), new ParticleData(particleType, task));
        player.sendMessage(CC.translate("&aParticle effect &e" + particleType.getDisplayName() + " &aactivated!"));
    }
    
    public void stopParticleEffect(Player player) {
        ParticleData data = activeParticles.remove(player.getUniqueId());
        if (data != null) {
            data.getTask().cancel();
            player.sendMessage(CC.translate("&cParticle effect disabled!"));
        }
    }
    
    public boolean hasParticleEffect(Player player) {
        return activeParticles.containsKey(player.getUniqueId());
    }
    
    public ParticleType getPlayerParticleType(Player player) {
        ParticleData data = activeParticles.get(player.getUniqueId());
        return data != null ? data.getType() : null;
    }
    
    private void displayParticle(Player player, ParticleType type) {
        Location location = player.getLocation().add(0, 1, 0);
        
        try {
            switch (type) {
                case HEART:
                    ParticleEffect.HEART.display(0.5f, 1.0f, 0.5f, 0.1f, 3, location);
                    break;
                case FLAME:
                    ParticleEffect.FLAME.display(0.3f, 0.5f, 0.3f, 0.02f, 5, location);
                    break;
                case WATER:
                    ParticleEffect.WATER_SPLASH.display(0.5f, 0.2f, 0.5f, 0.1f, 8, location);
                    break;
                case RAINBOW:
                    createRainbowEffect(location);
                    break;
                case SNOW:
                    ParticleEffect.SNOW_SHOVEL.display(1.0f, 2.0f, 1.0f, 0f, 10, location);
                    break;
                case MAGIC:
                    ParticleEffect.ENCHANTMENT_TABLE.display(1.0f, 1.0f, 1.0f, 1.0f, 15, location);
                    break;
                case CLOUD:
                    ParticleEffect.CLOUD.display(0.5f, 0.1f, 0.5f, 0.02f, 5, player.getLocation());
                    break;
                case STAR:
                    ParticleEffect.FIREWORKS_SPARK.display(0.8f, 1.0f, 0.8f, 0.1f, 8, location);
                    break;
                case EMERALD:
                    ParticleEffect.VILLAGER_HAPPY.display(0.5f, 1.0f, 0.5f, 0.1f, 5, location);
                    break;
                case LIGHTNING:
                    ParticleEffect.REDSTONE.display(0.3f, 0.5f, 0.3f, 0.1f, 10, location);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createRainbowEffect(Location location) {
        try {
            ParticleEffect.REDSTONE.display(0.3f, 0.3f, 0.3f, 0.1f, 2, location.clone().add(0.5, 0, 0));
            ParticleEffect.REDSTONE.display(0.3f, 0.3f, 0.3f, 0.1f, 2, location.clone().add(-0.5, 0, 0));
            ParticleEffect.REDSTONE.display(0.3f, 0.3f, 0.3f, 0.1f, 2, location.clone().add(0, 0, 0.5));
            ParticleEffect.REDSTONE.display(0.3f, 0.3f, 0.3f, 0.1f, 2, location.clone().add(0, 0, -0.5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Getter
    private static class ParticleData {
        private final ParticleType type;
        private final BukkitTask task;
        
        public ParticleData(ParticleType type, BukkitTask task) {
            this.type = type;
            this.task = task;
        }
    }
}