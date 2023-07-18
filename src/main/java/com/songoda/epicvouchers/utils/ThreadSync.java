package com.songoda.epicvouchers.utils;

import java.util.concurrent.atomic.AtomicReference;

// TODO: Copied from EpicAnchors - Move to SongodaCore
public class ThreadSync {
    private final Object syncObj = new Object();
    private final AtomicReference<Boolean> waiting = new AtomicReference<>(true);

    public void waitForRelease() {
        synchronized (this.syncObj) {
            while (this.waiting.get()) {
                try {
                    this.syncObj.wait();
                } catch (Exception ignore) {
                }
            }
        }
    }

    public void release() {
        synchronized (this.syncObj) {
            this.waiting.set(false);
            this.syncObj.notifyAll();
        }
    }

    public void reset() {
        this.waiting.set(true);
    }
}
