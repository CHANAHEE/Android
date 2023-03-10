package com.example.ex79_databinding;

import android.database.Observable;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class User {
    // 10_ 일반 자료형은 값이 변경되어도 화면갱신이 이루어지지 않는다!!!
    // data Binding 기능에 의해 변수값이 바뀌면 자동 갱신되는 특별한 자료형이 있다.
    // ObservableXXX 클래스 객체
    public ObservableField<String> name = new ObservableField<>(); // 3_ xml 과 이 User 클래스는 서로 다른 패키지 이기 때문에, 데이터바인딩을 위해서 public 으로 지정해주어야 한다.
    public ObservableInt age = new ObservableInt();
    public ObservableBoolean fav = new ObservableBoolean();
    // 11_ Obserbable 참조변수 set 해서 데이터 넣기
    public User(String name,int age,boolean fav) {
        this.name.set(name);
        this.age.set(age);
        this.fav.set(fav);
    }
    // 7_ 버튼클릭시에 실행될 콜백메소드 : 왜 여기? 바꾸려는 변수가 여기 클래스가 있으니까.

    // 8_ 리스너의 콜백 메소드에 있는 파라미터가 반드시 똑같이 있어야만 함.
    public void changeName(View view){
        this.name.set("robin"); // 9_ 근데 안바뀐다?
    }

    // 12_ 버튼 클릭 이벤트 처리하기
    public void increaseAge(View view){
        this.age.set(this.age.get()+1);
    }
}
