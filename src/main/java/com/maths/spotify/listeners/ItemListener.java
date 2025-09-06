package com.maths.spotify.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.maths.spotify.hHub;
import com.maths.spotify.hotbar.HubItems;
import com.maths.spotify.menu.HubSelectorMenu;
import lombok.Getter;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public class ItemListener implements Listener {
    private final List<Player> hidingPlayers = new ArrayList<>();
    private final Map<Player, Long> lastDone = new HashMap<>();

    @EventHandler
    public void click(final PlayerInteractEvent e) {
        if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && e.getItem() != null) {
            if (HubItems.SERVER_SELECTOR.isSimilar(e.getItem())) {
                (new BukkitRunnable() {
                    public void run() {
                        new HubSelectorMenu().openMenu(e.getPlayer());
                    }
                }).runTaskLaterAsynchronously(hHub.getInstance(), 2L);
            } else if (HubItems.ENDER_BUTT.isSimilar(e.getItem())) {
                e.setCancelled(true);
                e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(2.5D));
                e.getPlayer().updateInventory();
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 100.0F, 2.0F);
            } else {
                if (HubItems.HIDE_PLAYERS.isSimilar(e.getItem())) {
                    if (this.canUseHide(e.getPlayer())) {
                        e.getPlayer().sendMessage(ChatColor.RED + "You toggled player visibility off!");
                        e.getPlayer().getInventory().setItem(8, HubItems.SHOW_PLAYERS);
                        if (!this.getHidingPlayers().contains(e.getPlayer())) {
                            this.getHidingPlayers().add(e.getPlayer());
                        }

                        this.lastDone.put(e.getPlayer(), System.currentTimeMillis());
                        for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            e.getPlayer().hidePlayer(onlinePlayer);
                        }
                    }
                } else if (HubItems.SHOW_PLAYERS.isSimilar(e.getItem())) {
                    if (!this.canUseHide(e.getPlayer())) {
                        return;
                    }

                    e.getPlayer().sendMessage(ChatColor.GREEN + "You toggled player visibility on!");
                    e.getPlayer().getInventory().setItem(8, HubItems.HIDE_PLAYERS);
                    this.getHidingPlayers().remove(e.getPlayer());
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        e.getPlayer().showPlayer(player);
                    }

                    this.lastDone.put(e.getPlayer(), System.currentTimeMillis());
                }
            }
        }

    }

    private boolean canUseHide(Player player) {
        if (this.getLastDone().get(player) == null) {
            return true;
        } else if (this.getLastDone().get(player) + 5000L <= System.currentTimeMillis()) {
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "Please wait " + DurationFormatUtils.formatDurationWords(this.getLastDone().get(player) + 5000L - System.currentTimeMillis(), true, false) + " to toggle player visibility!");
            return false;
        }
    }

}