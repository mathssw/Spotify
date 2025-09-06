package gg.hazard.spotify.menu.buttons;

import java.util.ArrayList;
import java.util.List;

import gg.hazard.spotify.utils.BungeeUtil;
import gg.hazard.spotify.utils.CC;
import gg.hazard.spotify.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;

public class HCFButton extends Button {
    public String getName(Player player) {
        return CC.translate("&5&lHCF");
    }

    public List<String> getDescription(Player player) {
        List<String> lore = new ArrayList<>();
        lore.add(CC.translate("&7Hardcore factions with many"));
        lore.add(CC.translate("&7features to play and win!"));
        lore.add(CC.translate("&7"));
        lore.add(CC.translate("&5• &fPlayers&7: &d" + BungeeUtil.getPlayerCount("HCF")));
        lore.add(CC.translate("&5• &fMap Kit&7: &dProt 2, Sharp 2"));
        lore.add(CC.translate("&5• &fFaction Size&7: &d8 Man"));
        lore.add(CC.translate(" "));
        lore.add(CC.translate("&aClick to join the queue"));
        return lore;
    }

    public Material getMaterial(Player player) {
        return Material.DIAMOND_SWORD;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, InventoryView view) {
        player.performCommand("joinqueue HCF");
    }
}