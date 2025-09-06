package com.maths.spotify.cosmetics.armor.menu;

import com.maths.spotify.cosmetics.armor.ArmorManager;
import com.maths.spotify.cosmetics.armor.ArmorSet;
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

public class ArmorSelectorMenu extends Menu {
    
    @Override
    public String getTitle(Player player) {
        return CC.translate("&c&lArmor Sets");
    }
    
    @Override
    public int getSize() {
        return 45;
    }
    
    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        
        int[] armorSlots = {10, 11, 12, 13, 14, 15, 16, 19, 20, 25};
        ArmorSet[] armorSets = ArmorSet.values();
        
        for (int i = 0; i < armorSets.length && i < armorSlots.length; i++) {
            buttons.put(armorSlots[i], new ArmorButton(armorSets[i]));
        }
        
        buttons.put(40, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(Material.BARRIER)
                        .name("&c&lRemove Armor")
                        .lore("&7Click to remove your current armor set")
                        .build();
            }
            
            @Override
            public void clicked(Player player, ClickType clickType) {
                ArmorManager.getInstance().removeArmorSet(player);
                player.closeInventory();
            }
        });
        
        return buttons;
    }
    
    private static class ArmorButton extends Button {
        private final ArmorSet armorSet;
        
        public ArmorButton(ArmorSet armorSet) {
            this.armorSet = armorSet;
        }
        
        @Override
        public ItemStack getButtonItem(Player player) {
            ItemBuilder builder = new ItemBuilder(armorSet.getIcon())
                    .name(armorSet.getSetName())
                    .lore(armorSet.getLore());
            
            ArmorSet currentSet = ArmorManager.getInstance().getPlayerArmorSet(player);
            if (currentSet == armorSet) {
                builder.lore(
                        "",
                        "&a&lCURRENTLY EQUIPPED",
                        "&eClick to equip this armor set!"
                );
                builder.glow();
            } else {
                String[] newLore = new String[armorSet.getLore().length + 2];
                System.arraycopy(armorSet.getLore(), 0, newLore, 0, armorSet.getLore().length);
                newLore[armorSet.getLore().length] = "";
                newLore[armorSet.getLore().length + 1] = "&eClick to equip this armor set!";
                builder.lore(newLore);
            }
            
            return builder.build();
        }
        
        @Override
        public void clicked(Player player, ClickType clickType) {
            ArmorManager.getInstance().equipArmorSet(player, armorSet);
            player.closeInventory();
        }
    }
}