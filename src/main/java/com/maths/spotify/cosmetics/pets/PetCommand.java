package com.maths.spotify.cosmetics.pets;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.maths.spotify.cosmetics.pets.menu.PetSelectorMenu;
import com.maths.spotify.utils.CC;
import org.bukkit.entity.Player;

@CommandAlias("pet|pets")
@CommandPermission("hub.pet")
public class PetCommand extends BaseCommand {
    
    @Subcommand("menu|gui")
    public void openMenu(Player player) {
        new PetSelectorMenu().openMenu(player);
    }
    
    @Subcommand("spawn")
    @CommandCompletion("wolf|cat|rabbit|pig|chicken|cow|sheep|horse|ocelot|parrot")
    public void spawnPet(Player player, String petName) {
        try {
            Pet pet = Pet.valueOf(petName.toUpperCase());
            PetManager.getInstance().spawnPet(player, pet);
        } catch (IllegalArgumentException e) {
            player.sendMessage(CC.translate("&cInvalid pet type! Available pets: wolf, cat, rabbit, pig, chicken, cow, sheep, horse, ocelot, parrot"));
        }
    }
    
    @Subcommand("remove|clear")
    public void removePet(Player player) {
        if (PetManager.getInstance().hasPet(player)) {
            PetManager.getInstance().removePet(player);
        } else {
            player.sendMessage(CC.translate("&cYou don't have an active pet!"));
        }
    }
    
    @Subcommand("list")
    public void listPets(Player player) {
        player.sendMessage(CC.translate("&e&lAvailable Pets:"));
        for (Pet pet : Pet.values()) {
            player.sendMessage(CC.translate("&7- &a" + pet.getDisplayName() + "&7: " + pet.getDescription()));
        }
    }
}