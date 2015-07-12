package com.suzukiplan.vgs.sound;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * VGS sound player
 * Created by suzukiplan on 2015/07/12.
 */
public class VgsBgmPlayer {
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private Context mContext;
    private VgsSoundContext mVgsSoundContext;

    /**
     * initialize vgs bgm player
     *
     * @param context your activity
     */
    public VgsBgmPlayer(Context context) {
        mContext = context;
        mVgsSoundContext = VgsSoundContext.getInstance();
    }

    private void checkSlotNumber(final int slot) {
        if (slot < 0 || 255 < slot) {
            throw new IllegalArgumentException("invalid slot number: " + slot);
        }
    }

    /**
     * /**
     * load bgm file
     *
     * @param slot bgm slot number
     * @param path bgm data path (file or url)
     * @throws IOException could not load the file
     */
    public void load(final int slot, final String path) throws IOException {
        checkSlotNumber(slot);

        byte[] buffer = new byte[8192];
        InputStream inputStream;
        int size;

        // open
        if (path.startsWith(ASSET_PREFIX)) {
            // load bgm file from asset
            String assetPath = path.substring(ASSET_PREFIX.length());
            inputStream = mContext.getAssets().open(assetPath);
        } else {
            // load bgm file from local storage
            inputStream = new FileInputStream(new File(path));
        }

        // read
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        do {
            size = inputStream.read(buffer);
            if (0 < size) stream.write(buffer, 0, size);
        } while (0 < size);

        // load
        mVgsSoundContext.loadBgm((int) slot, stream.toByteArray());
    }

    /**
     * play bgm
     *
     * @param slot bgm slot number
     */
    public void play(final int slot) {
        checkSlotNumber(slot);
        mVgsSoundContext.playBgm((int) slot);
    }

    /**
     * stop bgm
     */
    public void stop() {
        mVgsSoundContext.stopBgm();
    }

    /**
     * resume bgm
     */
    public void resume() {
        mVgsSoundContext.resumeBgm();
    }

    /**
     * fadeout bgm
     */
    public void fadeout() {
        mVgsSoundContext.fadeoutBgm();
    }

    /**
     * destroy system resources
     */
    public void destroy() {
        mVgsSoundContext.destroy();
    }
}
