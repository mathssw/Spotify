package gg.hazard.spotify.menu.buttons;

import gg.hazard.spotify.utils.BungeeUtil;
import gg.hazard.spotify.utils.CC;
import gg.hazard.spotify.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;

import java.util.ArrayList;
import java.util.List;

public class PracticeButton extends Button {
    public String getName(Player player) {
        return CC.translate("&5&lPractice");
    }

    public List<String> getDescription(Player player) {
        List<String> lore = new ArrayList();
        lore.add(CC.translate("&7Our NA Practice server."));
        lore.add(CC.translate("&7Enjoy practising pvp and more!"));
        lore.add(CC.translate("&7"));
        lore.add(CC.translate("&5• &fPlayers&7: &d" + BungeeUtil.getPlayerCount("HCF")));
        lore.add(CC.translate("&5• &fRegion&7: &dNA"));
        lore.add(CC.translate(" "));
        lore.add(CC.translate("&aClick to join the queue"));
        return lore;
    }

    public Material getMaterial(Player player) {
        return Material.BOOK;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, InventoryView view) {
        player.performCommand("joinqueue Practice");
    }
}