package com.maths.spotify.extra.nametags.impl;

import com.maths.spotify.hHub;
import lombok.Getter;
import com.maths.spotify.extra.nametags.impl.construct.NameTagUpdate;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class NametagThread extends Thread {

    private final Map<NameTagUpdate, Boolean> pendingUpdates = new ConcurrentHashMap<>();

    public NametagThread() {
        super("hHub - NameTags Thread");
        setDaemon(false);
    }

    public void run() {
        while (true) {
            Iterator<NameTagUpdate> pendingUpdatesIterator = pendingUpdates.keySet().iterator();

            while(pendingUpdatesIterator.hasNext()) {
                NameTagUpdate pendingUpdate = pendingUpdatesIterator.next();

                try {
                    hHub.getInstance().getNameTagHandler().applyUpdate(pendingUpdate);
                    pendingUpdatesIterator.remove();
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }

            try {
                Thread.sleep(2 * 50L);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }
    }

}