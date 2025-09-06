package com.maths.spotify.menu.buttons;

import java.util.List;

import com.maths.spotify.utils.ItemBuilder;
import com.maths.spotify.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GlassButton extends Button {
    private final int glassData;

    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.STAINED_GLASS_PANE).name(" ").data((short)this.glassData).build();
    }

    public String getName(Player player) {
        return null;
    }

    public List<String> getDescription(Player player) {
        return null;
    }

    public Material getMaterial(Player player) {
        return null;
    }

    public GlassButton(int glassData) {
        this.glassData = glassData;
    }
}