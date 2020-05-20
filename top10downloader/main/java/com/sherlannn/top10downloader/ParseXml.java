package com.sherlannn.top10downloader;
//For parsing the data we've downloaded from url
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.StringReader;
import java.util.ArrayList;
public class ParseXml {
    //this arrayList will show the data on our phone screen
    private ArrayList<FeedEntry> applications;
    public ParseXml() {
        this.applications = new ArrayList<>();}
    public ArrayList<FeedEntry> getApplication() {
        return applications;}
    public boolean parse(String xmlData) {
        boolean status = true;
        FeedEntry currentRecord = null;
        boolean inEntry = false;
        // to store the text inside the current tag
        String textValue = "";
        try {//XmlPullParserFactory :this class is used to create implementation of XmlPullParser .
            //newInstance() : creates new instance of xppf that can be used to create xpp .
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //this parser will provide support for XML namespace? yes -> true   no -> false
            factory.setNamespaceAware(true);
            /* xpp is an interface that defines parsing functionality
             * we can not create an object of it
             * so we use xppf to create an instance for it to use of that  */
            XmlPullParser xpp = factory.newPullParser();
            //StringReader : treats the string like stream
            //or like the way that xpp reads data
            xpp.setInput(new StringReader(xmlData));
            //getEventType() : return the type of current event (START_TAG,END_TAG,TEXT,etc)
            int eventType = xpp.getEventType();
            //XmlPullParser.END_DOCUMENT : end of xml ,no more events are available
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //get the tag name
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("entry".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentRecord = new FeedEntry();}
                        break;
                    case XmlPullParser.TEXT:
                        //getting the text inside the tag
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry) { //equalsIgnoreCase = comparing two String without case Sensitive
                            if ("entry".equalsIgnoreCase(tagName)) {
                                //adding current FeedEntry object to applications arrayList
                                applications.add(currentRecord);
                                inEntry = false;
                            } else if ("name".equalsIgnoreCase(tagName)) {
                                currentRecord.setName(textValue);
                            } else if ("artist".equalsIgnoreCase(tagName)) {
                                currentRecord.setArtist(textValue);
                            } else if ("releaseDate".equalsIgnoreCase(tagName)) {
                                currentRecord.setReleaseDate(textValue);
                            } else if ("summary".equalsIgnoreCase(tagName)) {
                                currentRecord.setSummary(textValue);}}break;
                    default:}
                //going for next tag
                eventType = xpp.next();}
        } catch (Exception e) {
            status = false;
            //printing the type of exception in androidStudio monitor
            e.printStackTrace();}
        return status;}}
