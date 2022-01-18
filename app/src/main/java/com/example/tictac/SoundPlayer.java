package com.example.tictac;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int winSound;
    private static int lossSound;

    public SoundPlayer(Context context)
    {
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        winSound = soundPool.load(context,R.raw.win,1);
        lossSound = soundPool.load(context,R.raw.loss,1);
    }

    public void playWinSound()
    {
       // System.out.println("@@@ Success");
        soundPool.play(winSound,1.0f,1.0f,1,0,1.0f);
    }

    public void playlossSound()
    {
        //System.out.println("@@@ Success");
        soundPool.play(lossSound,1.0f,1.0f,1,0,1.0f);
    }
}
