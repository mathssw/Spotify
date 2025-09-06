package com.maths.spotify.hotbar;

import com.maths.spotify.utils.CC;
import com.maths.spotify.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HubItems {
    public static final ItemStack SERVER_SELECTOR;
    public static final ItemStack HIDE_PLAYERS;
    public static final ItemStack SHOW_PLAYERS;
    public static final ItemStack ENDER_BUTT;
    public static final ItemStack COSMETICS;

    static {
        SERVER_SELECTOR = new ItemBuilder(Material.COMPASS)
                .name(CC.translate("&6&lServer Selector &7(Right Click)"))
                .lore(
                        "&7Click to browse available servers",
                        "&7and teleport to different game modes!"
                )
                .build();
                
        HIDE_PLAYERS = new ItemBuilder(Material.INK_SACK)
                .data((short)8)
                .name(CC.translate("&8&lHide Players &7(Right Click)"))
                .lore(
                        "&7Click to hide all other players",
                        "&7for a more peaceful experience!"
                )
                .build();
                
        SHOW_PLAYERS = new ItemBuilder(Material.INK_SACK)
                .data((short)10)
                .name(CC.translate("&a&lShow Players &7(Right Click)"))
                .lore(
                        "&7Click to show all players",
                        "&7and interact with the community!"
                )
                .build();
                
        ENDER_BUTT = new ItemBuilder(Material.ENDER_PEARL)
                .name(CC.translate("&5&lPlayer Teleporter &7(Right Click)"))
                .lore(
                        "&7Click to teleport to a random",
                        "&7player in the hub!"
                )
                .build();
                
        COSMETICS = new ItemBuilder(Material.NETHER_STAR)
                .name(CC.translate("&b&lCosmetics &7(Right Click)"))
                .lore(
                        "&7Click to open the cosmetics menu",
                        "&7and customize your appearance!",
                        "",
                        "&7Available cosmetics:",
                        "&7• &ePets",
                        "&7• &dParticle Effects", 
                        "&7• &cArmor Sets"
                )
                .build();
    }
}