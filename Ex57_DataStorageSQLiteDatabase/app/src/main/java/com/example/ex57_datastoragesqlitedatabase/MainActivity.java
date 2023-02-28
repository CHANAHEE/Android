package com.example.ex57_datastoragesqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etEmail;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etEmail = findViewById(R.id.etEmail);

        findViewById(R.id.btn_insert).setOnClickListener(view -> clickInsert());
        findViewById(R.id.btn_update).setOnClickListener(view -> clickUpdate());
        findViewById(R.id.btn_delete).setOnClickListener(view -> clickDelete());
        findViewById(R.id.btn_seleteAll).setOnClickListener(view -> clickSelectAll());
        findViewById(R.id.btn_select).setOnClickListener(view -> clickSelectByName());

        // 2_ test.db 라는 이름으로 데이터베이스 파일을 열거나 생성 하자.
        // 3_ 액티비티 클래스에 이미 이 기능이 존재함
        db = openOrCreateDatabase("test.db",MODE_PRIVATE,null);
        // 4_ 위 메소드를 실행하면, 자동으로 test.db 를 제어할 수 있는 능력을 가진 객체가 리턴된다.

        // 5_ 만들어진 혹은 열려진 DB 파일에 테이블을 생성하는 작업을 수행하자.
        // 6_ SQL 언어를 이용하여 원하는 명령어를 Database 에 수행하자.
        db.execSQL("CREATE TABLE IF NOT EXISTS member(num INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20) NOT NULL, age INTEGER, email TEXT)");

    }
    // 1_ 버튼 5개 만들어서 CRUD 작업을 해보자~~~~~ 그 전에 먼저 테이블부터 만들어야겠네!

    void clickInsert(){
        // 7_ 데이터베이스에 저장할 데이터를 먼저 가져오자.
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String email = etEmail.getText().toString();

        // 8_ member 라는 이름의 테이블에 값을 삽입하는 쿼리문을 작성하자.
        db.execSQL("INSERT INTO member (name, age, email) VALUES ('"+ name +"', '"+age+"', '"+email+"')");
    }
    void clickUpdate(){
        // 9_ 업데이트할 데이터의 이름값
        String name = etName.getText().toString();
        db.execSQL("UPDATE member SET age = 30 WHERE name = ?",new String[]{ name });
    }
    void clickDelete(){
        String name = etName.getText().toString();
        db.execSQL("DELETE FROM member WHERE name = ?",new String[]{ name });
    }
    void clickSelectAll(){
        // 10_ member 테이블의 모든 데이터들을 검색하여 가져오기
        Cursor cursor = db.rawQuery("SELECT * FROM member",null); // 11_ select 다음에는 가져올 속성을 의미한다. 즉, * 이 오면 모든 속성을 다 가져오겠다는 의미.
        // 12_ 앱에서 데이터베이스의 데이터를 사용하고자 할 때, 데이터베이스에서 직접 얻어오는게 아니라, SELECT 명령을 하면 새로운 테이블이 만들어지는데,
        // 이 새로운 테이블을 커서라고 한다. 그리고 커서를 이용하여 값을 찾아온다.
        if(cursor == null) return;
        // 13_ 커서가 null 이라는것은, 조건에 맞는게 없다는게 아니라, 명령문이 뭔가 잘못되었을 때임. 조건에 맞는게 없다면 그냥 0줄인 커서가 만들어짐.
        int row = cursor.getCount();// 14_ 총 줄(row : 레코드) 수
        cursor.moveToFirst(); // 15_ 첫번째 레코드로 이동.

        // 16_ for 문을 활용하여 값을 가져오자
        // 17_ 가져온 값을 버퍼에 넣자
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<row;i++){
            int num = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String email = cursor.getString(3);

            buffer.append(num +" " + name +" " + age + " " + email + "\n");
            // 18_ 커서를 다음 레코드로 이동시키자.
            cursor.moveToNext();
        }

        // 19_ 결과를 보여주기 위해 다이얼로그를 만들자
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(buffer.toString());
        builder.create().show();

    }
    void clickSelectByName(){
        String name = etName.getText().toString();
        Cursor cursor = db.rawQuery("SELECT name, age FROM member WHERE name = ?",new String[]{ name });
        if(cursor == null) return;

        int row = cursor.getCount();
        cursor.moveToFirst();
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<row;i++){
            String name2 = cursor.getString(0);
            int age = cursor.getInt(1);

            buffer.append(name + " " + age + "\n");
            cursor.moveToNext();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(buffer.toString());
        builder.create().show();
    }
}