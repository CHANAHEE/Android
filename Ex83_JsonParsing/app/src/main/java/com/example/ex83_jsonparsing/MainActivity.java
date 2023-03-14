package com.example.ex83_jsonparsing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.example.ex83_jsonparsing.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(view->clickBtn());
        binding.btn2.setOnClickListener(view->clickBtn2());
    }

    void clickBtn(){
        AssetManager assetManager = getAssets();
        // 1_ assets/aaa.json 파일을 읽어오기 위한 InputStream 열자
        try {
            InputStream is = assetManager.open("aaa.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            while(true){
                String line = reader.readLine();
                if(line == null) break;
                buffer.append(line + "\n");
            }
            String jsonStr = buffer.toString();
            // 2_ 읽어온 json 문자열을 확인부터 해보자.
            binding.tv.setText(jsonStr);

            // 3_ Json 문자열을 분석(parse) 해보자.
            JSONObject jo = new JSONObject(jsonStr);
            String name = jo.getString("name");
            String message = jo.getString("msg");
            int age = jo.getInt("age");
            String nation = jo.getJSONObject("address").getString("nation");
            String city = jo.getJSONObject("address").getString("city");
            binding.tv.setText(name + "     " + message + "     " + age+"\n"+nation+"       "+city  );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<Item> items = new ArrayList<>();

    void clickBtn2(){
        AssetManager assetManager = getAssets();
        try {
            InputStream is = assetManager.open("bbb.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            while(true){
                String line = reader.readLine();
                if(line == null) break;
                buffer.append(line+"\n");
            }
            String jsonStr = buffer.toString();
            JSONArray ja = new JSONArray(jsonStr);
            for(int i=0;i<ja.length();i++){
                JSONObject jo = ja.getJSONObject(i);

                int no = jo.getInt("no");
                String name = jo.getString("name");
                String msg = jo.getString("msg");

                items.add(new Item(no,name,msg));
            }
            binding.tv.setText(items.size()+"");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}