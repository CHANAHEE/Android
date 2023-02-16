package com.example.tp07_memberlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    AlertDialog.Builder builder;
    Button dialog_add,dialog_cancel;

    FrameLayout frameLayout;
    ListView listView;
    MyAdapter adapter;
    EditText et_name;
    RadioGroup rg_gender;
    Spinner sp_nation;
    ImageView profile;
    String nation = "";
    int[] flag_arr = {R.drawable.flag_australia,R.drawable.flag_belgium,R.drawable.flag_canada,
            R.drawable.flag_china,R.drawable.flag_france,R.drawable.flag_germany,
            R.drawable.flag_ghana,R.drawable.flag_italy,R.drawable.flag_japan,
            R.drawable.flag_korea,R.drawable.flag_nepal,R.drawable.flag_russia,R.drawable.flag_usa};

    int selecNation = 0;
    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        adapter = new MyAdapter(this,items);
        listView.setAdapter(adapter);




    }

    /*
    *
    *
    *
    * 옵션메뉴 - 서치메뉴, 추가메뉴
    *
    *
    *
    *
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu,menu);

        /*      서치 옵션 메뉴        */
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("이름을 검색하세요");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 검색된 이름(query) 과 아이템뷰의 이름을 비교해서 찾아주자.
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        frameLayout = findViewById(R.id.framelayout);
        /*      추가 옵션 메뉴        */
        if(item.getItemId() == R.id.menu_add){
            frameLayout.setVisibility(View.GONE);
            builder = new AlertDialog.Builder(this);
            builder.setView(R.layout.dialog_layout);
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            dialog_add = dialog.findViewById(R.id.addBtn);
            dialog_cancel = dialog.findViewById(R.id.cancelBtn);

            et_name = dialog.findViewById(R.id.dialog_et);
            rg_gender = dialog.findViewById(R.id.dialog_rg);
            sp_nation = dialog.findViewById(R.id.dialog_nation);


            sp_nation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String[] nations = getResources().getStringArray(R.array.nation);
                    selecNation = i;
                    nation = nations[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            dialog_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    items.add(new Item(et_name.getText().toString(),nation,flag_arr[selecNation],flag_arr[selecNation]));
                    dialog.cancel();
                }
            });
            dialog_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                    Toast.makeText(MainActivity.this, "작성 취소", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}