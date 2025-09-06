package gg.hazard.spotify.extra.nametags;

import gg.hazard.spotify.extra.nametags.impl.construct.NameTagInfo;
import gg.hazard.spotify.extra.nametags.impl.provider.NameTagProvider;
import gg.hazard.spotify.utils.CC;
import org.bukkit.entity.Player;

public final class HubNametagProvider extends NameTagProvider {
    public HubNametagProvider() {
        super("Hub Nametag Provider", 5);
    }

    @Override
    public NameTagInfo fetchNameTag(Player player, Player player1) {
        return createNameTag(CC.replace(player, "%color_name%"), "");
    }
}