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

    private InstanceCountdownTimer mTimer;
    private ActionBar mActionBar;

    ////////////////////////////////////////////////////////////
    // EVENT METHODS
    ///////////////////////////////////////////////////////////

    @OnClick(R.id.button_start_focus)
    public void onClickStartFocus(){
        showBeingForcus();
    }

    @OnClick(R.id.button_pause_focus)
    public void onClickPauseFocus(){
        showPauseForcus();
    }

    @OnClick(R.id.button_continue)
    public void onClickContinue(){
        showResumeForcus();
    }

    @OnClick(R.id.button_exit)
    public void onClickExit(){
        showFinishForcus();
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

    public void initialViews(){

        showStartForcus();
    }

    private void showStartForcus(){
        mActionBar=getActionBar();
        mActionBar.setTitle("Focus");
        mButtonStartFocus.setVisibility(View.VISIBLE);
        mTextCountdown.setText("25:00");
    }

    private void showBeingForcus(){
        mButtonStartFocus.setVisibility(View.GONE);
        mButtonPause.setVisibility(View.VISIBLE);
        mTimer=new InstanceCountdownTimer(1500000,1000,mTextCountdown);
        mTimer.start();
    }

    private void showPauseForcus(){
        mTimer.onPause();
        mButtonPause.setVisibility(View.GONE);
        mLayoutContinueExit.setVisibility(View.VISIBLE);
    }

    private void showResumeForcus(){
        mLayoutContinueExit.setVisibility(View.GONE);
        mButtonPause.setVisibility(View.VISIBLE);
        mTimer.onResume();
    }

    private void showFinishForcus(){
        mTimer.onFinish();
        mLayoutContinueExit.setVisibility(View.GONE);
        showStartForcus();
    }
}
