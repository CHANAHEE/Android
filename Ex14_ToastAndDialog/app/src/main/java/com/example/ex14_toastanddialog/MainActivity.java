package com.example.ex14_toastanddialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn01;
    Button btn02;

    int selectedItemPosition = 1;

    TextView dialogTv;
    Button dialogBtn;
    // 11_ 다이얼로그가 보여줄 목록(리스트)의 항목들.. 애플 바나나 오렌지를 목록으로 만들겠다!
    String[] items = new String[]{ "Apple" , "Banana" , "Orange"};
    // 21_ boolean 배열 선언!
    boolean[] checkedItems = new boolean[] { true , false , true };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btn01 = findViewById(R.id.btn01);
        btn02 = findViewById(R.id.btn02);

        // 2_ 버튼을 누르면 토스트를 띄워준다.
//        btn01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 3_ 익명 클래스 안쪽이기 때문에 여기서 this 를 사용하면, 익명클래스를 지칭하게 된다.
//                Toast.makeText(MainActivity.this,"통키",Toast.LENGTH_SHORT).show();
//            }
//        });

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 5_ AlertDialog를 만들어주는 건축가(Builder) 객체를 생성하자.
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//

//                // 6_ 건축가에게 원하는 모양을 의뢰
//                builder.setTitle("다 이얼로그");
//                builder.setIcon(R.mipmap.ic_launcher);

                // 12_ 단순 메시지 1개를 보여줄때
                // builder.setMessage("Do you wanna Quit?");
                // 13_ 목록으로 항목을 보여줄 때.
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//                    // 14_ 리스트를 선택할 때의 리스너를 만들자!
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // 15_ 두번째 파라미터 i : 클릭된 항목의 위치 인덱스 번호! 시작 숫자가 0 이다!
//                        Toast.makeText(MainActivity.this,items[i],Toast.LENGTH_SHORT).show();
//                    }
//                });
                // 16_ 근데 리스트로 하니까 실수로 잘못누를수도 있고, 누르면 바로 선택되서 별로다. 그러니 다음걸 보자.

                // 17_ Single choice 항목들 보여줄 때...
//                builder.setSingleChoiceItems(items, selectedItemPosition, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        selectedItemPosition = i;
//                    }
//                });

                // 20_ Multiple choice 항목들 보여줄 때,.
//                builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
//                        // 22_ 두번째 파라미터 i : 클릭된 항목의 위치 인덱스 번호
//                        // 23_ 세번째 파라미터 b : 클릭된 항목의 true/false 값 여부
//                        checkedItems[i] = b;
//                    }
//                });

                // 25_ Custom View 항목을 보여줄 때...
                // 직접 자바로 뷰들을 만들어서 설정하면 지저분하기 그지 없다.
                // xml 언어로 뷰를 설계하면 편하게 적용가능하다.
                // res 폴더안에 layout 폴더 안에 dialog.xml 파일에 다이얼 로그의 커스텀 뷰의 모양을 설계하자.
                builder.setView(R.layout.dialog);
//
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // 18_ Toast.makeText(MainActivity.this,"OK 버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
//                        // 19_ SingleChoice 를 통해 선택한 아이템 문자열 출력해보기. 위에서 라디오 버튼을 선택했을 때와 OK 버튼을 누르는 영역이 다르기 때문에
//                        // 멤버변수를 통해 값을 저장해와서 출력할 때 인덱스로 활용해주어야 한다.
//                        // Toast.makeText(MainActivity.this,items[selectedItemPosition],Toast.LENGTH_SHORT).show();
//
//                        // 24_ Multiple choice 를 통해 선택된 아이템들의 문자열을 출력해보기
////                        String s = "";
////                        for(int k = 0; k < items.length ; k++) {
////                            if(checkedItems[k]) s += items[k] + " ";
////                        }
////
////                        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
//                    }
//                });

//                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(MainActivity.this,"CANCEL 버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
//                    }
//                });

                // 7_ 건축가에게 의뢰(설정)한대로 다이얼로그를 만들어달라고 요청
                AlertDialog dialog = builder.create();

                // 9_ 다이얼로그의 바깥쪽을 클릭했을 때 없어지지 않도록 해보자.
                dialog.setCanceledOnTouchOutside(false);
                // 10_ 다이얼로그 버튼이 아니면 어떤 방법으로도 취소 못하게
                dialog.setCancelable(false);
                // 8_ 다이얼로그 보여주기
                dialog.show();

                // 27_ 다이얼로그 안에 있는 Custom View 의 뷰들을 찾아서 제어하기
                dialogTv = dialog.findViewById(R.id.dialogTv);
                dialogBtn = dialog.findViewById(R.id.dialogBtn);
                dialogBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogTv.setText("Good!");
                    }
                });
            }
        });
    }
}