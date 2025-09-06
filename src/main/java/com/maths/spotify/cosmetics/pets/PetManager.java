package com.maths.spotify.cosmetics.pets;

import com.maths.spotify.utils.CC;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class PetManager {
    
    private static final PetManager instance = new PetManager();
    private final Map<UUID, PetData> activePets = new HashMap<>();
    
    public static PetManager getInstance() {
        return instance;
    }
    
    public void spawnPet(Player player, Pet pet) {
        removePet(player);
        
        Location spawnLocation = player.getLocation().add(0, 0, -2);
        EntityType entityType = getEntityType(pet);
        
        if (entityType != null) {
            Entity entity = player.getWorld().spawnEntity(spawnLocation, entityType);
            entity.setCustomName(CC.translate("&e" + pet.getDisplayName() + " &7(" + player.getName() + ")"));
            entity.setCustomNameVisible(true);
            
            PetData petData = new PetData(pet, entity);
            activePets.put(player.getUniqueId(), petData);
            
            player.sendMessage(CC.translate("&aYou have spawned a " + pet.getDisplayName() + " pet!"));
        }
    }
    
    public void removePet(Player player) {
        PetData petData = activePets.remove(player.getUniqueId());
        if (petData != null && petData.getEntity().isValid()) {
            petData.getEntity().remove();
            player.sendMessage(CC.translate("&cYour pet has been removed!"));
        }
    }
    
    public void updatePetLocation(Player player) {
        PetData petData = activePets.get(player.getUniqueId());
        if (petData != null && petData.getEntity().isValid()) {
            Entity pet = petData.getEntity();
            Location playerLocation = player.getLocation();
            Location petLocation = pet.getLocation();
            
            double distance = playerLocation.distance(petLocation);
            
            if (distance > 10) {
                pet.teleport(playerLocation.add(0, 0, -2));
            } else if (distance > 3) {
                Vector direction = playerLocation.toVector().subtract(petLocation.toVector()).normalize();
                direction.multiply(0.3);
                pet.setVelocity(direction);
            }
        }
    }
    
    public boolean hasPet(Player player) {
        return activePets.containsKey(player.getUniqueId());
    }
    
    public Pet getPlayerPet(Player player) {
        PetData petData = activePets.get(player.getUniqueId());
        return petData != null ? petData.getPet() : null;
    }
    
    private EntityType getEntityType(Pet pet) {
        switch (pet) {
            case WOLF:
                return EntityType.WOLF;
            case CAT:
            case OCELOT:
                return EntityType.OCELOT;
            case RABBIT:
                return EntityType.RABBIT;
            case PIG:
                return EntityType.PIG;
            case CHICKEN:
                return EntityType.CHICKEN;
            case COW:
                return EntityType.COW;
            case SHEEP:
                return EntityType.SHEEP;
            case HORSE:
                return EntityType.HORSE;
            case PARROT:
                return EntityType.BAT;
            default:
                return null;
        }
    }
    
    @Getter
    private static class PetData {
        private final Pet pet;
        private final Entity entity;
        
        public PetData(Pet pet, Entity entity) {
            this.pet = pet;
            this.entity = entity;
        }
    }
}