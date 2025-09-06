package gg.hazard.spotify.extra.nametags.impl.construct;

import com.google.common.primitives.Ints;
import gg.hazard.spotify.extra.nametags.impl.provider.NameTagProvider;

import java.util.Comparator;

public class NameTagComparator implements Comparator<NameTagProvider> {

    public int compare(NameTagProvider a,NameTagProvider b) {
        return Ints.compare(b.getWeight(), a.getWeight());
    }

}
