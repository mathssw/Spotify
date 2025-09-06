package com.maths.spotify.menu;

import com.google.common.collect.Maps;
import java.util.Map;

import com.maths.spotify.menu.buttons.*;
import com.maths.spotify.utils.menu.Button;
import com.maths.spotify.utils.menu.Menu;
import org.bukkit.entity.Player;

public class HubSelectorMenu extends Menu {
    private static final Button GLASS = new GlassButton(7);
    public boolean isAutoUpdate() {
        return true;
    }

    public String getTitle(Player player) {
        return "Server Selector";
    }

    public int size(Map<Integer, ? extends Button> buttons) {
        return 27;
    }

    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();
        int i;
        for(i = 0; i < 27; ++i) {
            buttons.put(i, GLASS);
        }
        buttons.put(11, new HCFButton());
        buttons.put(12, new KitMapButton());
        buttons.put(14, new PracticeButton());
        buttons.put(15, new BunkersButton());
        return buttons;
    }
}