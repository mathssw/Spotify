package com.maths.spotify.cosmetics.particles.menu;

import com.maths.spotify.cosmetics.particles.ParticleManager;
import com.maths.spotify.cosmetics.particles.ParticleType;
import com.maths.spotify.utils.CC;
import com.maths.spotify.utils.ItemBuilder;
import com.maths.spotify.utils.menu.Button;
import com.maths.spotify.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ParticleSelectorMenu extends Menu {
    
    @Override
    public String getTitle(Player player) {
        return CC.translate("&d&lParticle Effects");
    }
    
    @Override
    public int getSize() {
        return 54;
    }
    
    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        
        int[] particleSlots = {10, 11, 12, 13, 14, 15, 16, 19, 20, 25};
        ParticleType[] particles = ParticleType.values();
        
        for (int i = 0; i < particles.length && i < particleSlots.length; i++) {
            buttons.put(particleSlots[i], new ParticleButton(particles[i]));
        }
        
        buttons.put(49, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.BARRIER)
                        .name("&c&lDisable Particles")
                        .lore("&7Click to disable all particle effects")
                        .build();
            }
            
            @Override
            public void clicked(Player player, ClickType clickType) {
                ParticleManager.getInstance().stopParticleEffect(player);
                player.closeInventory();
            }
        });
        
        return buttons;
    }
    
    private static class ParticleButton extends Button {
        private final ParticleType particleType;
        
        public ParticleButton(ParticleType particleType) {
            this.particleType = particleType;
        }
        
        @Override
        public ItemStack getButtonItem(Player player) {
            ItemBuilder builder = new ItemBuilder(particleType.getIcon())
                    .name("&d&l" + particleType.getDisplayName())
                    .lore(
                            "&7" + particleType.getDescription(),
                            "",
                            "&eClick to activate this particle effect!"
                    );
            
            ParticleType currentType = ParticleManager.getInstance().getPlayerParticleType(player);
            if (currentType == particleType) {
                builder.lore(
                        "&7" + particleType.getDescription(),
                        "",
                        "&a&lCURRENTLY ACTIVE",
                        "&eClick to activate this particle effect!"
                );
                builder.glow();
            }
            
            return builder.build();
        }
        
        @Override
        public void clicked(Player player, ClickType clickType) {
            ParticleManager.getInstance().startParticleEffect(player, particleType);
            player.closeInventory();
        }
    }
}