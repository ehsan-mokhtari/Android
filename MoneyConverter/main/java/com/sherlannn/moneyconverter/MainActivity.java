package com.sherlannn.moneyconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static com.sherlannn.moneyconverter.R.id.dtr1;

public class MainActivity extends AppCompatActivity {

    public void convert1(View view){
        EditText crtd =(EditText) findViewById(R.id.rtd1);
        Double  rial1 =Double.parseDouble(crtd.getText().toString());
        Double codo =rial1/37570;
        Toast.makeText(getApplicationContext(),codo.toString()+" Dollar !",Toast.LENGTH_LONG).show();
        ImageView dollar1 =(ImageView) findViewById(R.id.invisable);
        dollar1.setImageResource(R.drawable.dollar);

    }

    public void convert2(View view){
        EditText cdtr=(EditText)findViewById(dtr1);
        Double dollar1=Double.parseDouble(cdtr.getText().toString());
        Double cori=dollar1*37570;
        Toast.makeText(getApplicationContext(),cori.toString()+" Rial !",Toast.LENGTH_LONG).show();
        ImageView rial1=(ImageView)findViewById(R.id.invisable);
        rial1.setImageResource(R.drawable.rial);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
