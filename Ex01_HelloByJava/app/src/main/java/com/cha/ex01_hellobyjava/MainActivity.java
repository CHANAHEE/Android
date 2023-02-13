package com.cha.ex01_hellobyjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 5_ 화면에 보이는 뷰들의 참조변수는 가급적 멤버변수로 만들자!
    TextView tv;
    Button btn;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        // 1_ 자바 언어만으로 화면을 꾸며보자.
        // 2_ 액티비티는 뷰 클래스의 능력을 가진(상속한) 클래스들만 보여줄 수 있음.

        // 3_ 글씨를 보여주는 TextView 객체로 생성 및 설정을 해보자.
        // 3_1 모든 뷰의 객체를 생성할 때, context 로 this 를 넘겨주어야 한다.
        tv = new TextView(this);

        // 4_ 객체의 메소드 사용하기
        tv.setText("Hello World");

        // 6_ 액티비티에 tv 를 붙이기
        setContentView(tv);

        // 7_ 버튼기능을 가진 객체를 생성하고 설정해보자
        btn = new Button(this);
        btn.setText("버튼");
        setContentView(btn);

        // 8_ TextView 가 안보인다. 스윙처럼 겹쳐진걸까?
        // 아니다. TextView 를 뜯어내고 버튼을 붙인거다. 명령어를 보자. setContentView 는 말그대로 뷰를 set 하는것이고, 스윙에서는 컴포넌트를 추가할때, add 명령어를 사용한다.
        // add 한다는것은 컴포넌트를 추가 한다는 의미이고, set 한다는것은 세팅한다는 느낌.
        // 즉, 액티비티는 한번에 1개의 뷰만 놓여질 수 있다는 특징이 있음. 그치만 액티비티에 하나의 뷰만 넣는것은 뭔가 비효율적인느낌이다 그럼 어떻게 해야하지?
        // 그래서 하나의 큰 뷰를 설정하고 그 큰 뷰위에 다른 작은 뷰를 놓아보자! 이 큰 뷰를 ViewGroup 이라고 한다. 그 중 대표적인 ViewGroup 이 Layout! 레이아웃에 관해서는 차차 알아보자.


        // 9_ ViewGroup 중 가장 간단한 LinearLayout 이라는 이름의 클래스를 객체로 만들어서 사용
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL); // 12_ 리니어 레이아웃 방향 변경

        // 10_ 레이아웃에게 뷰들을 추가하기
        layout.addView(tv);
        layout.addView(btn);

        // 11_ 액티비티에 이 뷰그룹을 붙이자. setContentView 여러번 하면 에러!
        //setContentView(layout);

        // 13_ 버튼 클릭했을 때 TextView 의 글씨를 변경해보자!
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 14_ 이 리스너가 감시하는 버튼이 클릭되었을 때 자동으로 발동하는 메소드 영역
                tv.setText("Nice to meet you");
            }
        };
        btn.setOnClickListener(listener);
    }
}
        // 15_ 가만히 쳐다보니 액티비티에 버튼이 몇개있는지, 레이아웃이 몇갠지 한눈에 잘 들어오지 않는다. 자바로 화면을 구성을 할 수는 있지만 복잡한 앱에서는 �