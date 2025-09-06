package gg.hazard.spotify.utils;

import io.github.maths.queue.shared.queue.Queue;
import org.bukkit.entity.Player;

public class QueueUtils {

    public static boolean isInQueue(Player player) {
        return (Queue.getByPlayer(player.getUniqueId()) != null);
    }

    public static String getQueueName(Player player) {
        if(!isInQueue(player)) return "";
        return Queue.getByPlayer(player.getUniqueId()).getName();
    }

    public static int getQueuePos(Player player) {
        if(!isInQueue(player)) return 0;
        return Queue.getByPlayer(player.getUniqueId()).getPosition(player.getUniqueId());
    }

    public static int getQueueTotalPlayers(Player player) {
        if(!isInQueue(player)) return 0;
        return Queue.getByPlayer(player.getUniqueId()).getPlayers().size();
    }
}
