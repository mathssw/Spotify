package gg.hazard.spotify.listeners;

import gg.hazard.spotify.hHub;
import gg.hazard.spotify.hotbar.HubItems;
import gg.hazard.spotify.utils.CC;
import gg.hazard.spotify.utils.ItemBuilder;
import me.andyreckt.holiday.api.HolidayAPI;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;

public class HubListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(CC.replace(e.getPlayer(), "&7[&a+&7] %color_name%"));

        Player player = e.getPlayer();

        player.spigot().setCollidesWithEntities(false);
        player.setGameMode(GameMode.ADVENTURE);
        player.setLevel(-1000);
        sendFakeGamemode(player, -1);
        player.setAllowFlight(true);
        player.teleport(player.getWorld().getSpawnLocation().clone().add(0.5, 0, 0.5));

        PlayerInventory inventory = player.getInventory();
        inventory.clear();
        inventory.setArmorContents(null);
        Bukkit.getScheduler().runTaskLater(hHub.getInstance(), () -> {
            player.getInventory().setHeldItemSlot(4);
            player.getInventory().setItem(4, HubItems.SERVER_SELECTOR);
            player.getInventory().setItem(0, HubItems.ENDER_BUTT);
            player.getInventory().setItem(8, HubItems.HIDE_PLAYERS);

            Color color = CC.translateChatColorToColor(ChatColor.valueOf(HolidayAPI.getInstance().getProfile(player.getUniqueId()).getDisplayRank().getColor()));
            e.getPlayer().getEquipment().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).armorColor(color).build());
            e.getPlayer().getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).armorColor(color).build());
            e.getPlayer().getEquipment().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).armorColor(color).build());
            e.getPlayer().getEquipment().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).armorColor(color).build());
        }, 2L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(CC.replace(e.getPlayer(), "&7[&c-&7] %color_name%"));
    }

    public void sendFakeGamemode(Player player, int gamemode) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(3, gamemode));
    }
}
