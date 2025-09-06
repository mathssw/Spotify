package com.maths.spotify.cosmetics.pets.menu;

import com.maths.spotify.cosmetics.pets.Pet;
import com.maths.spotify.cosmetics.pets.PetManager;
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

public class PetSelectorMenu extends Menu {
    
    @Override
    public String getTitle(Player player) {
        return CC.translate("&6&lPet Selector");
    }
    
    @Override
    public int getSize() {
        return 45;
    }
    
    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        
        int[] petSlots = {10, 11, 12, 13, 14, 15, 16, 19, 20, 25};
        Pet[] pets = Pet.values();
        
        for (int i = 0; i < pets.length && i < petSlots.length; i++) {
            buttons.put(petSlots[i], new PetButton(pets[i]));
        }
        
        buttons.put(40, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.BARRIER)
                        .name("&c&lRemove Pet")
                        .lore("&7Click to remove your current pet")
                        .build();
            }
            
            @Override
            public void clicked(Player player, ClickType clickType) {
                PetManager.getInstance().removePet(player);
                player.closeInventory();
            }
        });
        
        return buttons;
    }
    
    private static class PetButton extends Button {
        private final Pet pet;
        
        public PetButton(Pet pet) {
            this.pet = pet;
        }
        
        @Override
        public ItemStack getButtonItem(Player player) {
            ItemBuilder builder = new ItemBuilder(pet.getIcon())
                    .name("&e&l" + pet.getDisplayName())
                    .lore(
                            "&7" + pet.getDescription(),
                            "",
                            "&eClick to spawn this pet!"
                    );
            
            Pet currentPet = PetManager.getInstance().getPlayerPet(player);
            if (currentPet == pet) {
                builder.lore(
                        "&7" + pet.getDescription(),
                        "",
                        "&a&lCURRENTLY ACTIVE",
                        "&eClick to spawn this pet!"
                );
                builder.glow();
            }
            
            return builder.build();
        }
        
        @Override
        public void clicked(Player player, ClickType clickType) {
            PetManager.getInstance().spawnPet(player, pet);
            player.closeInventory();
        }
    }
}