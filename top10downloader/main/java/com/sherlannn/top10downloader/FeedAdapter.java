package com.sherlannn.top10downloader;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

//this is our costume ArrayAdapter class
/*  when we created this class;in superclass part,we should
    write android.widget.ArrayAdapter  */
/*  what i understand from adapter,it actually receives its data from an xml file and
    puts it on a view.so for that reason we created that list_item.xml layout
    for simple ArrayAdapter that saves its received data there then shows the
    data in the list view  */
/*  now we want to costume our array adapter so we create another list_record.xml
    that our costume ArrayAdapter save its received data there and then put in
    the listView rows like that .for example this layout has three parameter
   (TextView) and jus like that every row in listView will be like the same style!
   # ArrayAdapter just uses for string data so in this section for that we use it !*/

public class FeedAdapter extends ArrayAdapter {
    /* LayoutInflater takes the layout.xml(for example in this case it takes the data that stored
       by ArrayAdapter in list_record.xml)and then inflates it to produce an actual view object.
       we shouldn't create an instance of it.instead we should use getLayoutInflater()
       or getSystemService(class)  to get standard LayoutInflater that is already
       hooked up in current context and correctly  configured for the device */
    // for final method we must declare an value.
    private final LayoutInflater layoutInflater;
    //???
    private final int layoutResource;
    private List<FeedEntry> applications;

    // the constructor of this class
    /* Context : is an interface class to global information about application
       environment and allows to access to application specific resources & classes
       such as LayoutInflater ! */
    public FeedAdapter(Context context, int resource, List<FeedEntry> applications) {
        super(context, resource);
        this.layoutResource = resource;
        //we cant make an instance of it so we use from(context) method
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = applications;}
    //ctrl + o for @Override methods
    //for getting the number of rows we need in listView
    //if we don't use this method,the listView will be empty.
    @Override
    public int getCount() { //returns the number of elements in the list
        return applications.size();}
    //this view method is called by the listView every time wants another item or row to display
    /*   @Override
      public View getView(int position,View convertView,ViewGroup parent) {
        View view = layoutInflater.inflate(layoutResource,parent,false);
             # why we use view. in this part?we set the up view to that list_record.xml
  in fact this view is for that constraintLayout that holds 4 textView.so we are searching the ids in that view.so we use view. ! #

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvArtist = (TextView) view.findViewById(R.id.tvArtist);
        TextView tvSummary  = (TextView) view.findViewById(R.id.tvSummary);
        TextView tvReleaseDate= (TextView) view.findViewById(R.id.tvReleaseDate);
        FeedEntry   currentApp  = applications.get(position);
        tvName.setText(currentApp.getName());
        tvArtist.setText(currentApp.getArtist());
        tvSummary.setText(currentApp.getSummary());
        tvReleaseDate.setText(currentApp.getReleaseDate()); return view;   }  */
    //cause of its over usage of ram we need to edit it for that i commented all this method
    /* in simple basic adapter,when it sends data to listView,by scrolling
       the textView,the out of screen rows will turn off to prevent over
       usage of ram.in our costumeAdapter it doesn't.so we need to create a method to fix that problem ! */

    /* findViewById uses a lot memory usage.so in getView every time it
      calls by lisView ,the findViewById method uses a lot memory.so
      in this basic class we put findViewById methods that after using
      by getView,its data clear i think,so it doesn't overUse of ram anymore  */
    private class ViewHolder {
        final TextView tvName;
        final TextView tvArtist;
        final TextView tvSummary;
        final TextView tvReleaseDate;
        ViewHolder(View v) {
            this.tvName = (TextView) v.findViewById(R.id.tvName);
            this.tvArtist = (TextView) v.findViewById((R.id.tvArtist));
            this.tvSummary = (TextView) v.findViewById(R.id.tvSummary);
            this.tvReleaseDate = (TextView) v.findViewById(R.id.tvReleaseDate);}}
    @Override
    //this view method is called by the listView every time wants another item or row to display
    //getView : get a view that displays the data at the specified position
    //a ViewGroup is a view can contain other views(child)
    //the position of the item whose view we want
    //convertView : a view that we want to convert to show whe correct data on screen
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);}
        else {  viewHolder = (ViewHolder) convertView.getTag();}

        FeedEntry currentApp = applications.get(position);
        viewHolder.tvName.setText(currentApp.getName());
        viewHolder.tvArtist.setText(currentApp.getArtist());
        viewHolder.tvReleaseDate.setText(currentApp.getReleaseDate());
        viewHolder.tvSummary.setText(currentApp.getSummary());

        return convertView;}}
