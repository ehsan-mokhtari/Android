package com.ehsanmokhtari.ds_p1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText enter;
    List<String> splitted = new ArrayList<String>();
    List result = new ArrayList();
    String geted;
    int a,c,g,t ;
    List stop = new ArrayList();
    EditText editText5;
    EditText textView2;
    EditText editText8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter = (EditText) findViewById(R.id.enter_edittext);
        editText5 =(EditText) findViewById(R.id.editText5);
        textView2 = (EditText) findViewById(R.id.textView2);
        editText8 = (EditText) findViewById(R.id.editText8);
    }
    public void get(View veiw){
        a=0;
        g=0;
        c=0;
        t=0;
        try {
            geted = enter.getText().toString();
            proccess(geted);
            editText5.setText(result.toString());
            if(result.isEmpty() || result.contains(null)){
                editText5.setText("*****  Please enter valid string  *****");
                textView2.setText("*****  Please enter valid string to proccess on it ! *****");
                editText8.setText("*****  Please enter valid string to proccess on it ! *****");
            }else{
                String currentChar;
                for(int i=0;i<geted.length();i++){
                    currentChar = geted.substring(i, i+1);
                    switch (currentChar) {
                        case "A":
                            a++;
                            break;
                        case "C":
                            c++;
                            break;
                        case "G":
                            g++;
                            break;
                        case "T":
                            t++;
                            break;
                        default:
                            break;
                    }
                }
                int sum =a+g+t+c;
                textView2.setText("n(A)="+a+",n(C)="+c +"\nn(G)="+g+",n(T)="+t+"\n"+"p(A)="+a*100/sum+"%,p(C)="+c*100/sum+"%\np(G)="+g*100/sum
                        +",%p(T)="+t*100/sum+"%");
                if (result.contains("M") && result.contains("*")){
                    int indexofM =(((result.indexOf("M"))*3)+1);
                    for(int i=0;i<result.size();i++){
                        if(result.get(i).equals("*")){
                            if (i>result.indexOf("M")) {
                                stop.add(i);
                            }
                        }
                    }
                    int thelast = (int) (stop.get(0))*3+1;
                    System.out.println(result.get((int)stop.get(0)));


                    editText8.setText("Start codon for ATG = "+indexofM+"\nStop codon = "+thelast);

                    stop.clear();
                } else if(!result.contains("M") || !result.contains("*")){
                    editText8.setText("There is no @@ATG@@ or @@Stop codon@@ !");
                }
                splitted.clear();
                result.clear();
                stop.clear();

            }
        } catch (Exception e) {
            editText5.setText("*****  Please enter valid string  *****");
            textView2.setText("*****  Please enter valid string to proccess on it ! *****");
            editText8.setText("*****  Please enter valid string to proccess on it ! *****");
        }
    }



    void proccess (String data){
        try{
            for(int i =0;i<data.length();i=i+3){
                splitted.add(data.substring(i, i+3));
            }
            String converted=null;
            for(int j=0;j<splitted.size();j++){
                String current = splitted.get(j);

                if(current.startsWith("A")){
                    switch(current){
                        case "AGA":
                        case "AGG":
                            converted = "R";
                            break;
                        case "AAC":
                        case "AAT":
                            converted = "N";
                            break;
                        case "ATA":
                        case "ATC":
                        case "ATT":
                            converted = "I";
                            break;
                        case "AAA":
                        case "AAG":
                            converted = "K";
                            break;
                        case "ATG":
                            converted ="M";
                            break;
                        case "AGC":
                        case "AGT":
                            converted="S";
                            break;
                        case "ACA":
                        case "ACC":
                        case "ACG":
                        case "ACT":
                            converted ="T";
                            break;

                    }
                }else if(current.startsWith("C")){
                    switch (current){
                        case"CGA":
                        case"CGC":
                        case"CGG":
                        case"CGT":
                            converted = "R";
                            break;
                        case"CAA":
                        case"CAG":
                            converted = "Q";
                            break;
                        case"CAC":
                        case"CAT":
                            converted = "H";
                            break;
                        case"CTA":
                        case"CTC":
                        case"CTG":
                        case"CTT":
                            converted ="L";
                            break;
                        case"CCA":
                        case"CCC":
                        case"CCG":
                        case"CCT":
                            converted ="P";
                            break;
                        default:

                    }
                }else if(current.startsWith("G")){
                    switch(current){
                        case"GCA":
                        case"GCC":
                        case"GCG":
                        case"GCT":
                            converted ="A";
                            break;
                        case"GAC":
                        case"GAT":
                            converted = "D";
                            break;
                        case"GAA":
                        case"GAG":
                            converted ="E";
                            break;
                        case"GGA":
                        case"GGC":
                        case"GGG":
                        case"GGT":
                            converted ="G";
                            break;
                        case"GTA":
                        case"GTC":
                        case"GTG":
                        case"GTT":
                            converted ="V";
                            break;
                        default:
                    }
                }else if (current.startsWith("T")){
                    switch(current){
                        case"TGC":
                        case"TGT":
                            converted ="C";
                            break;
                        case"TTA":
                        case"TTG":
                            converted="L";
                            break;
                        case"TTC":
                        case"TTT":
                            converted="F";
                            break;
                        case"TCA":
                        case"TCC":
                        case"TCG":
                        case"TCT":
                            converted="S";
                            break;
                        case"TGG":
                            converted ="W";
                            break;
                        case"TAC":
                        case"TAT":
                            converted="Y";
                            break;
                        case"TAG":
                        case"TGA":
                        case"TAA":
                            converted="*";
                            break;

                    }
                }
                result.add(converted);

            }

        }catch(Exception e){
            editText5.setText("*****  Please enter valid string  *****");
        }


    }
}
