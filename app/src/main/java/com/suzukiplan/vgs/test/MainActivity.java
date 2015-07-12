package com.suzukiplan.vgs.test;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.suzukiplan.vgs.sound.VgsBgmPlayer;

import java.io.IOException;

public class MainActivity extends Activity {
    private VgsBgmPlayer mVgsBgmPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVgsBgmPlayer = ((VgsApplication)getApplication()).getBgmPlayer();
        try {
            mVgsBgmPlayer.load(0, "file:///android_asset/bgm/sample.bgm");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button button;

        button = (Button) findViewById(R.id.button_play);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVgsBgmPlayer.play(0);
            }
        });

        button = (Button) findViewById(R.id.button_stop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVgsBgmPlayer.stop();
            }
        });

        button = (Button) findViewById(R.id.button_resume);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVgsBgmPlayer.resume();
            }
        });

        button = (Button) findViewById(R.id.button_fade);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVgsBgmPlayer.fadeout();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mVgsBgmPlayer.stop();
        moveTaskToBack(true);
        super.onDestroy();
    }
}
