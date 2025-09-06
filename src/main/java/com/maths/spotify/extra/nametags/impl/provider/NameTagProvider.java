package com.maths.spotify.extra.nametags.impl.provider;

import com.maths.spotify.hHub;
import com.maths.spotify.utils.CC;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import com.maths.spotify.extra.nametags.impl.construct.NameTagInfo;

@Getter
@AllArgsConstructor
public abstract class NameTagProvider {

    private final hHub plugin = hHub.getInstance();

    private final String name;
    private final int weight;

    public abstract NameTagInfo fetchNameTag(Player toRefresh, Player refreshFor);

    public NameTagInfo createNameTag(String prefix, String suffix) {
        return (plugin.getNameTagHandler().getOrCreate(CC.translate(prefix), CC.translate(suffix)));
    }
}