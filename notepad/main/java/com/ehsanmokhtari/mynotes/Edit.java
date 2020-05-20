package com.ehsanmokhtari.mynotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

/*in creating an activity in hierarchical parent if we set an activity,
          it will set an back icon on the new activity action bar that with pressing on that
          it will go to the parent activity that we've set !   */
public class Edit extends AppCompatActivity implements TextWatcher {
    int receivedNoteRowId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_edit);
        /* in multiline text editor in gravity we can tick top and left
           to wen we write on it ,it starts from there.the default is from center! */
        EditText multiline = (EditText) findViewById(R.id.multiline_editText);
        //receiving the intent
        Intent i = getIntent();
        //( ,default value) >> if there was not an value on intent,put -1 on noteId
        receivedNoteRowId = i.getIntExtra("noteRowId",-1);
        if(receivedNoteRowId != -1){multiline.setText(MainActivity.myNoteList.get(receivedNoteRowId)); }
        //for every change it act sth! and has 3 implements below !
        multiline.addTextChangedListener(this);}
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    //(the value of multiline , ...)
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //changing the list with new changing!
       MainActivity.myNoteList.set(receivedNoteRowId,String.valueOf(s));
        //noticing to adapter to adapt the changes into listView
        MainActivity.myAdapter.notifyDataSetChanged();
        if(MainActivity.set ==null){MainActivity.set = new HashSet<String>();}
        else{MainActivity.set.clear();}
        MainActivity.set.addAll(MainActivity.myNoteList);
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.ehsanmokhtari.mynotes", Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("notes").apply();
        sharedPreferences.edit().putStringSet("notes", MainActivity.set).apply();}
    @Override
    public void afterTextChanged(Editable s) {}
}
