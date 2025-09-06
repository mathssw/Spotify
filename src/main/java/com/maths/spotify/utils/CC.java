package com.maths.spotify.utils;

import me.andyreckt.holiday.api.HolidayAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class CC {
    public static final String SEPARATOR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "---------------------";
    public static String SCORE_BAR = ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "-------------------";
    public static String MENU_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------";
    public static String CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------------------------------";
    public static String MEDIUM_CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------------";

    private static final DecimalFormat KDR_FORMAT = new DecimalFormat("0.00");

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> translate(List<String> strings) {
        return strings.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static String replace(Player player, String s) {
        HolidayAPI holidayAPI = HolidayAPI.getInstance();
        s = PlaceholderAPI.setPlaceholders(player, s
                .replace("%rank%", holidayAPI.getProfile(player.getUniqueId()).getDisplayRank().getDisplayName())
                .replace("%name%", player.getName())
                .replace("%color_name%", ChatColor.valueOf(holidayAPI.getProfile(player.getUniqueId()).getDisplayRank().getColor()) + player.getName())
                .replace("%queue-name%", QueueUtils.getQueueName(player)))
                .replace("%queue-pos%", QueueUtils.getQueuePos(player) + "")
                .replace("%queue-length%", QueueUtils.getQueueTotalPlayers(player) + "");
        return translate(s);
    }

    public static String replaceNoColor(Player player, String s) {
        return s;
    }

    public static List<String> replace(Player player, List<String> strings) {
        return strings.stream().map(s -> replace(player, s)).collect(Collectors.toList());
    }

    public static List<String> replaceNoColor(Player player, List<String> strings) {
        return strings.stream().map(s -> replaceNoColor(player, s)).collect(Collectors.toList());
    }

    public static Color translateChatColorToColor(ChatColor chatColor)
    {
        switch (chatColor) {
            case AQUA:
                return Color.AQUA;
            case BLACK:
                return Color.BLACK;
            case BLUE:
                return Color.BLUE;
            case DARK_AQUA:
                return Color.BLUE;
            case DARK_BLUE:
                return Color.BLUE;
            case DARK_GRAY:
                return Color.GRAY;
            case DARK_GREEN:
                return Color.GREEN;
            case DARK_PURPLE:
                return Color.PURPLE;
            case DARK_RED:
                return Color.RED;
            case GOLD:
                return Color.YELLOW;
            case GRAY:
                return Color.GRAY;
            case GREEN:
                return Color.GREEN;
            case LIGHT_PURPLE:
                return Color.PURPLE;
            case RED:
                return Color.RED;
            case WHITE:
                return Color.WHITE;
            case YELLOW:
                return Color.YELLOW;
            default:
                break;
        }
        return null;
    }
}
