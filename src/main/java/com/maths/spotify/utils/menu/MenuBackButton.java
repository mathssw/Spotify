/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.InventoryView
 */
package com.maths.spotify.utils.menu;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;

import java.util.List;
import java.util.function.Consumer;

public final class MenuBackButton
extends Button {
    private final Consumer<Player> openPreviousMenuConsumer;

    public MenuBackButton(Consumer<Player> openPreviousMenuConsumer) {
        this.openPreviousMenuConsumer = Preconditions.checkNotNull(openPreviousMenuConsumer, "openPreviousMenuConsumer");
    }

    @Override
    public String getName(Player player) {
        return ChatColor.RED.toString() + (Object)ChatColor.BOLD + "Back";
    }

    @Override
    public List<String> getDescription(Player player) {
        return ImmutableList.of("", (Object)ChatColor.RED + "Click here to return to", (Object)ChatColor.RED + "the previous menu.");
    }

    @Override
    public Material getMaterial(Player player) {
        return Material.REDSTONE;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, InventoryView view) {
        player.closeInventory();
        this.openPreviousMenuConsumer.accept(player);
    }
}

