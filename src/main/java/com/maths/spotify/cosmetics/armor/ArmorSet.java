package com.maths.spotify.cosmetics.armor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

@Getter
@RequiredArgsConstructor
public enum ArmorSet {
    
    DIAMOND_WARRIOR("Diamond Warrior", Material.DIAMOND_CHESTPLATE, "§b§lDiamond Warrior Set", 
            new String[]{
                "§7A prestigious armor set forged", 
                "§7from the finest diamonds in the realm.",
                "",
                "§e§lSet Bonus:",
                "§7• +10% Movement Speed",
                "§7• Resistance I",
                "§7• Regeneration I",
                "",
                "§6§lRarity: §6§lLEGENDARY"
            }),
    
    EMERALD_KING("Emerald King", Material.LEATHER_CHESTPLATE, "§a§lEmerald King Set",
            new String[]{
                "§7Royal armor worn by the",
                "§7Emerald King of the ancient lands.",
                "",
                "§e§lSet Bonus:",
                "§7• +15% Jump Boost",
                "§7• Night Vision",
                "§7• Luck II",
                "",
                "§d§lRarity: §d§lMYTHIC"
            }),
    
    GOLD_CHAMPION("Gold Champion", Material.GOLD_CHESTPLATE, "§6§lGold Champion Set",
            new String[]{
                "§7Gleaming armor that reflects",
                "§7the glory of countless victories.",
                "",
                "§e§lSet Bonus:",
                "§7• +20% Attack Speed",
                "§7• Strength I",
                "§7• Fire Resistance",
                "",
                "§5§lRarity: §5§lEPIC"
            }),
    
    IRON_GUARD("Iron Guard", Material.IRON_CHESTPLATE, "§7§lIron Guard Set",
            new String[]{
                "§7Sturdy armor forged by the",
                "§7master blacksmiths of the citadel.",
                "",
                "§e§lSet Bonus:",
                "§7• +5% Damage Reduction",
                "§7• Absorption I",
                "§7• Health Boost I",
                "",
                "§9§lRarity: §9§lRARE"
            }),
    
    CHAINMAIL_SCOUT("Chainmail Scout", Material.CHAINMAIL_CHESTPLATE, "§f§lChainmail Scout Set",
            new String[]{
                "§7Lightweight armor perfect for",
                "§7swift reconnaissance missions.",
                "",
                "§e§lSet Bonus:",
                "§7• +25% Movement Speed",
                "§7• Invisibility (10s cooldown)",
                "§7• Feather Falling IV",
                "",
                "§a§lRarity: §a§lUNCOMMON"
            }),
    
    LEATHER_RANGER("Leather Ranger", Material.LEATHER_CHESTPLATE, "§8§lLeather Ranger Set",
            new String[]{
                "§7Traditional ranger equipment",
                "§7crafted from the finest leather.",
                "",
                "§e§lSet Bonus:",
                "§7• +10% Projectile Speed",
                "§7• Water Breathing",
                "§7• Dolphin's Grace",
                "",
                "§f§lRarity: §f§lCOMMON"
            });

    private final String displayName;
    private final Material icon;
    private final String setName;
    private final String[] lore;
}