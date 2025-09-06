package com.maths.spotify.menu.buttons;

import java.util.ArrayList;
import java.util.List;

import com.maths.spotify.utils.BungeeUtil;
import com.maths.spotify.utils.CC;
import com.maths.spotify.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;

public class KitMapButton extends Button {
    public String getName(Player player) {
        return CC.translate("&5&lKitMap");
    }

    public List<String> getDescription(Player player) {
        List<String> lore = new ArrayList();
        lore.add(CC.translate("&7Hardcore factions without"));
        lore.add(CC.translate("&7deathbans and free kits!"));
        lore.add(CC.translate("&7"));
        lore.add(CC.translate("&5• &fPlayers&7: &d" + BungeeUtil.getPlayerCount("KitMap")));
        lore.add(CC.translate("&5• &fMap Kit&7: &dProt 1, Sharp 1"));
        lore.add(CC.translate("&5• &fFaction Size&7: &d30 Man"));
        lore.add(CC.translate(" "));
        lore.add(CC.translate("&aClick to join the queue"));
        return lore;
    }

    public Material getMaterial(Player player) {
        return Material.ENDER_CHEST;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, InventoryView view) {
        player.performCommand("joinqueue Kitmap");
    }
}