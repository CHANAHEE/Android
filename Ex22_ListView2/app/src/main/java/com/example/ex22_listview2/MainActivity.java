package com.example.ex22_listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    // 2_ 대량의 데이터를 저장할 리스트 객체
    ArrayList<String> datas = new ArrayList<>();
    ListView listView;

    // 7_ 대량의 데이터를 적절한 뷰 객체로 만들어주는 Adapter 객체의 참조변수를 만들자.
    // 원래 어댑터를 우리식대로 만들 수 있다. 지금은 안드로이드에서 만들어주느 어레이 어댑터를 사용해보자.
    // 8_ 어레이 어댑터는 대량의 데이터가 String 이고 배열일때만 사용할 수 있다.
    ArrayAdapter adapter;

    EditText et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1_ 앞서 arrays.xml 에 배열을 만들어서 리스트뷰를 만들면,
        // 고정적인 값 밖에 사용할 수 없다.

        // 4_ 대량의 데이터를 추가
        datas.add(new String("aaa"));
        datas.add("bbb");
        datas.add("ccc");

        // 9_ 어댑터 객체를 생성하자.
        // 파라미터로 컨택스트, 우리가 만든 시안, 대량의 데이터를 넘겨주자.
        adapter = new ArrayAdapter(this,R.layout.listview_item,datas);

        // 10_ 그리고 우리는 리스트 뷰를 여기 자바에서 제어해주어야 한다.
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        // 11_ 끝~~~ 리스트뷰 완성!

        // 12_ 리스트의 항목을 클릭했을 때 반응해보자.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, datas.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        // 17_ 리스트의 아이템을 롱~~~~~~~~ 클릭 했을때 반응하기 - 데이터 삭제를 해보자!!
        // 컨택스트 메뉴 처럼 해도 되겠지만, 이러면 아이템 하나하나에 추가해줘야한다. 그러니 아래처럼 사용해보자.
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, "long", Toast.LENGTH_SHORT).show();
                // 19_ 대량의 데이터 datas 리스트 객체에서 현재 롱클릭한 아이템의 위치요소를 제거
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.menu_delete){
                            datas.remove(i);
                            adapter.notifyDataSetChanged();
                        }
                        return false;
                    }
                });
                return true;
            }
        });
        // 18_ 클릭이라는 행위가 무엇이냐! 손가락을 누르고 떼는 행위이다!
        // 그래서 롱클릭을 했을때, 즉 꾸욱 눌렀을 때 토스트메시지로 long 이 뜨고, 손가락을 똇을때 저 위쪽 토스트메시지가 뜬다.
        // 근데 이게 문제가 될 수 있다. 롱클릭을 하면 반드시 클릭이 되니까!!
        // 여기서 봐야할게 return 이다. false 이면 이벤트를 끝내겠느냐 물어봤는데 아니요 라고 대답한것.
        // true 이면 이벤트를 여기서 끝내겠다는 의미. 즉, 롱클릭만 발동되고 클릭은 발동되지 않는다. 영어로 컨슘했다 라고 한다.





        // 14_ EditText의 글씨를 얻어와서 대량의 데이타인 datas에 변경을 시도해보자
        // 헷갈리지 말아야 할게, 리스트뷰는 어댑터가 보여주는 화면임. 즉, 리스트뷰에 직접적으로 추가를 하는게 아니라,
        // 어댑터에게 전달했던 대량의 데이터를 변경하면 그 데이터가 어댑터로 전달되고, 다시 리스트뷰로 전달되는 구조임.
        // 리스트뷰는 뷰그룹이지만, xml 을 통해 리스트뷰 안에 직접적으로 뷰를 집어넣으면 에러! 왜? 어댑터가 넣어줘야 하니까!
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = et.getText().toString();
                datas.add(data);
                et.setText("");
                // 15_ 근데 이렇게 하면 갱신이 안됨. 왜? 어댑터에게 시안과 데이터를 전달했을때는 시작했을 때고, 버튼을 누르면 대량의 데이터는 변경이 되지만,
                // 어댑터는 그 사실을 모르고 있음. 그렇기때문에 어댑터에게 데이터가 변경되었다고 알려줘야 한다.
                adapter.notifyDataSetChanged();

                // 16_ 근데 데이터가 추가될때 스크롤이 고정되있지 않고 새로 만든 데이터쪽으로 움직여줬으면 좋겠다.
                listView.setSelection(datas.size()-1);
            }
        });
    }
}