package com.example.ex28_recyclerview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


// 8_ 제네릭을 사용해보자.
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        VH holder = new VH(itemView);
        return holder; // 9_ 여기서 리턴된 값의 타입이 VH !!! 그래서 타입 형변환을 해주지 않아도 된다~~ 편리하군.
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        // 10_ 여기서도 VH 로 홀더를 받아오니까 형변환 필요없네~

        // 11_ 현재번째 아이템 요소 얻어오기
        Item item = items.get(position);

        // 12_ VH 객체가 가지고 있는 참조변수를 이용하여 아이템의 값을 설정하자. 참조변수는 리사이클러뷰의 뷰 객체들의 참조변수 이기때문에 아이템값을 넣으면 리사이클러 뷰의 값의 설정이 가능하다.
        holder.name_tv.setText(item.name);
        holder.role_tv.setText(item.role);
        holder.civ_profile.setImageResource(item.profile);
        holder.img_iv.setImageResource(item.imgId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*  7_ 아이템뷰 1개 안에 있는 자식뷰들의 참조변수를 저장하고 있는 이너 클래스 */
    class VH extends RecyclerView.ViewHolder{

        CircleImageView civ_profile;
        TextView name_tv, role_tv;
        ImageView img_iv;
        public VH(@NonNull View itemView) {
            super(itemView);

            civ_profile = itemView.findViewById(R.id.civ_profile);
            name_tv = itemView.findViewById(R.id.name_tv);
            role_tv = itemView.findViewById(R.id.role_tv);
            img_iv = itemView.findViewById(R.id.img_iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 15_ 클릭한 아이템의 위치 인덱스 번호 얻어오기
                    int position = getLayoutPosition();
                    Item item = items.get(position);
                    Toast.makeText(context, item.name + "\n" + item.role, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
