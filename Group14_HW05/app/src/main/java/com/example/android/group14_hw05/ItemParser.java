package com.example.android.group14_hw05;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class ItemParser {
    public static class ItemsPullParser{
        static public ArrayList<DataObject> parseItems(InputStream inputStream) throws XmlPullParserException, IOException{
            ArrayList<DataObject> items = new ArrayList<>();
            DataObject item = null;

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, "UTF-8");

            int event = parser.getEventType();
            boolean hasPassed = false;

            while(event != XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_TAG:
                        System.out.println(parser.getName());
                            if(parser.getName().equals("entry")){
                                hasPassed = true;
                                item = new DataObject();
                            } else if (parser.getName().equals("title") && hasPassed) {
                                item.setTitle(parser.nextText().trim());
                            } else if (parser.getName().equals("im:image") && hasPassed && Integer.parseInt(parser.getAttributeValue(null, "height")) == 55) {
                                item.setSmallImageURL(parser.nextText().trim());
                            }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("entry")){
                            items.add(item);
                        }
                        break;
                    default:
                        break;
                }

                event = parser.next();
            }
            return items;
        }
    }
}
