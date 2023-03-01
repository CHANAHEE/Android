package com.example.myrecipeapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    public HomeFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview_home);
        adapter = new RecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(adapter);

        new Thread(){
            @Override
            public void run() {
                String address = "http://openapi.foodsafetykorea.go.kr/api/50c404d9fa5141449493/COOKRCP01/xml/1/10";

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
                                item = new Item();
                                tagName = xpp.getName();
                                if(tagName.equals("RCP_NM")){
                                    xpp.next();
                                    item.title = xpp.getText();
                                    Log.i("title",item.title);
                                } else if(tagName.equals("ATT_FILE_NO_MAIN")){
                                    xpp.next();
                                    item.mainImg = xpp.getText();
                                }
                                break;
                            case XmlPullParser.END_DOCUMENT:
                                break;
                            case XmlPullParser.END_TAG:
                                tagName = xpp.getName();
                                if(tagName.equals("row")){
                                    items.add(item);
                                }
                                break;
                            case XmlPullParser.TEXT:
                                break;
                        }
                        eventType = xpp.next();
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });

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
}
