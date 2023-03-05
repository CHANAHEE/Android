package com.example.myrecipeapp;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class RecycleritemFragment extends Fragment {

    Toolbar toolbar;
    MainActivity mainActivity;
    Context context;
    int idx = 0;
    Bundle bundle;
    SQLiteDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        idx = getArguments().getInt("index") - 1;

        return inflater.inflate(R.layout.fragment_recycleritem,container,false);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == 16908332){

            mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,mainActivity.allRecipeFragment).addToBackStack(null).commit();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = view.findViewById(R.id.toolbar);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setSupportActionBar(toolbar);

        ActionBar actionBar = mainActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        actionBar.setTitle("");


        view.findViewById(R.id.bookmark_btn).setOnClickListener(v -> {
            if(v.isSelected()) {
                v.setSelected(false);
            }
            else {
                v.setSelected(true);
                saveSelectedItem();
            }
        });


        /*
        *
        * 프래그먼트 뷰에 레시피 정보 뿌리기.
        *
        * */
        database = SQLiteDatabase.openDatabase("/data/data/com.example.myrecipeapp/databases/recipe.db",null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = database.rawQuery("SELECT * FROM recipe",null);
        cursor.moveToPosition(idx);

        ImageView rcv_img = view.findViewById(R.id.img_recycleritemview);
        TextView rcv_title = view.findViewById(R.id.rcv_item_title);
        TextView rcv_ingre = view.findViewById(R.id.rcv_item_ingre);
        TextView rcv_howto = view.findViewById(R.id.rcv_item_howto);

        String title = cursor.getString(1);
        String img = cursor.getString(2);
        String ingre = cursor.getString(4);
        String howto = cursor.getString(5);

        Glide.with(context).load(img).into(rcv_img);
        rcv_title.setText(title);
        rcv_ingre.setText(ingre);
        rcv_howto.setText(howto);
    }

    void saveSelectedItem(){
        database = context.openOrCreateDatabase("selecteditem.db",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS selecteditem(num INTEGER PRIMARY KEY , idx INTEGER)");
        database.execSQL("INSERT INTO selecteditem (idx) VALUES (?)",new String[]{ idx+"" });
        Log.i("cursor",idx +"리사이클러뷰 아이템 인덱스");
        // INSERT 수행

    }
}

