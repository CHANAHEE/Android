package com.example.ex27_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// 9_ 새로운 어댑터뷰인만큼 어댑터도 새롭게 리사이클러뷰의 어댑터를 사용하자!
public class MyAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    //14_ 재활용할 뷰가 없으면 뷰를 만들기 위해 자동으로 호출되는 메소드
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
/*        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recyclerview_item,parent,false); // 16_ parent 는 여기서는 리사이클러뷰를 의미! 그리고 붙이지마! 자동으로 붙여주니까.
        VH viewHolder = new VH(itemView);*/
        VH viewHolder = new VH(LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false));
        return viewHolder; // 17_ 여기서 리턴된 값이 onBindViewHolder 메서드의 holder 로 넘어간다.
    }

    // 15_ 현재 연결할 번째(position) 데이터를 뷰에 넣어주는 작업을 위해 자동으로 호출되는 메소드
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 18_ 첫번째 파라미터 holder 가 가진 뷰들의 참조변수를 통해 값을 설정해주자.
        VH vh = (VH)holder; // 19_ 부모 참조변수로는 자식 고유 기능과 멤버변수를 사용할 수 없으니 다운캐스팅하자.
        // 20_ 현재번째 아이템 요소를 얻어와서 뷰들에게 값을 설정하자!
        vh.recycler_item_name.setText(items.get(position).name);
        vh.recycler_item_message.setText(items.get(position).message);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // 10_ 아이템 1개 뷰 안에 있는 자식뷰들의 참조값을 저장하는 참조변수들을 멤버로 가지는 이너 클래스!
    class VH extends RecyclerView.ViewHolder {

        TextView recycler_item_name;
        TextView recycler_item_message;
        // 11_ 생성자 중에 빈놈이 없다.
        public VH(@NonNull View itemView) {
            super(itemView); //  12_ 요고는 해야 한다. 그래야 뷰홀더 시스템이 가동된다.

            recycler_item_name = itemView.findViewById(R.id.recycler_item_name);
            recycler_item_message = itemView.findViewById(R.id.recycler_item_message);
            // 13_ 이러면 준비끗~~ 이제 만들어보자


            // 24_ 항목뷰를 클릭했을때 반응하는 리스너 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 25_ 클릭한 아이템의 위치 인덱스 번호
                    int position = getLayoutPosition();

                    // 26_ 클릭한 번째 아이템 요소의 데이터를 얻어오자
                    items.get(position);
                    Toast.makeText(context, "clicked : " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
