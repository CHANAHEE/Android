package com.example.ex26_listviewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    ArrayList<String> items;
    Context context;

    public MyAdapter(Context context, ArrayList<String> items){
        this.context = context;
        this.items = items;
    }
    // 5_ 어댑터가 만들어야 할 뷰의 갯수
    @Override
    public int getCount() {
        return items.size();
    }

    // 6_ i 번째 아이템을 뽑아온다
    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // 7_ create view
        // 8_ 우리는 뷰를 재활용해야하니까 재활용할 뷰가 있는지부터 확인하자
        if( view == null ){
            // 9_ listview_item.xml 문서를 읽어와서 View 객체로 생성해주는 Inflater 객체를 소환
            LayoutInflater inflater = LayoutInflater.from(context);
            // 10_ 이 리스트뷰 아이템을 어디다가 붙일건데? 원래는 리스트뷰에 붙여야겠지.. 근데 이 어댑터의 메소드를 어댑터뷰가 리턴받는데,
            // 리턴을 받아서 어댑터뷰에서 알아서 자동으로 리스트뷰에 붙여주니까 우리는 따로 붙이면 안돼. 그래서 null 을 사용해야해.
            // 근데 null 을 넣으니 이 뷰의 크기가 얼만지를 모르네. 그래서 알아서 자동으로 wrap 으로 맞춰줘.
            // 그건 별로 안좋으니까 null 말고 viewGroup 으로 지정해주자. 근데 아까 자동으로 해주니까 하지 말라며.
            // 그래서 부모는 지정해주지만 붙이는건 우리가 안하겠다는 파라미터를 추가해줘.
            view = inflater.inflate(R.layout.listview_item, viewGroup,false);

            // 12_만들어진 뷰(view) 안에 있는 자식 뷰들의 참조값을 tag 로 저장하기.
            // 13_ 자식뷰들의 참조변수를 멤버로 가지는 별도의 클래스를 설계하여 객체로 생성
            ViewHolder holder = new ViewHolder(view);
            view.setTag(holder);
        }



        // 8_ bind view
        // 15_ 현재 보여줄 데이터 얻어오기
        String item = items.get(i);

        // 16_ 아이템뷰 안에 tag 로 저장되어 있던 ViewHolder 객체를 빼오기
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tv.setText(item);
        return view;
    }

    // 14_ 항목 1개의 뷰(item view) 안에 있는 자식뷰들 참조변수를 멤버로 가지는 이너 클래스
    class ViewHolder{
        TextView tv;

        public ViewHolder(View itemView){
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
