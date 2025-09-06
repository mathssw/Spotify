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

public class BunkersButton extends Button {
    @Override
    public String getName(Player player) {
        return CC.translate("&5&lBunkers");
    }
    @Override
    public List<String> getDescription(Player player) {
        List<String> lore = new ArrayList();
        lore.add(CC.translate("&7Original HCF minigame."));
        lore.add(CC.translate("&7"));
        lore.add(CC.translate("&5• &fPlayers&7: &d" + BungeeUtil.getPlayerCount("Bunkers")));
        lore.add(CC.translate("&5• &fTeam-Size&7: &d5"));
        lore.add(CC.translate(" "));
        lore.add(CC.translate("&aClick to join the queue"));
        return lore;
    }
    @Override
    public Material getMaterial(Player player) {
        return Material.BEACON;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, InventoryView view) {
        player.performCommand("joinqueue Bunkers");
    }
}