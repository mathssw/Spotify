package com.maths.spotify.cosmetics.particles;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.maths.spotify.cosmetics.particles.menu.ParticleSelectorMenu;
import com.maths.spotify.utils.CC;
import org.bukkit.entity.Player;

@CommandAlias("particles|particle")
@CommandPermission("hub.particles")
public class ParticleCommand extends BaseCommand {
    
    @Subcommand("menu|gui")
    public void openMenu(Player player) {
        new ParticleSelectorMenu().openMenu(player);
    }
    
    @Subcommand("start")
    @CommandCompletion("heart|flame|water|rainbow|snow|magic|cloud|star|emerald|lightning")
    public void startParticle(Player player, String particleName) {
        try {
            ParticleType type = ParticleType.valueOf(particleName.toUpperCase());
            ParticleManager.getInstance().startParticleEffect(player, type);
        } catch (IllegalArgumentException e) {
            player.sendMessage(CC.translate("&cInvalid particle type! Use /particles list to see available types."));
        }
    }
    
    @Subcommand("stop|clear")
    public void stopParticle(Player player) {
        if (ParticleManager.getInstance().hasParticleEffect(player)) {
            ParticleManager.getInstance().stopParticleEffect(player);
        } else {
            player.sendMessage(CC.translate("&cYou don't have any active particle effects!"));
        }
    }
    
    @Subcommand("list")
    public void listParticles(Player player) {
        player.sendMessage(CC.translate("&e&lAvailable Particle Effects:"));
        for (ParticleType type : ParticleType.values()) {
            player.sendMessage(CC.translate("&7- &a" + type.getDisplayName() + "&7: " + type.getDescription()));
        }
    }
}