package com.maths.spotify.utils;

import me.andyreckt.holiday.api.HolidayAPI;
import me.andyreckt.holiday.api.server.IServer;
import me.andyreckt.holiday.api.server.ServerStatus;
import me.andyreckt.holiday.api.user.IRank;

import java.util.List;
import java.util.UUID;

public class BungeeUtil {

    public static IServer getServer(String server) {
        IServer iServer = HolidayAPI.getInstance().getServer(server);
        return (iServer != null) ? iServer : createFallbackServer(server);
    }

    public static int getPlayerCount(String server) {
        return getServer(server).getPlayerCount();
    }

    public static String getServerStatus(String server) {
        return getServer(server).getServerStatus().getFormat();
    }

    public static int getGlobalPlayerCount() {
        return HolidayAPI.getInstance().getOnlinePlayers().size();
    }

    private static IServer createFallbackServer(String name) {
        return new IServer() {
            @Override
            public String getServerName() {
                return name;
            }

            @Override
            public String getServerId() {
                return name;
            }

            @Override
            public double[] getTps() {
                return new double[0];
            }

            @Override
            public boolean isOnline() {
                return false;
            }

            @Override
            public boolean isJoinable() {
                return false;
            }

            @Override
            public String getAddress() {
                return "";
            }

            @Override
            public int getPort() {
                return 0;
            }

            @Override
            public int getPlayerCount() {
                return 0;
            }

            @Override
            public List<UUID> getOnlinePlayers() {
                return List.of();
            }

            @Override
            public int getMaxPlayers() {
                return 0;
            }

            @Override
            public void setOnlinePlayers(List<UUID> list) {

            }

            @Override
            public boolean isWhitelisted() {
                return false;
            }

            @Override
            public void setWhitelisted(boolean b) {

            }

            @Override
            public IRank getWhitelistRank() {
                return null;
            }

            @Override
            public void setWhitelistRank(IRank iRank) {

            }

            @Override
            public List<UUID> getWhitelistedPlayers() {
                return List.of();
            }

            @Override
            public void setWhitelistedPlayers(List<UUID> list) {

            }

            @Override
            public boolean isChatMuted() {
                return false;
            }

            @Override
            public void setChatMuted(boolean b) {

            }

            @Override
            public long getChatDelay() {
                return 0;
            }

            @Override
            public void setChatDelay(long l) {

            }

            @Override
            public long getLastKeepAlive() {
                return 0;
            }

            @Override
            public void keepAlive() {

            }

            @Override
            public void sendUpdate() {

            }

            @Override
            public long getStartupTime() {
                return 0;
            }

            @Override
            public long getUptime() {
                return 0;
            }

            @Override
            public int getMemoryUsage() {
                return 0;
            }

            @Override
            public int getMemoryMax() {
                return 0;
            }

            @Override
            public int getMemoryFree() {
                return 0;
            }

            @Override
            public ServerStatus getServerStatus() {
                return ServerStatus.OFFLINE;
            }
        };
    }
}
