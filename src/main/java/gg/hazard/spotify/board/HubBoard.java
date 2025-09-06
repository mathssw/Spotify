package gg.hazard.spotify.board;

import fr.mrmicky.fastboard.FastBoard;
import gg.hazard.spotify.configs.ScoreboardConfig;
import gg.hazard.spotify.hHub;
import gg.hazard.spotify.utils.BungeeUtil;
import gg.hazard.spotify.utils.CC;
import gg.hazard.spotify.utils.QueueUtils;
import lombok.Getter;
import me.andyreckt.holiday.api.HolidayAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

import static org.bukkit.Bukkit.getServer;

@Getter
public class HubBoard implements Listener {
    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private Long lastMillisTitle = System.currentTimeMillis();
    private Integer iTitle = 0;
    private long lastMillisFooter = System.currentTimeMillis();
    private int iFooter = 0;

    private void updateBoard(FastBoard board) {
        Player player = board.getPlayer();
        if (player == null || !player.isOnline()) return;

        board.updateTitle(CC.replace(player, getTitle()));

        getServer().getScheduler().runTaskAsynchronously(hHub.getInstance(), () -> {
            HolidayAPI holidayAPI = HolidayAPI.getInstance();
            List<String> lines = new ArrayList<>();

            for (String line : ScoreboardConfig.SCOREBOARD_LOBBY) {
                String replaced = line;

                replaced = replaced.replace("%global_count%", String.valueOf(BungeeUtil.getGlobalPlayerCount()));
                replaced = replaced.replace("%footer%", getFooter());
                replaced = replaced.replace("%line%", CC.SCORE_BAR);

                if (replaced.contains("%queue%")) {
                    if (QueueUtils.isInQueue(player)) {
                        for (String qLine : ScoreboardConfig.SCOREBOARD_QUEUE) {
                            String qReplaced = qLine
                                    .replace("%queue_name%", QueueUtils.getQueueName(player))
                                    .replace("%queue_pos%", String.valueOf(QueueUtils.getQueuePos(player)))
                                    .replace("%queue_total%", String.valueOf(QueueUtils.getQueueTotalPlayers(player)));
                            lines.add(CC.translate(qReplaced));
                        }
                    }
                    lines.remove(line);
                    continue;
                }
                replaced = replaced
                        .replace("%rank%", holidayAPI.getProfile(player.getUniqueId()).getDisplayRank().getDisplayName())
                        .replace("%color%", holidayAPI.getProfile(player.getUniqueId()).getDisplayRank().getColor())
                        .replace("%name%",  player.getName());

                lines.add(CC.translate(replaced));
            }

            getServer().getScheduler().runTask(hHub.getInstance(), () -> {
                if (!board.isDeleted()) board.updateLines(lines);
            });
        });
    }

    public HubBoard(hHub plugin) {
        getServer().getScheduler().runTaskTimer(plugin, () -> {
            for (FastBoard board : getBoards().values()) {
                updateBoard(board);
            }
        }, 0L, 2L);
        getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        FastBoard board = new FastBoard(player);
        getBoards().put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        FastBoard board = getBoards().remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }
    private String getTitle() {
        List<String> frames = CC.translate(ScoreboardConfig.SCOREBOARD_TITLE);
        long time = System.currentTimeMillis();

        if (lastMillisTitle + 20L <= time) {
            if (iTitle < frames.size() - 1) {
                iTitle++;
            } else {
                iTitle = 0;
            }
            lastMillisTitle = time;
        }

        return frames.get(iTitle).replace("|", "┃");
    }

    private String getFooter() {
        List<String> footers = CC.translate(ScoreboardConfig.SCOREBOARD_FOOTER);
        long time = System.currentTimeMillis();

        if (footers.isEmpty()) return "";

        if (lastMillisFooter + 2000L <= time) {
            iFooter = (iFooter + 1) % footers.size();
            lastMillisFooter = time;
        }

        if (iFooter >= footers.size()) iFooter = 0;
        return footers.get(iFooter);
    }
}
