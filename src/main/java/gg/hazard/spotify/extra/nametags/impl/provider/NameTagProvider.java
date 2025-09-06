package gg.hazard.spotify.extra.nametags.impl.provider;

import gg.hazard.spotify.hHub;
import gg.hazard.spotify.utils.CC;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import gg.hazard.spotify.extra.nametags.impl.construct.NameTagInfo;

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