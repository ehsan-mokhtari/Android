package com.ehsanmokhtari.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    SeekBar timerSeekBar;
    TextView timerTextView;
    Button controlTimerButton;
    CountDownTimer countDownTimer;
    /*  with pressing go button and changing the seekBar it mix everything
        and after finishing timer , the seekBar locked!
        so we need to do some thing with pressing go the seekBar locked then the
        go button turn to stop button to stop timer to change the seekBar   */
    boolean counterIsActive = false;

    public void updateTimer(int secondsLeft){
        Log.d(TAG, "updateTimer:starts ");
        int minutes =  secondsLeft / 60;
        int seconds = secondsLeft - minutes*60;
        //solving for showing single 0 instead of 00
        String secondString = Integer.toString(seconds);
        if (seconds<=9){
            secondString = "0"+secondString;   }
        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
        Log.d(TAG, "updateTimer: ends");}

    public void resetTimer(){
        Log.d(TAG, "resetTimer: starts");
        timerTextView.setText("13:00");
        timerSeekBar.setProgress(780);
        countDownTimer.cancel();
        controlTimerButton.setText("GO");
        controlTimerButton.setTextSize(30);
        timerSeekBar.setEnabled(true);
        counterIsActive = false;
        Log.d(TAG, "resetTimer: ends");}

    public void controlTimer(View view){
        Log.d(TAG, "controlTimer: starts");
        if (!counterIsActive) {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controlTimerButton.setText("STOP");
            controlTimerButton.setTextSize(20);
            //+100 causes running has no delay at finish
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                    /*after it get to 0:1 it stops so we need to fix it in onFinish method */  }
                @Override
                public void onFinish() {
                /*   (this , ) it refers to go click but we need to use this to mainActivity so instead
                     of this we use getApplicationContext()  */
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rango);
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(),"Your Eggs Are Ready !",Toast.LENGTH_LONG).show();
                    resetTimer(); }
            }.start();   }//end of if(counterIsActive == false)  !
        else{
            Log.d(TAG, "controlTimer: else starts");
            resetTimer();
            Log.d(TAG, "controlTimer: else ends");
        }
        Log.d(TAG, "controlTimer: ends");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: before set content");
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: after set content");
        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controlTimerButton = (Button) findViewById(R.id.controlTimerButton);
        timerSeekBar.setMax(780);
        timerSeekBar.setProgress(780);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);  }
            //in this app we do not need two methods below
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }     });
    }
}
