package com.example.zhubingning.timecontrol;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by zhubingning on 16/09/14.
 */
public class InstanceCountdownTimer extends CountDownTimer {

    private long millisUntilFinished;

    private TextView showTimer;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public InstanceCountdownTimer(long millisInFuture, long countDownInterval, TextView showTimer) {
        super(millisInFuture, countDownInterval);
        this.millisUntilFinished=millisInFuture;
        this.showTimer=showTimer;
    }

    public void onPause(){
        cancel();
    }

    public void onResume(){
        if(millisUntilFinished>0){
            onTick(millisUntilFinished);
            start();
        }
    }

    @Override
    public void onTick(long millisUntilFinished) {
        this.millisUntilFinished=millisUntilFinished;
        long minutes=millisUntilFinished/60;
        long seconds=millisUntilFinished-minutes*60;
        showTimer.setText(minutes+":"+seconds);
    }

    @Override
    public void onFinish() {
        showTimer.setText("00:00");
    }
}


