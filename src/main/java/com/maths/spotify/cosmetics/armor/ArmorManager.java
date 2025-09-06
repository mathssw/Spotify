package com.maths.spotify.cosmetics.armor;

import com.maths.spotify.utils.CC;
import com.maths.spotify.utils.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ArmorManager {
    
    private static final ArmorManager instance = new ArmorManager();
    private final Map<UUID, ArmorSet> equippedArmor = new HashMap<>();
    
    public static ArmorManager getInstance() {
        return instance;
    }
    
    public void equipArmorSet(Player player, ArmorSet armorSet) {
        removeArmorSet(player);
        
        ItemStack[] armor = createArmorPieces(armorSet);
        player.getInventory().setArmorContents(armor);
        
        applySetBonus(player, armorSet);
        equippedArmor.put(player.getUniqueId(), armorSet);
        
        player.sendMessage(CC.translate("&aYou have equipped the " + armorSet.getSetName() + "&a!"));
    }
    
    public void removeArmorSet(Player player) {
        ArmorSet current = equippedArmor.remove(player.getUniqueId());
        if (current != null) {
            removeSetBonus(player);
            player.getInventory().setArmorContents(new ItemStack[4]);
            player.sendMessage(CC.translate("&cArmor set removed!"));
        }
    }
    
    public boolean hasArmorSet(Player player) {
        return equippedArmor.containsKey(player.getUniqueId());
    }
    
    public ArmorSet getPlayerArmorSet(Player player) {
        return equippedArmor.get(player.getUniqueId());
    }
    
    private ItemStack[] createArmorPieces(ArmorSet armorSet) {
        Material baseMaterial = armorSet.getIcon();
        
        ItemStack helmet = createArmorPiece(baseMaterial, "HELMET", armorSet);
        ItemStack chestplate = createArmorPiece(baseMaterial, "CHESTPLATE", armorSet);
        ItemStack leggings = createArmorPiece(baseMaterial, "LEGGINGS", armorSet);
        ItemStack boots = createArmorPiece(baseMaterial, "BOOTS", armorSet);
        
        return new ItemStack[]{boots, leggings, chestplate, helmet};
    }
    
    private ItemStack createArmorPiece(Material baseMaterial, String piece, ArmorSet armorSet) {
        String materialName = baseMaterial.name().split("_")[0];
        Material pieceMaterial;
        
        try {
            pieceMaterial = Material.valueOf(materialName + "_" + piece);
        } catch (IllegalArgumentException e) {
            pieceMaterial = baseMaterial;
        }
        
        ItemBuilder builder = new ItemBuilder(pieceMaterial)
                .name(armorSet.getSetName() + " " + formatPieceName(piece))
                .lore(armorSet.getLore());
        
        if (armorSet.name().contains("EMERALD") || armorSet.name().contains("MYTHIC")) {
            builder.glow();
        }
        
        return builder.build();
    }
    
    private String formatPieceName(String piece) {
        switch (piece) {
            case "HELMET":
                return "§7Helmet";
            case "CHESTPLATE":
                return "§7Chestplate";
            case "LEGGINGS":
                return "§7Leggings";
            case "BOOTS":
                return "§7Boots";
            default:
                return "§7" + piece;
        }
    }
    
    private void applySetBonus(Player player, ArmorSet armorSet) {
        switch (armorSet) {
            case DIAMOND_WARRIOR:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, true, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, true, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0, true, false));
                break;
            case EMERALD_KING:
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0, true, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, 1, true, false));
                break;
            case GOLD_CHAMPION:
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0, true, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, true, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, true, false));
                break;
            case IRON_GUARD:
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 0, true, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 0, true, false));
                break;
            case CHAINMAIL_SCOUT:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, true, false));
                break;
            case LEATHER_RANGER:
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0, true, false));
                try {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Integer.MAX_VALUE, 0, true, false));
                } catch (Exception e) {
                    // Handle older versions that don't have DOLPHINS_GRACE
                }
                break;
        }
    }
    
    private void removeSetBonus(Player player) {
        player.removePotionEffect(PotionEffectType.SPEED);
        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.removePotionEffect(PotionEffectType.JUMP);
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        player.removePotionEffect(PotionEffectType.LUCK);
        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        player.removePotionEffect(PotionEffectType.ABSORPTION);
        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
        player.removePotionEffect(PotionEffectType.WATER_BREATHING);
        try {
            player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
        } catch (Exception e) {
            // Handle older versions that don't have DOLPHINS_GRACE
        }
    }
}