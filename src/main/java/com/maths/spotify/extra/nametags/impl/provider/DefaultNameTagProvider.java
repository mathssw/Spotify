package com.maths.spotify.extra.nametags.impl.provider;

import org.bukkit.entity.Player;
import com.maths.spotify.extra.nametags.impl.construct.NameTagInfo;

public class DefaultNameTagProvider extends NameTagProvider {

    public DefaultNameTagProvider() {
        super("Default Provider", 0);
    }

    @Override
    public NameTagInfo fetchNameTag(Player toRefresh, Player refreshFor) {
        return (createNameTag(toRefresh.getDisplayName().replace(toRefresh.getName(),""), ""));
    }

}
