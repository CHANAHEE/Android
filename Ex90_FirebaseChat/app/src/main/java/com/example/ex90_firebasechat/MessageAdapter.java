package com.example.ex90_firebasechat;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.VH> {


    Context context;
    ArrayList<MessageItem> messageItems;
    final int TYPE_MY_MESSAGE = 0;
    final int TYPE_OTHER_MESSAGE = 1;

    public MessageAdapter(Context context, ArrayList<MessageItem> messageItems) {
        this.context = context;
        this.messageItems = messageItems;
    }

    // recyclerview 의 아이템뷰가 경우에 따라 다른 모양으로 보여야 할 때 사용하는 콜백메소드 : getItemViewType
    // 이 메소드에서 해당 position 에 따른 식별 값 , 즉 ViewType 번호를 정하여 리턴
    // 리턴값은 onCreateViewHolder 의 viewType 파라미터로 전달된다.
    // 그러면 onCreateViewHolder() 안에서 그 값에 따라 다른 xml 문서를 inflate 해주면 됨.

    @Override
    public int getItemViewType(int position) {
        if(messageItems.get(position).name.equals(G.nickName)) return TYPE_MY_MESSAGE;
        else return TYPE_OTHER_MESSAGE;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = null;
        if(viewType == TYPE_MY_MESSAGE){
            itemView = LayoutInflater.from(context).inflate(R.layout.my_messagebox,parent,false);
        }else{
            itemView = LayoutInflater.from(context).inflate(R.layout.other_messagebox,parent,false);
        }

        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MessageItem item = messageItems.get(position);

        holder.tvName.setText(item.name);
        holder.tvMsg.setText(item.message);
        holder.tvTime.setText(item.time);
        Glide.with(context).load(item.profileUrl).into(holder.civ);


    }

    @Override
    public int getItemCount() {
        return messageItems.size();
    }

    class VH extends RecyclerView.ViewHolder{

        // 메세지 타입에 따라 뷰가 다르기에 바인딩 클래스를 고정하지 못함.
        // MyMessageboxBinding , OtherMyMessageboxBinding ...
        // ViewHolder 를 두개 만들어 사용하기도 함
        // 근데 홀더를 두개 만들면 제네릭을 사용할 수 없으므로, onBinding 에서 분기처리가 필요하여
        // 이번에는 뷰바인딩을 쓰지 않고 만들어보자.

        CircleImageView civ;
        TextView tvName;
        TextView tvMsg;
        TextView tvTime;

        public VH(@NonNull View itemView) {
            super(itemView);
            civ = itemView.findViewById(R.id.civ);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMsg = itemView.findViewById(R.id.tv_msg);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
