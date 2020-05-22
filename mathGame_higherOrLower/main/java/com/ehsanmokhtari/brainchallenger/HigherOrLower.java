package com.ehsanmokhtari.brainchallenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class HigherOrLower extends AppCompatActivity {

    EditText hOLEntered ;
    Random random = new Random();
    int theNumber;
    int highborder;
    int times = 0;
    TextView hOLTimes;
    TextView hOLSituation;
    GridLayout hOLLevelGridLayout;
    Button hOLTest;
    ImageButton hOLStart;
    Quotes hOLQuotes = new Quotes();
    String[] numberIsLower = {"برو بالاتر ...","خیلی پایینی !","بالا بالا بالا !","بالاتر !","هنوز پایینی !","عین هواپیما برو بالا !",
            "پایین تری هنوز !"};
    String[] numberIsHigher ={"بیا پایین تر!","خیلی بالایی !","پایین تر !","هنوز بالایی ...","فرود بیا پایین !"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_or_lower);
        hOLEntered = (EditText) findViewById(R.id.hOLEnterdeNumberEditText);
        hOLTimes = (TextView) findViewById(R.id.hOLTimes);
        hOLSituation = (TextView) findViewById(R.id.hOLSituationTextView);
        hOLTest = (Button) findViewById(R.id.hOLTestButton);
        hOLStart = (ImageButton) findViewById(R.id.hOLStartImageButton);
        hOLLevelGridLayout = (GridLayout) findViewById(R.id.hOLLevelGridLayout);
    }

    public void hOLTest(View view){
        int enteredNumber;
        try {
          enteredNumber = Integer.valueOf( hOLEntered.getText().toString());
            times++;
            hOLTimes.setText(Integer.toString(times));
                  if (enteredNumber >highborder || enteredNumber<0){
                      hOLSituation.setText("وارد کن !"+highborder+"یه عدد از 0 تا ");
                  }else {
                  if (enteredNumber<theNumber){
                      hOLSituation.setText( numberIsLower[random.nextInt(numberIsLower.length)]+"\n");
                      hOLSituation.append("your number : "+enteredNumber);
                  }
                  if (enteredNumber>theNumber){
                      hOLSituation.setText(numberIsHigher[random.nextInt(numberIsHigher.length)]+"\n");
                      hOLSituation.append("your number : "+enteredNumber);
                  }

                  if (enteredNumber == theNumber){
                      hOLSituation.setText(hOLQuotes.getCorrectAnswer()+"\n\n"+"از اول بریم ؟؟");
                      hOLTimes.setText("تعداد دفعاتت : "+times);
                      hOLTest.setVisibility(View.INVISIBLE);
                      hOLEntered.setVisibility(View.INVISIBLE);
                      hOLLevelGridLayout.setVisibility(View.VISIBLE);
                  }}
            hOLEntered.setText(null);

        }catch(Exception e){
            hOLSituation.setText("خالیه ! یه عدد وارد کن !");
        }

    }

    public void hOLStart(View view){
        highborder =200;
        times=0;
        theNumber = random.nextInt(201);
        hOLSituation.setText("برو که رفتیم !"+"\n\n"+"یه عدد از 0 تا 200 وارد کن !");
        hOLEntered.setVisibility(View.VISIBLE);
        hOLTimes.setVisibility(View.VISIBLE);
        hOLSituation.setVisibility(View.VISIBLE);
        hOLTest.setVisibility(View.VISIBLE);
        hOLLevelGridLayout.setVisibility(View.INVISIBLE);
    }

    public void hOLStartMed(View view){
        highborder = 1000;
        times=0;
        theNumber = random.nextInt(1001);
        hOLSituation.setText("برو که رفتیم !"+"\n\n"+"یه عدد از 0 تا 1000 وارد کن !");
        hOLEntered.setVisibility(View.VISIBLE);
        hOLTimes.setVisibility(View.VISIBLE);
        hOLSituation.setVisibility(View.VISIBLE);
        hOLTest.setVisibility(View.VISIBLE);
        hOLLevelGridLayout.setVisibility(View.INVISIBLE);
    }

    public void hOLStartHard(View view){
        highborder = 100000;
        times=0;
        theNumber = random.nextInt(100001);
        hOLSituation.setText("برو که رفتیم !"+"\n\n"+"یه عدد از 0 تا 100000 وارد کن !");
        hOLEntered.setVisibility(View.VISIBLE);
        hOLTimes.setVisibility(View.VISIBLE);
        hOLSituation.setVisibility(View.VISIBLE);
        hOLTest.setVisibility(View.VISIBLE);
        hOLLevelGridLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent hOLBackIntent = new Intent(HigherOrLower.this , MainActivity.class);
        HigherOrLower.this.startActivity(hOLBackIntent);
        HigherOrLower.this.finish();
    }


}
