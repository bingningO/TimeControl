package com.example.zhubingning.timecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopActivity extends AppCompatActivity {
    
    //bind
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.button_pause)
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

    ////////////////////////////////////////////////////////////
    // EVENT METHODS
    ///////////////////////////////////////////////////////////

    @OnClick(R.id.button_start_focus)
    public void onClickStartFocus(){
        showBeingForcus();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        ButterKnife.bind(this);
       // ButterKnife.setDebug(true);

        initialViews();
    }

    ////////////////////////////////////////////////////////////
    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////

    public void initialViews(){
        //mToolbar.setTitle("Focus");
        showStartForcus();
    }

    private void showStartForcus(){
        mButtonStartFocus.setVisibility(View.VISIBLE);
        mTextCountdown.setText("25:00");
    }

    private void showBeingForcus(){
        mButtonStartFocus.setVisibility(View.GONE);
        mLayoutContinueExit.setVisibility(View.VISIBLE);
        mTimer=new InstanceCountdownTimer(1500000,1000,mTextCountdown);

    }

    private void showPauseForcus(){

    }

    private void showFinishForcus(){

    }
}
