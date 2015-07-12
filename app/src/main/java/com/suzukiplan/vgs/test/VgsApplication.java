package com.suzukiplan.vgs.test;

import android.app.Application;

import com.suzukiplan.vgs.sound.VgsBgmPlayer;

/**
 * application class
 * Created by suzukiplan on 2015/07/12.
 */
public class VgsApplication extends Application {
    private static VgsBgmPlayer mVgsBgmPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mVgsBgmPlayer = new VgsBgmPlayer(this);
    }

    public VgsBgmPlayer getBgmPlayer() {
        return mVgsBgmPlayer;
    }

    @Override
    public void onTerminate() {
        mVgsBgmPlayer.destroy();
        super.onTerminate();
    }
}
