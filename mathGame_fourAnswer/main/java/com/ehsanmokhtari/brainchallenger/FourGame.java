package com.ehsanmokhtari.brainchallenger;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class FourGame extends AppCompatActivity {
    private static final String TAG = "FourGame";
    //for timer balance
    int balance = 0;

    TextView fGOperation;
    TextView fGTimer;
    TextView fGScore;
    int score=0;
    int round=0;
    ImageButton fGStart;
    Button fGChoose0;
    Button fGChoose1;
    Button fGChoose2;
    Button fGChoose3;
    CountDownTimer fGCountDownTimer;
    int rightAnswer;
    int locationOfRightAnswer;
    TextView fGSituation;
    Random random = new Random();
    Quotes fourGameQuotes = new Quotes();
    ArrayList<Integer> answers = new ArrayList<>();
    GridLayout fGStartGridLayout ;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: four game starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_game);
        fGOperation = (TextView) findViewById(R.id.fGoperationTextView);
        fGScore = (TextView) findViewById(R.id.fGScoreTextView);
        fGTimer = (TextView) findViewById(R.id.fGTimerTextView);
        fGStart = (ImageButton) findViewById(R.id.fGStartImageButton) ;
        fGChoose0 = (Button)findViewById(R.id.fGButton0);
        fGChoose1 = (Button)findViewById(R.id.fGButton1);
        fGChoose2 = (Button)findViewById(R.id.fGButton2);
        fGChoose3 = (Button)findViewById(R.id.fGButton3);
        fGSituation = (TextView) findViewById(R.id.fGSituationTextView);
        fGStartGridLayout = (GridLayout) findViewById(R.id.fGStartGridLayout);
        Log.d(TAG, "onCreate: four game ends");
    }

    public  void generate(){
        Log.d(TAG, "generate: starts");
        String[] operation = {"-","+","/","*"};
        int  a = random.nextInt(31);
        int  b = random.nextInt(31);
        String currentOperation = operation[random.nextInt(4)];
        switch (currentOperation){
            case "-" :
                while(b>a){
                    b = random.nextInt(31);   }
                fGOperation.setText(Integer.toString(a) + " - " + Integer.toString(b));
                rightAnswer = a-b;
                break;

            case "+" :
                fGOperation.setText(Integer.toString(a) + " + " + Integer.toString(b));
                rightAnswer = a+b;
                break;

            case "/" :
                while (a== 0 || b==0 || !(a%b ==0)){
                    a=random.nextInt(31);
                    b=random.nextInt(31);
                }
                fGOperation.setText(Integer.toString(a) + " / " + Integer.toString(b));
                rightAnswer = a/b;
                break;

            case "*" :
                a=random.nextInt(11);
                b=random.nextInt(11);
                fGOperation.setText(Integer.toString(a) + " * " + Integer.toString(b));
                rightAnswer = a*b;
                break;

            default:     }

        locationOfRightAnswer = random.nextInt(3);
        int incorrectAnswer;
        for (int i =0 ; i<4 ; i++){
            if (i == locationOfRightAnswer){
                answers.add(rightAnswer);}
            else{
                incorrectAnswer = random.nextInt(61);
                while(incorrectAnswer == rightAnswer){
                    incorrectAnswer = random.nextInt(61);}
                answers.add(incorrectAnswer);
            }
        }

        fGChoose0.setText(Integer.toString(answers.get(0)));
        fGChoose1.setText(Integer.toString(answers.get(1)));
        fGChoose2.setText(Integer.toString(answers.get(2)));
        fGChoose3.setText(Integer.toString(answers.get(3)));



        Log.d(TAG, "generate: ends");

        answers.clear();
    }

    public void generateMed(){
        Log.d(TAG, "generate: starts");
        int[] wrongBalance = {-4,-7,-9,-11,3,6,9,11,13,15};
        String[] operation = {"-","+","/","*"};
        int  a = random.nextInt(61)+10;
        int  b = random.nextInt(61)+10;
        String currentOperation = operation[random.nextInt(4)];
        switch (currentOperation){
            case "-" :
                while(b>a){
                    b = random.nextInt(61)+10;   }
                fGOperation.setText(Integer.toString(a) + " - " + Integer.toString(b));
                rightAnswer = a-b;
                break;

            case "+" :
                fGOperation.setText(Integer.toString(a) + " + " + Integer.toString(b));
                rightAnswer = a+b;
                break;

            case "/" :
                while (a== 0 || b==0 || !(a%b ==0)){
                    a=random.nextInt(61)+10;
                    b=random.nextInt(61)+10;
                }
                fGOperation.setText(Integer.toString(a) + " / " + Integer.toString(b));
                rightAnswer = a/b;
                break;

            case "*" :
                a=random.nextInt(21);
                b=random.nextInt(21);
                fGOperation.setText(Integer.toString(a) + " * " + Integer.toString(b));
                rightAnswer = a*b;
                break;

            default:     }

        locationOfRightAnswer = random.nextInt(3);
        int incorrectAnswer;
        for (int i =0 ; i<4 ; i++){
            if (i == locationOfRightAnswer){
                answers.add(rightAnswer);}
            else{

                incorrectAnswer = rightAnswer + wrongBalance[random.nextInt(wrongBalance.length)];
                while(incorrectAnswer == rightAnswer || answers.contains(incorrectAnswer) || incorrectAnswer<0){
                    incorrectAnswer = rightAnswer + wrongBalance[random.nextInt(wrongBalance.length)];;}
                answers.add(incorrectAnswer);
            }
        }

        fGChoose0.setText(Integer.toString(answers.get(0)));
        fGChoose1.setText(Integer.toString(answers.get(1)));
        fGChoose2.setText(Integer.toString(answers.get(2)));
        fGChoose3.setText(Integer.toString(answers.get(3)));



        Log.d(TAG, "generate: ends");

        answers.clear();

    }

    public void generateHard(){
        Log.d(TAG, "generate: starts");
        int[] wrongBalanceHard = {-1,-2,-3,-4,-5,-6,1,2,3,4,5,6};
        String[] operation = {"-","+","/","*"};
        int  a = random.nextInt(181)-90;
        int  b = random.nextInt(181)-90;
        String currentOperation = operation[random.nextInt(4)];
        switch (currentOperation){
            case "-" :if (b<0){
                fGOperation.setText(Integer.toString(a)+" - (" + Integer.toString(b)+")");
            }else if(b>=0) {
                fGOperation.setText(Integer.toString(a) + " - " + Integer.toString(b));}
                rightAnswer = a-b;
                break;

            case "+" :
                if(b<0){
                    fGOperation.setText(Integer.toString(a)+" + ( " + Integer.toString(b)+" )");
                }else if (b>=0){
                fGOperation.setText(Integer.toString(a) + " + " + Integer.toString(b));}
                rightAnswer = a+b;
                break;

            case "/" :
                while (a== 0 || b==0 || !(a%b ==0)){
                    a=random.nextInt(181)-90;
                    b=random.nextInt(181)-90;
                }
                if (b<0){
                    fGOperation.setText(Integer.toString(a) + " / ( " + Integer.toString(b)+" )");
                }else if (b>=0){
                fGOperation.setText(Integer.toString(a) + " / " + Integer.toString(b));}
                rightAnswer = a/b;
                break;

            case "*" :
                a=random.nextInt(51)-26;
                b=random.nextInt(51)-26;
                if (b<0){
                    fGOperation.setText(Integer.toString(a) + " * ( " + Integer.toString(b)+" )");
                }else if (b>=0){
                fGOperation.setText(Integer.toString(a) + " * " + Integer.toString(b));}
                rightAnswer = a*b;
                break;

            default:     }

        locationOfRightAnswer = random.nextInt(3);
        int incorrectAnswer;
        for (int i =0 ; i<4 ; i++){
            if (i == locationOfRightAnswer){
                answers.add(rightAnswer);}
            else{
                incorrectAnswer = rightAnswer + wrongBalanceHard[random.nextInt(wrongBalanceHard.length)];
                while(incorrectAnswer == rightAnswer || answers.contains(incorrectAnswer)){
                    incorrectAnswer = rightAnswer + wrongBalanceHard[random.nextInt(wrongBalanceHard.length)];;}
                answers.add(incorrectAnswer);
            }
        }

        fGChoose0.setText(Integer.toString(answers.get(0)));
        fGChoose1.setText(Integer.toString(answers.get(1)));
        fGChoose2.setText(Integer.toString(answers.get(2)));
        fGChoose3.setText(Integer.toString(answers.get(3)));



        Log.d(TAG, "generate: ends");

        answers.clear();

    }

    public void fGStart(View view){
        type = 0;
        fGScore.setText("0/0");
        Log.d(TAG, "fGStart: button generate starts");
       fGCountDownTimer  = new CountDownTimer(60400,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                fGTimer.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                fGTimer.setText("0s");
                if (round>=balance + 10 && round-score<=score){
                    balance=balance+10;
                    fGSituation.setText("بردی ! ادامه میدیم ژنرال !");
                    generate();
                    fGCountDownTimer.start();


                }else {
                    round=0;
                    score=0;
                    balance = 0;
                    fGSituation.setText("باختی ! بیشتر تلاش کن !");
                    fGOperation.setVisibility(View.INVISIBLE);
                    fGChoose0.setVisibility(View.INVISIBLE);
                    fGChoose1.setVisibility(View.INVISIBLE);
                    fGChoose2.setVisibility(View.INVISIBLE);
                    fGChoose3.setVisibility(View.INVISIBLE);
                    fGStartGridLayout.setVisibility(View.VISIBLE);

                }


            }
        }.start();
        generate();
        fGOperation.setVisibility(View.VISIBLE);
        fGScore.setVisibility(View.VISIBLE);
        fGTimer.setVisibility(View.VISIBLE);
        fGChoose0.setVisibility(View.VISIBLE);
        fGChoose1.setVisibility(View.VISIBLE);
        fGChoose2.setVisibility(View.VISIBLE);
        fGChoose3.setVisibility(View.VISIBLE);
        fGSituation.setVisibility(View.VISIBLE);
        fGStartGridLayout.setVisibility(View.INVISIBLE);
        Log.d(TAG, "fGStart: button generate ends");
    }


    public void fGMedStart(View view){
        type =1;
        fGScore.setText("0/0");
        Log.d(TAG, "fGStart: button generate starts");
        fGCountDownTimer  = new CountDownTimer(60400,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                fGTimer.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                fGTimer.setText("0s");
                if (round>=balance + 10 && round-score<=score){
                    balance=balance+10;
                    fGSituation.setText("بردی ! ادامه میدیم ژنرال !");
                    generateMed();
                    fGCountDownTimer.start();


                }else {
                    round=0;
                    score=0;
                    balance = 0;
                    fGSituation.setText("باختی ! بیشتر تلاش کن !");
                    fGOperation.setVisibility(View.INVISIBLE);
                    fGChoose0.setVisibility(View.INVISIBLE);
                    fGChoose1.setVisibility(View.INVISIBLE);
                    fGChoose2.setVisibility(View.INVISIBLE);
                    fGChoose3.setVisibility(View.INVISIBLE);
                    fGStartGridLayout.setVisibility(View.VISIBLE);

                }


            }
        }.start();
        generateMed();
        fGOperation.setVisibility(View.VISIBLE);
        fGScore.setVisibility(View.VISIBLE);
        fGTimer.setVisibility(View.VISIBLE);
        fGChoose0.setVisibility(View.VISIBLE);
        fGChoose1.setVisibility(View.VISIBLE);
        fGChoose2.setVisibility(View.VISIBLE);
        fGChoose3.setVisibility(View.VISIBLE);
        fGSituation.setVisibility(View.VISIBLE);
        fGStartGridLayout.setVisibility(View.INVISIBLE);
        Log.d(TAG, "fGStart: button generate ends");
    }

    public void fGHardStart(View view){
        type =2;
        fGScore.setText("0/0");
        Log.d(TAG, "fGStart: button generate starts");
        fGCountDownTimer  = new CountDownTimer(60400,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                fGTimer.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                fGTimer.setText("0s");
                if (round>=balance + 10 && round-score<=score){
                    balance=balance+10;
                    fGSituation.setText("بردی ! ادامه میدیم ژنرال !");
                    generateHard();
                    fGCountDownTimer.start();


                }else {
                    round=0;
                    score=0;
                    balance = 0;
                    fGSituation.setText("باختی ! بیشتر تلاش کن !");
                    fGOperation.setVisibility(View.INVISIBLE);
                    fGChoose0.setVisibility(View.INVISIBLE);
                    fGChoose1.setVisibility(View.INVISIBLE);
                    fGChoose2.setVisibility(View.INVISIBLE);
                    fGChoose3.setVisibility(View.INVISIBLE);
                    fGStartGridLayout.setVisibility(View.VISIBLE);

                }


            }
        }.start();
        generateHard();
        fGOperation.setVisibility(View.VISIBLE);
        fGScore.setVisibility(View.VISIBLE);
        fGTimer.setVisibility(View.VISIBLE);
        fGChoose0.setVisibility(View.VISIBLE);
        fGChoose1.setVisibility(View.VISIBLE);
        fGChoose2.setVisibility(View.VISIBLE);
        fGChoose3.setVisibility(View.VISIBLE);
        fGSituation.setVisibility(View.VISIBLE);
        fGStartGridLayout.setVisibility(View.INVISIBLE);
        Log.d(TAG, "fGStart: button generate ends");

    }

    public void fGChoose (View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfRightAnswer))){
          fGSituation.setText(fourGameQuotes.getCorrectAnswer());
            score++;round++;
            fGScore.setText(Integer.toString(score)+"/"+Integer.toString(round));
        }else {
            fGSituation.setText(fourGameQuotes.getWrongAnswer());
            round++;
            fGScore.setText(Integer.toString(score)+"/"+Integer.toString(round));
        }

        if(type == 0){
            generate();
        }else if(type == 1){
            generateMed();
        }else if(type ==2){
            generateHard();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent fGBackIntent = new Intent(FourGame.this , MainActivity.class);
        FourGame.this.startActivity(fGBackIntent);
        FourGame.this.finish();
    }
}


