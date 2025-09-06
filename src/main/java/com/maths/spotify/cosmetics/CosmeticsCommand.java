package com.maths.spotify.cosmetics;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.maths.spotify.cosmetics.armor.menu.ArmorSelectorMenu;
import com.maths.spotify.cosmetics.particles.menu.ParticleSelectorMenu;
import com.maths.spotify.cosmetics.pets.menu.PetSelectorMenu;
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

@CommandAlias("cosmetics|cosmetic")
@CommandPermission("hub.cosmetics")
public class CosmeticsCommand extends BaseCommand {
    
    @Default
    public void openMenu(Player player) {
        new CosmeticsMainMenu().openMenu(player);
    }
    
    private static class CosmeticsMainMenu extends Menu {
        
        @Override
        public String getTitle(Player player) {
            return CC.translate("&b&lCosmetics Menu");
        }
        
        @Override
        public int getSize() {
            return 27;
        }
        
        @Override
        public Map<Integer, Button> getButtons(Player player) {
            Map<Integer, Button> buttons = new HashMap<>();
            
            buttons.put(10, new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    return new ItemBuilder(Material.BONE)
                            .name("&e&lPets")
                            .lore(
                                    "&7Spawn cute pets that follow",
                                    "&7you around the hub!",
                                    "",
                                    "&eClick to open pet menu!"
                            )
                            .build();
                }
                
                @Override
                public void clicked(Player player, ClickType clickType) {
                    new PetSelectorMenu().openMenu(player);
                }
            });
            
            buttons.put(13, new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    return new ItemBuilder(Material.NETHER_STAR)
                            .name("&d&lParticle Effects")
                            .lore(
                                    "&7Activate stunning particle effects",
                                    "&7that surround your player!",
                                    "",
                                    "&eClick to open particle menu!"
                            )
                            .build();
                }
                
                @Override
                public void clicked(Player player, ClickType clickType) {
                    new ParticleSelectorMenu().openMenu(player);
                }
            });
            
            buttons.put(16, new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    return new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                            .name("&c&lArmor Sets")
                            .lore(
                                    "&7Equip exclusive armor sets with",
                                    "&7unique set bonuses and effects!",
                                    "",
                                    "&eClick to open armor menu!"
                            )
                            .build();
                }
                
                @Override
                public void clicked(Player player, ClickType clickType) {
                    new ArmorSelectorMenu().openMenu(player);
                }
            });
            
            return buttons;
        }
    }
}