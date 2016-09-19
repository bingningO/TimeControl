package com.example.zhubingning.timecontrol;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopActivity extends Activity {

    //bind
    @BindView(R.id.button_pause_focus)
    Button mButtonPause;
    @BindView(R.id.button_continue)
    Button mButtonContinue;
    @BindView(R.id.button_exit)
    Button mButtonExit;
    @BindView(R.id.button_start_focus)
    Button mButtonStartFocus;
    @BindView(R.id.layout_countdown)
    RelativeLayout mLayoutCountdown;
    @BindView(R.id.layout_continue_exit)
    LinearLayout mLayoutContinueExit;
    @BindView(R.id.layout_statistic)
    LinearLayout mLayoutStatistics;
    @BindView(R.id.countdown_text)
    TextView mTextCountdown;
    @BindView(R.id.text_completed)
    TextView mTextCompleted;
    @BindView(R.id.text_goal)
    TextView mTextGoal;

    private CustomCountDownTimer mTimerCustom;
    private ActionBar mActionBar;
    private int mCompletTime;
    private int mGoalTime;
    private boolean mIsFocus;
    private boolean mIsNatureOnfinish;
    private long mfocusTime = 60000;
    private long mbreakTime = 3000;

    ////////////////////////////////////////////////////////////
    // EVENT METHODS
    ///////////////////////////////////////////////////////////

    @OnClick(R.id.button_start_focus)
    public void onClickStartFocus() {
        long countTime = mIsFocus ? mfocusTime : mbreakTime;
        inCounting(countTime);
    }

    @OnClick(R.id.button_pause_focus)
    public void onClickPauseFocus() {
        showPause();
    }

    @OnClick(R.id.button_continue)
    public void onClickContinue() {
        showResume();
    }

    @OnClick(R.id.button_exit)
    public void onClickExit() {
        mIsNatureOnfinish=false;
        mTimerCustom.onFinish();
        mTimerCustom.cancel();
        mLayoutContinueExit.setVisibility(View.GONE);

        mIsFocus = true;
        showStart(mfocusTime);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        ButterKnife.bind(this);

        initialViews();
    }

    ////////////////////////////////////////////////////////////
    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////

    private void initialViews() {
        setmGoalTime(180);
        setmCompletTime(0);
        mTextCompleted.setText(getResources().getString(R.string.time_completed) + " " + mCompletTime);
        mTextGoal.setText(getResources().getString(R.string.time_goal) + " " + mGoalTime);

        mIsFocus = true;
        showStart(mfocusTime);
    }

    /**
     * show screens
     **/
    private void showStart(long timeMillis) {
        mActionBar = getActionBar();
        mIsNatureOnfinish=true;

        String title = mIsFocus ? "Focus" : "Take a Break";
        mActionBar.setTitle(title);
        mButtonStartFocus.setVisibility(View.VISIBLE);
        setShowTimeText(timeMillis);
    }

    private void inCounting(long countTime) {
        mButtonStartFocus.setVisibility(View.GONE);
        mButtonPause.setVisibility(View.VISIBLE);
        mTimerCustom = new CustomCountDownTimer(countTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setShowTimeText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                if (mIsNatureOnfinish && mIsFocus) {
                    mCompletTime += mTimerCustom.getCountTimes() / 60000;
                    mTextCompleted.setText(getResources().getString(R.string.time_completed) + mCompletTime);
                }
                if (mIsFocus) {
                    mIsFocus = false;
                    showStart(mbreakTime);
                } else {
                    mIsFocus = true;
                    showStart(mfocusTime);
                }
            }
        }.start();
    }

    private void showPause() {
        String exitText = mIsFocus ? "Exit" : "Skip";
        mButtonPause.setVisibility(View.GONE);
        mLayoutContinueExit.setVisibility(View.VISIBLE);
        mButtonExit.setText(exitText);
        mTimerCustom.pause();
    }

    private void showResume() {
        mLayoutContinueExit.setVisibility(View.GONE);
        mButtonPause.setVisibility(View.VISIBLE);
        mTimerCustom.resume();
    }

    /**
     * small functional methods
     **/
    private void setmCompletTime(int completTime) {
        mCompletTime = completTime;
    }

    private void setmGoalTime(int goalTime) {
        mGoalTime = goalTime;
    }

    private void setShowTimeText(long timeMill) {
        String minutes = (timeMill / 1000 / 60) + "";
        String seconds = (timeMill / 1000 % 60) + "";
        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        if (seconds.length() == 1) {
            seconds = "0" + seconds;
        }
        mTextCountdown.setText(minutes + ":" + seconds);
    }

}
