package com.maths.spotify.cosmetics.pets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

@Getter
@RequiredArgsConstructor
public enum Pet {
    
    WOLF("Wolf", Material.BONE, "A loyal companion that follows you around the hub"),
    CAT("Cat", Material.FISH, "A cute feline friend that purrs when nearby"),
    RABBIT("Rabbit", Material.CARROT_ITEM, "An energetic bunny that hops around you"),
    PIG("Pig", Material.CARROT_ITEM, "A friendly pig that loves to roll in mud"),
    CHICKEN("Chicken", Material.SEEDS, "A feathered friend that lays occasional eggs"),
    COW("Cow", Material.WHEAT, "A gentle bovine companion that moos softly"),
    SHEEP("Sheep", Material.WHEAT, "A fluffy sheep that provides comfort"),
    HORSE("Horse", Material.APPLE, "A majestic steed that gallops beside you"),
    OCELOT("Ocelot", Material.FISH, "A wild cat with spotted fur"),
    PARROT("Parrot", Material.SEEDS, "A colorful bird that repeats sounds");

    private final String displayName;
    private final Material icon;
    private final String description;
}