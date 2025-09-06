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

    static {
        SERVER_SELECTOR = new ItemBuilder(Material.COMPASS).name(CC.translate("&5Server Selector &7(Right Click)")).build();
        HIDE_PLAYERS = new ItemBuilder(Material.INK_SACK).data((short)8).name(CC.translate("&8Hide Players &7(Right Click)")).build();
        SHOW_PLAYERS = new ItemBuilder(Material.INK_SACK).data((short)10).name(CC.translate("&aShow Players &7(Right Click)")).build();
        ENDER_BUTT = new ItemBuilder(Material.ENDER_PEARL).name(CC.translate("&5Ender Butt &7(Right Click)")).build();
    }
}