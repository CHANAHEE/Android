package com.example.myrecipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    Intent intent;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled = ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);

        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);

        dbInit();

        intent = new Intent(SplashActivity.this, MainActivity.class);
        new SplashThread().start();

    }

    synchronized void dbInit(){
        database = openOrCreateDatabase("recipe.db",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS recipe(num INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(30) NOT NULL, imgurl TEXT, hash VARCHAR(30))");

        // INSERT 수행
        new Thread(){
            @Override
            public void run() {
                String address = "http://openapi.foodsafetykorea.go.kr/api/50c404d9fa5141449493/COOKRCP01/xml/1/1000";

                try {
                    URL url = new URL(address);

                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(isr);

                    int eventType = xpp.getEventType();

                    Item item = null;
                    while(eventType != XmlPullParser.END_DOCUMENT){

                        String tagName = "";
                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:

                                break;
                            case XmlPullParser.START_TAG:
                                tagName = xpp.getName();
                                if(tagName.equals("row")) {
                                    item = new Item();
                                    item.hash = "";
                                } else if(tagName.equals("RCP_NM")){
                                    xpp.next();
                                    item.title = xpp.getText();
                                } else if(tagName.equals("ATT_FILE_NO_MAIN")){
                                    xpp.next();
                                    item.mainImg = xpp.getText();
                                } else if(tagName.equals("HASH_TAG")){

                                    if(!xpp.isEmptyElementTag()) {
                                        xpp.next();
                                        item.hash += "#" + xpp.getText() + "  ";
                                    }
                                } else if(tagName.equals("RCP_WAY2")){
                                    xpp.next();
                                    item.hash += "#" + xpp.getText() + "  ";
                                } else if(tagName.equals("RCP_PAT2")){
                                    xpp.next();
                                    item.hash += "#" + xpp.getText();
                                }
                                break;
                            case XmlPullParser.END_DOCUMENT:
                                break;
                            case XmlPullParser.END_TAG:
                                tagName = xpp.getName();
                                if(tagName.equals("row")){
                                    database.execSQL("INSERT INTO recipe (title, imgurl, hash) VALUES (?,?,?)",new String[]{ item.title , item.mainImg , item.hash});

                                }

                                break;
                            case XmlPullParser.TEXT:
                                break;
                        }
                        eventType = xpp.next();
                    }


                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }

            }

        }.start();
    }
    class SplashThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            startActivity(intent);
        }
    }
}
