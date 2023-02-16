package com.example.ex25_listviewcustom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(ArrayList<Item> items,Context context){
        this.items = items;
        this.context = context;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // 리스트 뷰가 보여줄 아이템 1개의 뷰 객체를 생성하여 리턴해주는 메서드
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // 1. create view : xml 모양으로 View 객체를 생성
        // 혹시 재활용할 view 가 없는가? 이 메소드의 두번째 파라미터 view
        if( view == null ){ // 객체를 참조하는게 아니니까 올라간놈이 없는거임. 올라가면 이제 이 view 파라미터로 올라간놈의 view 가 전달됨.
            // xml 파일을 읽어서 뷰 객체로 만들어 주는 객체를 운영체제로 부터 소환.
            // 원래는 다음같이 사용해야함.
            // LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.listview_item,null);

        }
        // 2. bind view : 생성된 뷰 객체 안에 정보들을 설정(연결)
        // 이 메소드의 첫번째 파라미터 i : 현재 만들어야할 인덱스

        // 현재번째 데이터(Item 객체) 얻어오기
        Item item = items.get(i);

        // 아이템뷰 안에 있는 자식뷰들을 참조하자.
        ImageView iv = view.findViewById(R.id.imgFlag);
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvNation = view.findViewById(R.id.tv_nation);

        // 찾아왔으니 각 뷰들에 현재번째 데이터를 연결
        tvName.setText(item.name);
        tvNation.setText(item.nation);
        iv.setImageResource(item.imgId);

        return view; // 리스트 뷰가 이 리턴된 뷰를 화면에 목록으로 추가해줌.
        // 여기까지 해서 시안대로 뷰를 만들기만 했음. 이제 리스트뷰에 붙이는 작업을 해보자./
    }
}
