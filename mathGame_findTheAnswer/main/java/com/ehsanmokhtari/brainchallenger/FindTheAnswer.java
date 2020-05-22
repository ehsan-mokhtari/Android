package com.ehsanmokhtari.brainchallenger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class FindTheAnswer extends AppCompatActivity {
    int enteredAnswer;
    int currentAnswer;
    TextView fTACurrent;
    int secondAnswer;
    TextView fTASecond;
    TextView fTACurrentColor;
    TextView fTATimer;
    int round = 0;
    int score = 0;
    int balance =0;
    TextView fTAScore;
    TextView fTASituation;
    EditText fTAEntered;
    ImageButton ftAStartButton;
    Button fTAGoButton;
    String[] operations = {"-","+","/","*"};
    Random random;
    Quotes fTAQuotes = new Quotes();
    CountDownTimer fTACountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_the_answer);
        fTACurrent = (TextView) findViewById(R.id.fTACurrentTextView);
        fTASecond = (TextView) findViewById(R.id.fTASecondTextView);
        fTACurrentColor = (TextView)findViewById(R.id.fTACurrentColorTextView);
        fTATimer = (TextView) findViewById(R.id.fTATimerTextView);
        fTAScore = (TextView)findViewById(R.id.fTAScoreTextView);
        fTASituation = (TextView) findViewById(R.id.fTASituationTextView);
        fTAEntered = (EditText) findViewById(R.id.fTAEnteredrEditText);
        ftAStartButton = (ImageButton) findViewById(R.id.fTAStartImageButton);
        fTAGoButton = (Button) findViewById(R.id.fTAGoButton);
        random = new Random();
    }

    public void fTAStart(View view) {
        fTAScore.setText("0/0");
        fTACurrentColor.setText("");
        fTACurrent.setVisibility(View.VISIBLE);
        fTASecond.setVisibility(View.VISIBLE);
        fTACurrentColor.setVisibility(View.VISIBLE);
        fTAScore.setVisibility(View.VISIBLE);
        fTATimer.setVisibility(View.VISIBLE);
        fTAEntered.setVisibility(View.VISIBLE);
        fTASituation.setText("برو که رفتیم !");
        fTASituation.setVisibility(View.VISIBLE);
        fTAGoButton.setVisibility(View.VISIBLE);
        ftAStartButton.setVisibility(View.INVISIBLE);
        generateOne();
        generateTwo();
        fTACountDownTimer = new CountDownTimer(60400, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                fTATimer.setText(Integer.toString((int) millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
              if (round>balance+8 && round-score<=score){
                  fTACountDownTimer.start();
                  balance+=8;
                  fTASituation.setText("بردی ! ادامه میدیم ژنرال !");
              }else {
                  fTATimer.setText("0s");
                  round = 0;
                  score = 0;
                  balance = 0;
                  ftAStartButton.setVisibility(View.VISIBLE);
                  fTACurrent.setVisibility(View.INVISIBLE);
                  fTASecond.setVisibility(View.INVISIBLE);
                  fTACurrentColor.setVisibility(View.INVISIBLE);
                  fTAEntered.setVisibility(View.INVISIBLE);
                  fTAGoButton.setVisibility(View.INVISIBLE);
                  fTASituation.setText("باختی ! بیشتر تلاش کن !");
              }

            }


        }.start();
    }

    public void generateOne(){
        int a1 = random.nextInt(31);
        int b1 = random.nextInt(31);
        String operation1 = operations[random.nextInt(4)];
        switch (operation1){
            case "-":
                while(b1>a1){ b1 = random.nextInt(31);   }
                currentAnswer = a1-b1;
                fTACurrent.setText(Integer.toString(a1)+" - "+Integer.toString(b1)+" =");
                break;

            case "+":
                currentAnswer = a1+b1;
                fTACurrent.setText(Integer.toString(a1)+" + "+Integer.toString(b1)+" =");
                break;

            case  "/":
                while(a1==0 || b1==0 || !(a1%b1 == 0) ){
                    a1 = random.nextInt(31);
                    b1 = random.nextInt(31);}
                currentAnswer = a1/b1;
                fTACurrent.setText(Integer.toString(a1)+" / "+Integer.toString(b1)+" =");
                break;

            case "*" :
                a1 = random.nextInt(11);
                b1 = random.nextInt(11);
                currentAnswer = a1*b1;
                fTACurrent.setText(Integer.toString(a1)+" * "+Integer.toString(b1)+" =");
                break;

            default:
        }

    }

    public void generateTwo(){
        int a2 = random.nextInt(31);
        int b2 = random.nextInt(31);
        String operation2 = operations[random.nextInt(4)];
        switch (operation2){
            case "-":
                while(b2>a2){ b2= random.nextInt(31);   }
                secondAnswer =a2-b2;
                fTASecond.setText(Integer.toString(a2)+" - "+Integer.toString(b2));
                break;

            case "+":
                secondAnswer = a2+b2;
                fTASecond.setText(Integer.toString(a2)+" + "+Integer.toString(b2));
                break;

            case  "/":
                while(a2==0 || b2==0 || !(a2%b2 == 0) ){
                    a2 = random.nextInt(31);
                    b2 = random.nextInt(31);}
                secondAnswer = a2/b2;
                fTASecond.setText(Integer.toString(a2)+" / "+Integer.toString(b2));
                break;

            case "*" :
                a2 = random.nextInt(11);
                b2 = random.nextInt(11);
                secondAnswer = a2*b2;
                fTASecond.setText(Integer.toString(a2)+" * "+Integer.toString(b2));
                break;

            default:
        }}


    public  void fTAGo (View view){
        try {
            enteredAnswer = Integer.valueOf( fTAEntered.getText().toString());
            if (enteredAnswer == currentAnswer){
                score++;
                fTACurrentColor.setText(fTACurrent.getText()+""+Integer.toString(enteredAnswer));
                fTACurrentColor.setTextColor(Color.parseColor("#1B5E20"));
                fTASituation.setText(fTAQuotes.getCorrectAnswer());
            }else{
                fTACurrentColor.setText(fTACurrent.getText()+""+Integer.toString(enteredAnswer));
                fTACurrentColor.setTextColor(Color.parseColor("#B71C1C"));
                fTASituation.setText(fTAQuotes.getWrongAnswer());}
            round++;
            fTAScore.setText(Integer.toString(score)+"/"+Integer.toString(round));
            currentAnswer = secondAnswer;
            fTACurrent.setText(fTASecond.getText()+" =");
            generateTwo();
            fTAEntered.setText(null);
        }catch (Exception e){
            fTASituation.setText("یه عدد وارد کن !");
            
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent fTABackIntent = new Intent(FindTheAnswer.this , MainActivity.class);
        FindTheAnswer.this.startActivity(fTABackIntent);
        FindTheAnswer.this.finish();
    }
}
