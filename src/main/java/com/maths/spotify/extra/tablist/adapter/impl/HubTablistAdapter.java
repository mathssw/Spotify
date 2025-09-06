package com.maths.spotify.extra.tablist.adapter.impl;

import com.maths.spotify.configs.TablistConfig;
import com.maths.spotify.utils.CC;
import com.maths.spotify.utils.QueueUtils;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.andyreckt.holiday.api.HolidayAPI;
import me.andyreckt.holiday.api.server.IServer;
import org.bukkit.entity.Player;
import com.maths.spotify.extra.skin.CachedSkin;
import com.maths.spotify.extra.tablist.Heads;
import com.maths.spotify.extra.tablist.adapter.TabAdapter;
import com.maths.spotify.extra.tablist.setup.TabEntry;
import com.maths.spotify.extra.tablist.util.Skin;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HubTablistAdapter implements TabAdapter {

    @Override
    public String getHeader(Player viewer) {
        return String.join("\n", CC.replace(viewer, TablistConfig.TABLIST_HEADER));
    }

    @Override
    public String getFooter(Player viewer) {
        return String.join("\n", CC.replace(viewer, TablistConfig.TABLIST_FOOTER));
    }

    @Override
    public List<TabEntry> getLines(Player viewer) {
        List<TabEntry> entries = new ObjectArrayList<>();

        addTabEntries(entries, viewer, TablistConfig.TABLIST_LEFT, 0);
        addTabEntries(entries, viewer, TablistConfig.TABLIST_MIDDLE, 1);
        addTabEntries(entries, viewer, TablistConfig.TABLIST_RIGHT, 2);
        addTabEntries(entries, viewer, TablistConfig.TABLIST_FAR_RIGHT, 3);

        return entries;
    }

    private void addTabEntries(List<TabEntry> entries, Player viewer, List<String> tablistConfig, int column) {
        for (int y = 0; y < tablistConfig.size(); y++) {
            String[] entryParts = tablistConfig.get(y).split(";", 2);

            if (entryParts.length != 2) {
                continue;
            }

            String head = entryParts[0];
            String text = entryParts[1];

            CachedSkin skin;
            if (head.equalsIgnoreCase("VIEWER_HEAD")) {
                skin = Skin.getPlayer(viewer);
            } else if (head.equalsIgnoreCase("ALPHABET_HEAD")) {
                skin = Skin.getAlphabetSkin(viewer);
            } else {
                skin = Heads.getHead(head);
            }

            if (text.toLowerCase().contains("%queue")) {

                if (!QueueUtils.isInQueue(viewer)) {
                    if (text.toLowerCase().contains("%queue-name%"))
                        entries.add(new TabEntry(column, y, CC.replace(viewer, TablistConfig.NOT_IN_QUEUE), skin));

                    entries.add(new TabEntry(column, y, "", skin));
                    continue;
                }
            }
            Matcher matcher = Pattern.compile("%svstatus_([a-zA-Z0-9_]+)%").matcher(text);
            StringBuffer buffer = new StringBuffer();

            while (matcher.find()) {
                String server = matcher.group(1);
                String replacement = "&cOffline";
                IServer iServer = HolidayAPI.getInstance().getServer(server);
                if(iServer != null) {
                    replacement = iServer.getServerStatus().getFormat();
                }
                matcher.appendReplacement(buffer, replacement);
            }

            matcher.appendTail(buffer);

            entries.add(new TabEntry(column, y, CC.replace(viewer, buffer.toString()), skin));
        }
    }
}