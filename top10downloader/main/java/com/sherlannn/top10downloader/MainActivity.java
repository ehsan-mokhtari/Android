package com.sherlannn.top10downloader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//AppCompatActivity : Basic class for activities
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml";
    private int feedLimit = 10;
    //this variable is for if we select the same menu item,it do not download again
    private String feedCashedUrl = "";
    //using for onSaveInstanceState
    public String ticked = "ticked";
    public static final String State_Url = "FeedUrl";
    public static final String State_limit="FeedLimit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declaring the textView
        listView = (ListView) findViewById(R.id.xmlLIstView);
        //if any orientation is happened ,the on create method will start again
        //so we put our saveInstanceState code here
        if(savedInstanceState != null){
            feedUrl = savedInstanceState.getString(State_Url);
            feedLimit = savedInstanceState.getInt(State_limit);}
        downloadUrl(String.format(feedUrl,feedLimit));}

    //setting the url address
    private void downloadUrl(String feedUrl) {
        //if we click the same item menu,it doesn't reDownload it
        if(!feedUrl.equalsIgnoreCase(feedCashedUrl)){
        DownloadData downloadData = new DownloadData();
        //executes the task with specified parameters or its the asyncTask first param
        downloadData.execute(feedUrl);
            feedCashedUrl=feedUrl;}}

    //creating an inner class
    //<type of received or input data , process , type of output data>
    //in common three of them are Void
    //at least we have to override two methods(doInBackGround,onPostExecute)
    private class DownloadData extends AsyncTask <String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ParseXml parseXml = new ParseXml();
            //parse is a method in ParseXml class
            //the s is the parameter we entered on execute
            //translating that xml to sth we want
            parseXml.parse(s);
            //View is sth can be displayed on screen
            //arrayAdapter uses text view as a view to send to listView to show on screen
            /*why we created a list_item layout with root element "TextView ??
            the arrayAdapter jus uses this xml file to create a textView
            then puts the object text into it before sending it on listView */
            //creating an arrayAdapter
            //adapter is like a bridge between adapterView and data for that "view
            //(??? , ??? , the data that we want to use)
            //down comment is an simple use of ArrayAdapter
            /*ArrayAdapter<FeedEntry> arrayAdapter =
       new ArrayAdapter<FeedEntry>(MainActivity.this , R.layout.list_item , parse1.getApplication());
            //setting an adapter for that view
            listView.setAdapter(arrayAdapter);*/

            //using of our costume ArrayAdapter
            FeedAdapter feedAdapter = new FeedAdapter(MainActivity.this, R.layout.list_record,parseXml.getApplication());
            listView.setAdapter(feedAdapter);}
        @Override
        protected String doInBackground(String... params) {
            //receiving the xml from url
            String rssFeed = downloadXml(params[0]);
            return rssFeed;}
        private String downloadXml(String urlPath) {
            //StringBuilder is easier than String.we can append text to it easily
            StringBuilder xmlResult = new StringBuilder();
            try {//making uniform resource locator or webPath
                URL url = new URL(urlPath);
                //enabling data stream (receiving and sending) from that url
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // receiving response code ... 200 is ok
                int response = connection.getResponseCode();
               /* InputStream inputStream =connection.getInputStream();
               *  InputStreamReader inputStreamReader =new InputStreamReader(inputStream);
               *  BufferedReader reader=new BufferedReader(inputStreamReader); */
               /* BufferedReader : reading data from inputStream (in characters).
                it reads byte to byte,safe,for receiving data that may has buffer*/
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                int charsRead;
                char[] inputBuffer = new char[700];
                while (true) { charsRead = reader.read(inputBuffer);
                    if (charsRead < 0) {break;}
                    if (charsRead > 0) {xmlResult.append(String.copyValueOf(inputBuffer, 0, charsRead));}}
                //BufferedReader should close to not receive anymore
                reader.close();
                return xmlResult.toString();}
            //this exception says may the url is unCorrect!
            catch (MalformedURLException e) {}
            catch (IOException e) {}
            //this exception is for if there is no permission then don't crash!
            catch (SecurityException e) {}  return null;}}

    /*   creating menu :
         res > new > android resource directory >   directory name : menu & resource type : menu
  then   res > menu > new > menu resource file >   choose a name for it    */
    //for declaring the menu to java , override these
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //group:checkableBehavior=single  top10:checked=ticked
        //getMenuInflater() : this class is used to instantiate menu XML files into menu objects
        getMenuInflater().inflate(R.menu.feeds_menu, menu);
        return true;}
    //for putting the code for menu items
    @Override
    //MenuItem : an interface for direct access to created menu items
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.topFreeGames :
                feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml";  break;
            case R.id.topSongs :
                feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=%d/xml";  break;
            case R.id.menu10://!item.isChecked() : that radioButton is pressed
                if (!item.isChecked()){  item.setChecked(true); feedLimit=10;}break;
            case R.id.menu25:
                if (!item.isChecked()){  item.setChecked(true); feedLimit=25;}break;
            case  R.id.refresh : //it causes the downloadUrl reDownload again
                feedCashedUrl = "somethingwrong"; break;
            //important : default method should be like this
            default:  return super.onOptionsItemSelected(item);}
        downloadUrl(String.format(feedUrl,feedLimit));
        return true;}
    //the super method saves ,so we need to put our code before it to save
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(State_Url,feedUrl);
        outState.putInt(State_limit,feedLimit);
        super.onSaveInstanceState(outState);}}