package com.ehsanmokhtari.brainchallenger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class YesOrNo extends AppCompatActivity {
    Random random = new Random();
    Button   yONYes;
    Button   yONNo;
    TextView yONSituation;
    TextView yONCurrent;
    TextView yONSecond;
    TextView yONThird;
    TextView yONScore;
    TextView yONTimer;
    int balance = 0;
    int score = 0;
    int round = 0;
    ImageView yONStart;
    String[] operations = {"-","+","/","*"};
    int yesOrNo;
    Quotes yesOrNoQuotes = new Quotes();
    CountDownTimer yONCountDownTimer;
    GridLayout yONLevelGridLayout;
     static int type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_or_no);
        yONYes = (Button) findViewById(R.id.yONChooseYes);
        yONNo = (Button) findViewById(R.id.yONChooseNo);
        yONSituation = (TextView) findViewById(R.id.yONSituationTextView);
        yONCurrent = (TextView) findViewById(R.id.yONCurrentTextView);
        yONSecond = (TextView) findViewById(R.id.yONSecondTextView);
        yONThird = (TextView) findViewById(R.id.yONThirdTextView);
        yONScore = (TextView) findViewById(R.id.yONScoreTextView) ;
        yONTimer = (TextView) findViewById(R.id.yONTimerTextView);
        yONStart = (ImageView) findViewById(R.id.yONStartImageView);
        yONLevelGridLayout  = (GridLayout) findViewById(R.id.yONLevelGridLayout);

    }


    public void yONStart(View view){
        type =0;
        yONLevelGridLayout.setVisibility(View.INVISIBLE);
        yONYes.setVisibility(View.VISIBLE);
        yONNo.setVisibility(View.VISIBLE);
        yONCurrent.setVisibility(View.VISIBLE);
        yONTimer.setVisibility(View.VISIBLE);
        yONScore.setVisibility(View.VISIBLE);
        yONSituation.setText("برو که رفتیم !");
        yONSituation.setVisibility(View.VISIBLE);
        yONSecond.setVisibility(View.VISIBLE);
        yONThird.setVisibility(View.VISIBLE);
        generate();

        yONCountDownTimer = new CountDownTimer(60400,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                yONTimer.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                if (round>=balance+10 && round-score<=score){
                    yONSituation.setText("بردی ! ادامه میدیم ژنرال !");
                    yONCountDownTimer.start();
                    generate();
                    balance +=10;
                }else {
                    yONSituation.setText("باختی ! بیشتر تلاش کن !");
                    score = 0;
                    round = 0;
                    balance = 0;
                    yONTimer.setText("0s");
                    yONLevelGridLayout.setVisibility(View.VISIBLE);
                    yONNo.setVisibility(View.INVISIBLE);
                    yONYes.setVisibility(View.INVISIBLE);
                    yONCurrent.setVisibility(View.INVISIBLE);
                    yONThird.setVisibility(View.INVISIBLE);
                    yONSecond.setVisibility(View.INVISIBLE);
                }}
        }.start();
    }

    public void yONStartMed(View view){
        type=1;
        yONLevelGridLayout.setVisibility(View.INVISIBLE);
        yONYes.setVisibility(View.VISIBLE);
        yONNo.setVisibility(View.VISIBLE);
        yONCurrent.setVisibility(View.VISIBLE);
        yONTimer.setVisibility(View.VISIBLE);
        yONScore.setVisibility(View.VISIBLE);
        yONSituation.setText("برو که رفتیم !");
        yONSituation.setVisibility(View.VISIBLE);
        yONSecond.setVisibility(View.VISIBLE);
        yONThird.setVisibility(View.VISIBLE);
        generateMed();

        yONCountDownTimer = new CountDownTimer(60400,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                yONTimer.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                if (round>=balance+10 && round-score<=score){
                    yONSituation.setText("بردی ! ادامه میدیم ژنرال !");
                    yONCountDownTimer.start();
                    generateMed();
                    balance +=10;
                }else {
                    yONSituation.setText("باختی ! بیشتر تلاش کن !");
                    score = 0;
                    round = 0;
                    balance = 0;
                    yONTimer.setText("0s");
                    yONLevelGridLayout.setVisibility(View.VISIBLE);
                    yONNo.setVisibility(View.INVISIBLE);
                    yONYes.setVisibility(View.INVISIBLE);
                    yONCurrent.setVisibility(View.INVISIBLE);
                    yONThird.setVisibility(View.INVISIBLE);
                    yONSecond.setVisibility(View.INVISIBLE);
                }}
        }.start();
    }


    public void generate(){
        // 0no 1yes
        yesOrNo = random.nextInt(2);
        int a = random.nextInt(31);
        int b = random.nextInt(31);
        if (yesOrNo == 1){
            String currentOperation = operations[random.nextInt(4)];
            switch (currentOperation){
                case "-":
                    while(b>a){ b = random.nextInt(31);   }
                    yONCurrent.setText(Integer.toString(a)+" - "+Integer.toString(b)+" = "+Integer.toString(a-b));
                    break;
                case "+":
                    yONCurrent.setText(Integer.toString(a)+" + "+Integer.toString(b)+" = "+Integer.toString(a+b));
                    break;
                case  "/":
                    while(a==0 || b==0 || !(a%b == 0) ){
                        a = random.nextInt(31);
                        b = random.nextInt(31);}
                    yONCurrent.setText(Integer.toString(a)+" / "+Integer.toString(b)+" = "+Integer.toString(a/b));
                    break;
                case "*" :
                      a = random.nextInt(11);
                      b = random.nextInt(11);
                    yONCurrent.setText(Integer.toString(a)+" * "+Integer.toString(b)+" = "+Integer.toString(a*b));
                    break;
                default:
            }
        }else{
            int wrongAnswer;
            String currentOperation = operations[random.nextInt(4)];
            switch (currentOperation){
                case "-":
                    wrongAnswer = random.nextInt(31);
                    while(b>a ){ b = random.nextInt(31);   }
                    while (wrongAnswer == a-b){
                        wrongAnswer = random.nextInt(31);}
                    yONCurrent.setText(Integer.toString(a)+" - "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));
                    break;
                case "+":
                    wrongAnswer = random.nextInt(61);
                    yONCurrent.setText(Integer.toString(a)+" + "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));
                    break;
                case  "/":
                    wrongAnswer = random.nextInt(31);
                    while(a==0 || b==0 || !(a%b == 0) ){
                        a = random.nextInt(31);
                        b = random.nextInt(31);}
                    yONCurrent.setText(Integer.toString(a)+" / "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));
                    break;
                case "*" :
                    wrongAnswer = random.nextInt(101);
                    a = random.nextInt(11);
                    b = random.nextInt(11);
                    yONCurrent.setText(Integer.toString(a)+" * "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));
                    break;
                default:
        }
    }}

    public void generateMed(){
        // 0no 1yes
        yesOrNo = random.nextInt(2);
        int a = random.nextInt(61);
        int b = random.nextInt(61);
        int[] balanceMed = {-5,-4,2,3,5,7,8,9};
        if (yesOrNo == 1){
            String currentOperation = operations[random.nextInt(4)];
            switch (currentOperation){
                case "-":
                    while(b>a){ b = random.nextInt(61);   }
                    yONCurrent.setText(Integer.toString(a)+" - "+Integer.toString(b)+" = "+Integer.toString(a-b));
                    break;
                case "+":
                    yONCurrent.setText(Integer.toString(a)+" + "+Integer.toString(b)+" = "+Integer.toString(a+b));
                    break;
                case  "/":
                    while(a==0 || b==0 || !(a%b == 0) ){
                        a = random.nextInt(61);
                        b = random.nextInt(61);}
                    yONCurrent.setText(Integer.toString(a)+" / "+Integer.toString(b)+" = "+Integer.toString(a/b));
                    break;
                case "*" :
                    a = random.nextInt(21);
                    b = random.nextInt(21);
                    yONCurrent.setText(Integer.toString(a)+" * "+Integer.toString(b)+" = "+Integer.toString(a*b));
                    break;
                default:
            }
        }else{
            int wrongAnswer;
            String currentOperation = operations[random.nextInt(4)];
            switch (currentOperation){
                case "-":
                    wrongAnswer = a-b + balanceMed[random.nextInt(balanceMed.length)];
                    while (wrongAnswer<0){
                        wrongAnswer = a-b + balanceMed[random.nextInt(balanceMed.length)];
                    }
                    yONCurrent.setText(Integer.toString(a)+" - "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));
                    break;
                case "+":
                    wrongAnswer = a+b + balanceMed[random.nextInt(balanceMed.length)];
                    while (wrongAnswer<0){
                        wrongAnswer = a+b + balanceMed[random.nextInt(balanceMed.length)];
                    }
                    yONCurrent.setText(Integer.toString(a)+" + "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));
                    break;
                case  "/":
                    a = random.nextInt(31);
                    b = random.nextInt(31);
                    while(a==0 || b==0 || !(a%b == 0) ){
                        a = random.nextInt(31);
                        b = random.nextInt(31);}
                    wrongAnswer = a/b + balanceMed[random.nextInt(balanceMed.length)];
                    while (wrongAnswer<0){
                        wrongAnswer = a/b + balanceMed[random.nextInt(balanceMed.length)];
                    }
                    yONCurrent.setText(Integer.toString(a)+" / "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));
                    break;
                case "*" :
                    a = random.nextInt(21);
                    b = random.nextInt(21);
                    wrongAnswer = a*b + balanceMed[random.nextInt(balanceMed.length)];
                    while (wrongAnswer<0){
                        wrongAnswer = a*b + balanceMed[random.nextInt(balanceMed.length)];
                    }
                    yONCurrent.setText(Integer.toString(a)+" * "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));
                    break;
                default:
            }
        }
    }

    public void generateHard(){
        // 0no 1yes
        yesOrNo = random.nextInt(2);
        int a = random.nextInt(181)-90;
        int b = random.nextInt(181)-90;
        int[] balanceHard = {1,2,3,4,5,6,-1,-2,-3,-4,-5,-6};
        if (yesOrNo == 1){
            String currentOperation = operations[random.nextInt(4)];
            switch (currentOperation){
                case "-":
                    if (b<0){
                        yONCurrent.setText(Integer.toString(a)+" - ( "+Integer.toString(b)+" ) = "+Integer.toString(a-b));
                    } else if (b>=0){
                    yONCurrent.setText(Integer.toString(a)+" - "+Integer.toString(b)+" = "+Integer.toString(a-b));}
                    break;
                case "+":
                    if (b<0){
                        yONCurrent.setText(Integer.toString(a)+" + ( "+Integer.toString(b)+" ) = "+Integer.toString(a+b));
                    }else if (b>=0){
                    yONCurrent.setText(Integer.toString(a)+" + "+Integer.toString(b)+" = "+Integer.toString(a+b));}
                    break;
                case  "/":
                    a = random.nextInt(121)-60;
                    b = random.nextInt(121)-60;
                    while(a==0 || b==0 || !(a%b == 0) ){
                        a = random.nextInt(121)-60;
                        b = random.nextInt(121)-60;}
                    if (b<0){
                        yONCurrent.setText(Integer.toString(a)+" / ( "+Integer.toString(b)+" ) = "+Integer.toString(a/b));
                    }else if(b>=0){
                    yONCurrent.setText(Integer.toString(a)+" / "+Integer.toString(b)+" = "+Integer.toString(a/b));}
                    break;
                case "*" :
                    a = random.nextInt(61)-30;
                    b = random.nextInt(61)-30;
                    if (b<0){
                        yONCurrent.setText(Integer.toString(a)+" * ( "+Integer.toString(b)+" ) = "+Integer.toString(a*b));
                    }else if(b>=0){
                    yONCurrent.setText(Integer.toString(a)+" * "+Integer.toString(b)+" = "+Integer.toString(a*b));}
                    break;
                default:
            }
        }else{
            int wrongAnswer;
            String currentOperation = operations[random.nextInt(4)];
            switch (currentOperation){
                case "-":
                    wrongAnswer = a-b + balanceHard[random.nextInt(balanceHard.length)];
                    if (b <0){
                        yONCurrent.setText(Integer.toString(a)+" - ( "+Integer.toString(b)+" ) = "+Integer.toString(wrongAnswer));
                    }else if(b>=0){
                    yONCurrent.setText(Integer.toString(a)+" - "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));}
                    break;
                case "+":
                    wrongAnswer = a+b + balanceHard[random.nextInt(balanceHard.length)];
                    if(b<0){
                        yONCurrent.setText(Integer.toString(a)+" + ( "+Integer.toString(b)+" ) = "+Integer.toString(wrongAnswer));
                    }else if(b>=0){
                    yONCurrent.setText(Integer.toString(a)+" + "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));}
                    break;
                case  "/":
                    a = random.nextInt(121)-60;
                    b = random.nextInt(121)-60;
                    while(a==0 || b==0 || !(a%b == 0) ){
                        a = random.nextInt(121)-60;
                        b = random.nextInt(121)-60;}
                    wrongAnswer = a/b + balanceHard[random.nextInt(balanceHard.length)];
                    if (b<0){
                        yONCurrent.setText(Integer.toString(a)+" / ( "+Integer.toString(b)+" ) = "+Integer.toString(wrongAnswer));
                    }else if(b>=0){
                    yONCurrent.setText(Integer.toString(a)+" / "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));}
                    break;
                case "*" :
                    a = random.nextInt(61)-30;
                    b = random.nextInt(61)-30;
                    wrongAnswer = a*b + balanceHard[random.nextInt(balanceHard.length)];
                    if (b<0){
                        yONCurrent.setText(Integer.toString(a)+" * ( "+Integer.toString(b)+" ) = "+Integer.toString(wrongAnswer));
                    }else if(b>=0){
                    yONCurrent.setText(Integer.toString(a)+" * "+Integer.toString(b)+" = "+Integer.toString(wrongAnswer));}
                    break;
                default:
            }
        }
    }

    public void yONStartHard(View view){
        type = 2;
        yONLevelGridLayout.setVisibility(View.INVISIBLE);
        yONYes.setVisibility(View.VISIBLE);
        yONNo.setVisibility(View.VISIBLE);
        yONCurrent.setVisibility(View.VISIBLE);
        yONTimer.setVisibility(View.VISIBLE);
        yONScore.setVisibility(View.VISIBLE);
        yONSituation.setText("برو که رفتیم !");
        yONSituation.setVisibility(View.VISIBLE);
        yONSecond.setVisibility(View.VISIBLE);
        yONThird.setVisibility(View.VISIBLE);
        generateHard();

        yONCountDownTimer = new CountDownTimer(60400,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                yONTimer.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                if (round>=balance+10 && round-score<=score){
                    yONSituation.setText("بردی ! ادامه میدیم ژنرال !");
                    yONCountDownTimer.start();
                    generateHard();
                    balance +=10;
                }else {
                    yONSituation.setText("باختی ! بیشتر تلاش کن !");
                    score = 0;
                    round = 0;
                    balance = 0;
                    yONTimer.setText("0s");
                    yONLevelGridLayout.setVisibility(View.VISIBLE);
                    yONNo.setVisibility(View.INVISIBLE);
                    yONYes.setVisibility(View.INVISIBLE);
                    yONCurrent.setVisibility(View.INVISIBLE);
                    yONThird.setVisibility(View.INVISIBLE);
                    yONSecond.setVisibility(View.INVISIBLE);
                }}
        }.start();
    }

    public void yONChoose (View view){
        round++;
        if ((yesOrNo == 1 && view.getTag().equals("1")) || (yesOrNo == 0 && view.getTag().equals("0") )){

            if (round>1){
                yONThird.setText(yONSecond.getText());
                yONThird.setTextColor(yONSecond.getCurrentTextColor());
            }
         yONSituation.setText(yesOrNoQuotes.getCorrectAnswer());
            score++;
            yONSecond.setText(yONCurrent.getText());
            yONSecond.setTextColor(Color.parseColor("#43A047"));

        }else {
            if (round>1){
                yONThird.setText(yONSecond.getText());
                yONThird.setTextColor(yONSecond.getCurrentTextColor());
            }
            yONSituation.setText(yesOrNoQuotes.getWrongAnswer());
            yONSecond.setText(yONCurrent.getText());
            yONSecond.setTextColor(Color.parseColor("#E53935"));

        }
        yONScore.setText(Integer.toString(score)+"/"+Integer.toString(round));
        if (type == 0){
            generate();
        }else if(type == 1){
            generateMed();
        }else if(type == 2){
            generateHard();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent yONBackIntent = new Intent(YesOrNo.this , MainActivity.class);
        YesOrNo.this.startActivity(yONBackIntent);
        YesOrNo.this.finish();
    }
}
