package com.maths.spotify.cosmetics.armor;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.maths.spotify.cosmetics.armor.menu.ArmorSelectorMenu;
import com.maths.spotify.utils.CC;
import org.bukkit.entity.Player;

@CommandAlias("armor|armour")
@CommandPermission("hub.armor")
public class ArmorCommand extends BaseCommand {
    
    @Subcommand("menu|gui")
    public void openMenu(Player player) {
        new ArmorSelectorMenu().openMenu(player);
    }
    
    @Subcommand("equip")
    @CommandCompletion("diamond_warrior|emerald_king|gold_champion|iron_guard|chainmail_scout|leather_ranger")
    public void equipArmor(Player player, String armorName) {
        try {
            ArmorSet armorSet = ArmorSet.valueOf(armorName.toUpperCase());
            ArmorManager.getInstance().equipArmorSet(player, armorSet);
        } catch (IllegalArgumentException e) {
            player.sendMessage(CC.translate("&cInvalid armor set! Use /armor list to see available sets."));
        }
    }
    
    @Subcommand("remove|clear")
    public void removeArmor(Player player) {
        if (ArmorManager.getInstance().hasArmorSet(player)) {
            ArmorManager.getInstance().removeArmorSet(player);
        } else {
            player.sendMessage(CC.translate("&cYou are not wearing any cosmetic armor!"));
        }
    }
    
    @Subcommand("list")
    public void listArmor(Player player) {
        player.sendMessage(CC.translate("&e&lAvailable Armor Sets:"));
        for (ArmorSet armorSet : ArmorSet.values()) {
            player.sendMessage(CC.translate("&7- " + armorSet.getSetName()));
        }
    }
}