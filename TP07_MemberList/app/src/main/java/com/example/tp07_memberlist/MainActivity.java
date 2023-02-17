package com.example.tp07_memberlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    int profile;
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
        /*
        *       어댑터로 리스트뷰에 아이템 추가하기
        * */
        listView = findViewById(R.id.listview);
        adapter = new MyAdapter(this,items);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.popup,popupMenu.getMenu());

                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if(id == R.id.menu_delete){
                            items.remove(position);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                return false;
            }
        });




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



    /*
    *
    *       서치 옵션 메뉴
    *
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("이름을 검색하세요");

        /*
        *
        *   서치뷰 이벤트
        *
        * */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*
                *
                *       검색된 이름(query) 과 아이템뷰의 name 을 비교해서 검색.
                *
                * */
                for(int i = 0;i<items.size();i++){
                    if(query.equals(items.get(i).name)){
                        listView.setSelection(i);
                    }
                }
                searchView.clearFocus();

                /*
                *
                *       검색 후 다시 검색할 때 검색어 초기화
                *
                * */
                searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        searchView.setQuery("",true);
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    /*
    *
    *       추가 옵션 메뉴
    *
    * */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        frameLayout = findViewById(R.id.framelayout);

        if(item.getItemId() == R.id.menu_add){
            frameLayout.setVisibility(View.GONE);
            /*
            *       다이얼로그 만들기
            * */
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

            String[] nations = getResources().getStringArray(R.array.nation);

            /*
            *       스피너 아이템 선택 이벤트 : 선택하면 현재 선택한 나라의 인덱스 번호를 멤버변수에 저장하고, 나라 이름을 문자열 변수에 저장.
            * */
            sp_nation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selecNation = i;
                    nation = nations[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            /*
            *
            *      다이얼 로그에서 ADD 버튼 클릭시 ArrayList 에 아이템 추가한 뒤 다이얼로그 종료, 추가한 순서가 아닌 최근 추가가 맨 위로 오게 코드 작성
            *
            * */
            dialog_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*
                    *       현재 날짜 및 시간 표기 SimpleDateFormat 클래스 활용
                    * */
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd   hh:mm", Locale.getDefault());
                    String current = format.format(System.currentTimeMillis());

                    int checkedId = rg_gender.getCheckedRadioButtonId();
                    RadioButton rb = dialog.findViewById(checkedId);
                    if(rb.getText().toString().equals("MALE")){
                        profile = R.drawable.male;
                    } else if(rb.getText().toString().equals("FEMALE")){
                        profile = R.drawable.female;
                    }


                    items.add(0,new Item(et_name.getText().toString(),nation,current,flag_arr[selecNation],profile));
                    dialog.cancel();
                }
            });

            /*
            *       다이얼로그 종료
            * */
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