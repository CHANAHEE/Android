package com.cha.ex03_buttonevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* 2_ 액티비티가 보여주는 뷰들의 참조변수는 가급적 멤버변수의 위치에 작성하자!*/
    TextView tv;
    Button btn;
    Button sendBtn;
    EditText et;

    TextView tv2;

    // 3_ 위 참조변수는 MainActivity 가 객체로 생성될 때 메모리에 올라간다!
    @Override
    protected void onCreate(Bundle savedInstanceState) { // 4_ 이 메서드는 객체가 생성된 뒤 콜백 호출된다.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 5_ xml 언어를 통해 만든 View 객체들을 찾아와서 참조변수에 대입해주어야한다!! 이게 안드로이드의 핵심 메커니즘.
        // 원래는 언어가 다르기 때문에 xml 의 뷰들을 가져오려면 스트림을 만들어서 파일 입출력을 통해 가져와야 한다.
        // 그런데 정말 다행스럽게도, 그걸 대신해주는 메서드가 존재한다.

        // res 폴더에 물건을 가져다 놓으면, Resources 라는 객체가 장부( = R.java )에 쫘악 써놓는다. 그리고 번호를 쫘악 매겨놓는다.
        // 그래서 물건을 찾을 때 번호를 써먹는다. 근데 번호는 외우기 어려우니 대체 단어를 마련했다.
        // R class 안에 섹션별로 내부클래스가 하나씩 만들어져 있다. 그래서 이너클래스 내부에 안쪽에 변수를 하나 설정하고 변수 이름은 가져온 물건의 이름으로 작성한다. 그리고 변수의 값으로 번호를 지정한다.
        // 사실 @ 는 R 장부를 부르는 키워드 였음.
        tv = findViewById(R.id.potato_image);
        btn = findViewById(R.id.fryButton);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources res = getResources();
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.fries, null);


                tv.setBackground(drawable);            }
        };
        btn.setOnClickListener(listener);

        tv2 = findViewById(R.id.myText);
        et = findViewById(R.id.rip);
        sendBtn = findViewById(R.id.sendBtn);

        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv2.setText(et.getText() + "\n");
                et.setText(null);

            }
        };
        sendBtn.setOnClickListener(listener1);


    }


}