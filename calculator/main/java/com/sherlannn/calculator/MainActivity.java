package com.sherlannn.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText enter;
    private TextView displayOperatio;

    private Double operand1 = null;
    private String pendingOperation = "=";
    private static final String save = "save_Contents";
    private static final String saveOperand ="saveOperand";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = (EditText) findViewById(R.id.enter);
        result = (EditText) findViewById(R.id.result);
        displayOperatio = (TextView) findViewById(R.id.equalsTextView);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button dot = (Button) findViewById(R.id.dot);

        Button plus = (Button) findViewById(R.id.plus);
        Button multiply = (Button) findViewById(R.id.multiply);
        Button minus = (Button) findViewById(R.id.minus);
        Button division = (Button) findViewById(R.id.division);
        Button equals = (Button) findViewById(R.id.equals);

        View.OnClickListener ourOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                enter.append(button.getText().toString());
            }
        };
        button0.setOnClickListener(ourOnClickListener);
        button1.setOnClickListener(ourOnClickListener);
        button2.setOnClickListener(ourOnClickListener);
        button3.setOnClickListener(ourOnClickListener);
        button4.setOnClickListener(ourOnClickListener);
        button5.setOnClickListener(ourOnClickListener);
        button6.setOnClickListener(ourOnClickListener);
        button7.setOnClickListener(ourOnClickListener);
        button8.setOnClickListener(ourOnClickListener);
        button9.setOnClickListener(ourOnClickListener);
        dot.setOnClickListener(ourOnClickListener);
        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button opButton = (Button) view;
                String op = opButton.getText().toString();
                String value = enter.getText().toString();
                try {
                    Double valueDouble = Double.valueOf(value);
                    performOperation(valueDouble, op);
                } catch (NumberFormatException e) {
                    enter.setText("");
                }
                pendingOperation = op;
                displayOperatio.setText(pendingOperation);
            }
        };

        equals.setOnClickListener(opListener);
        plus.setOnClickListener(opListener);
        multiply.setOnClickListener(opListener);
        minus.setOnClickListener(opListener);
        division.setOnClickListener(opListener);

        Button negButton =(Button) findViewById(R.id.negative);
        negButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=enter.getText().toString();
                if(value.length()==0){
                    enter.setText("-");
                }else{
                    try{
                        Double entered=Double.valueOf(value);
                        entered *= -1;
                        enter.setText(entered.toString());
                    }catch (NumberFormatException e){
                        enter.setText("");
                        Toast.makeText(getApplicationContext(),"error!",Toast.LENGTH_SHORT).show();
                    }}
            }
        });
    }
    public void helpabout(View view){
     setContentView(R.layout.aboutus);}
    public void backbut(View view){
        setContentView(R.layout.activity_main);}
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation=savedInstanceState.getString(save);
        operand1=savedInstanceState.getDouble(saveOperand);
        displayOperatio.setText(pendingOperation);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(save, pendingOperation);
        if(operand1!=null){
            outState.putDouble(saveOperand,operand1);}
        super.onSaveInstanceState(outState);}
    private void performOperation(Double value, String operation) {
        if (operand1 == null) {
            operand1 = value;} else {
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;}
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                        Toast.makeText(getApplicationContext(), "error :|", Toast.LENGTH_LONG).show();
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "-":
                    operand1 = operand1-value;
                    break;
                case "+":
                    operand1 =operand1+value;
                    break;}
        }
        result.setText(operand1.toString());
        enter.setText("");}
}