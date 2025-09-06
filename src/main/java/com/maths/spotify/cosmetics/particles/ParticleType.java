package com.maths.spotify.cosmetics.particles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

@Getter
@RequiredArgsConstructor
public enum ParticleType {
    
    HEART("Love Hearts", Material.RED_ROSE, "Romantic hearts floating around you"),
    FLAME("Fire Trail", Material.BLAZE_POWDER, "Fiery particles that follow your movement"),
    WATER("Water Splash", Material.WATER_BUCKET, "Refreshing water droplets around you"),
    RAINBOW("Rainbow Aura", Material.WOOL, "Colorful rainbow particles surrounding you"),
    SNOW("Snow Storm", Material.SNOW_BALL, "Gentle snowflakes falling around you"),
    MAGIC("Magic Sparkles", Material.NETHER_STAR, "Mystical sparkles that dance around you"),
    CLOUD("Cloud Walker", Material.WOOL, "Fluffy cloud particles at your feet"),
    STAR("Starlight", Material.GLOWSTONE_DUST, "Twinkling stars following your path"),
    EMERALD("Emerald Dust", Material.EMERALD, "Precious emerald particles around you"),
    LIGHTNING("Electric Aura", Material.REDSTONE, "Electric sparks crackling around you");

    private final String displayName;
    private final Material icon;
    private final String description;
}