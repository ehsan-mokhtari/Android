package com.ehsanmokhtari.mynotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    //if we want to access to sth form another class its better to make it static
    //its good to set first generic as an its dataType
    //static is for accessing to another class variable without creating an object of the class
    public static List<String> myNoteList = new ArrayList();
    public static ArrayAdapter myAdapter;
    //Set is a collection of objects that they don't have pair!
    public static Set<String> set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView notesListView = (ListView) findViewById(R.id.notes_listView);
        SharedPreferences sharedPreferences =  this.getSharedPreferences("com.ehsanmokhtari.mynotes", Context.MODE_PRIVATE);
        //StringSet = a set of strings or in easy way string array !
        //(the key , the default)
        set = sharedPreferences.getStringSet("notes",null);
        /*this is for when we press back button and come back to this screen,the first note !
        adds again to the list twice and ... ! */
        //in this app we use set as temporary list and myNoteList as official list to adapt to listView !
        myNoteList.clear();
        if(set!=null){   myNoteList.addAll(set);
        }else{  set = new HashSet<String>();
            myNoteList.add("first note !");
            //in second or third or .. times cause we have first note ! it doesn't add it to set !
            set.addAll(myNoteList);
            sharedPreferences.edit().putStringSet("notes",set).apply(); }
        myAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myNoteList);
        notesListView.setAdapter(myAdapter);
        //setting onItemClickListener to with clicking on every item it go to edit activity to edit its string
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //send its string from here with Intent !
                //Intent send values between activities or receives them !
                //(context , the class that we want to send the values!)
                Intent i = new Intent(getApplicationContext(),Edit.class);
                //position is the number of row that clicked
                i.putExtra("noteRowId",position);
                //this one is optional!in here we want after clicking it go to edit activity
                startActivity(i);} });
        notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(set == null){ set = new HashSet<String>();}
                else{set.clear();}
                myNoteList.remove(position);
                set.addAll(myNoteList);
                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.ehsanmokhtari.mynotes", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("notes").apply();
                sharedPreferences.edit().putStringSet("notes",set).apply();
                myAdapter.notifyDataSetChanged();
                return false;}});
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.add :
                myNoteList.add("");
                if(set ==null){set = new HashSet<String>();}
                else{set.clear();}
                set.addAll(myNoteList);
                SharedPreferences sharedPreferences = this.getSharedPreferences("com.ehsanmokhtari.mynotes", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("notes").apply();
                sharedPreferences.edit().putStringSet("notes",set).apply();
                myAdapter.notifyDataSetChanged();
                Intent i = new Intent(getApplicationContext(),Edit.class);
                i.putExtra("noteRowId",myNoteList.size()-1);
                startActivity(i);
                break;  }
        return super.onOptionsItemSelected(item); }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;}
}
