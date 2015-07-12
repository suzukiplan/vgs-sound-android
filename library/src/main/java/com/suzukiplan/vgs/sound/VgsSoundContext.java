package com.suzukiplan.vgs.sound;

/**
 * sound context
 * Created by suzukiplan on 2015/07/12.
 */
class VgsSoundContext {
    static {
        System.loadLibrary("vgs");
    }

    private static native boolean vgs_init();

    private static native boolean vgs_loadBgm(final int slot, final byte[] bgm);

    private static native boolean vgs_playBgm(final int slot);

    private static native void vgs_stopBgm();

    private static native void vgs_resumeBgm();

    private static native void vgs_fadeoutBgm();

    private static native void vgs_term();

    private static boolean mCallGetInstance = false;
    private static VgsSoundContext mContext = null;
    private static boolean mInitialized = false;
    private boolean mPlayingBgm = false;

    public VgsSoundContext() {
        if (!mCallGetInstance || null != mContext) {
            throw new RuntimeException("use getInstance");
        }
    }

    public static synchronized VgsSoundContext getInstance() {
        mCallGetInstance = true;
        if (null == mContext) {
            mContext = new VgsSoundContext();
            mContext.initialize();
        }
        return mContext;
    }

    private void initialize() {
        VgsSoundContext.vgs_init();
        mInitialized = true;
    }

    public synchronized boolean loadBgm(final int slot, final byte[] bgm) {
        return mInitialized && vgs_loadBgm(slot, bgm);
    }

    public synchronized void playBgm(final int slot) {
        if (!mInitialized) return;
        mPlayingBgm = vgs_playBgm(slot);
    }

    public synchronized void stopBgm() {
        if (!mInitialized || !mPlayingBgm) return;
        vgs_stopBgm();
        mPlayingBgm = false;
    }

    public synchronized void resumeBgm() {
        if (!mInitialized || mPlayingBgm) return;
        vgs_resumeBgm();
        mPlayingBgm = true;
    }

    public synchronized void fadeoutBgm() {
        if (!mInitialized || !mPlayingBgm) return;
        vgs_fadeoutBgm();
        mPlayingBgm = false;
    }

    public synchronized void destroy() {
        if (mInitialized) {
            VgsSoundContext.vgs_term();
            mInitialized = false;
        }
    }
}
