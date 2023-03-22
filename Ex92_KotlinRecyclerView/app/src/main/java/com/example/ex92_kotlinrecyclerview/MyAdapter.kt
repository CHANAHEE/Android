package com.example.ex92_kotlinrecyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class MyAdapter constructor(var context:Context,var items:MutableList<Item>): Adapter<MyAdapter.VH>(){


    // 15_ VH 만드는거 잘 보자. 상속받는거, 생성자 만드는거, 상속받은 뷰홀더한테 itemView 넘겨주는거 등등
    inner class VH (itemView:View) : ViewHolder(itemView){
        val tvName:TextView by lazy { itemView.findViewById(R.id.tv_name) }
        val tvMsg:TextView by lazy { itemView.findViewById(R.id.tv_msg) }
        val iv:ImageView by lazy { itemView.findViewById(R.id.iv) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.recycler_itemview,parent,false))
    }

    // 16_ return 실행문을 = 할당 연산자로 단순화
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        var item:Item = items[position]

        // 17_ 코틀린은 set, get 을 권하지 않는다!!
        holder.tvName.text = item.name
        holder.tvMsg.text = item.msg
        Glide.with(context).load(item.imgId).into(holder.iv)

        // 18_ 이제 어댑터 붙이러 가자

        // 24_ 리사이클러뷰 아이템 클릭 리스너
        holder.itemView.setOnClickListener {
            val intent:Intent = Intent(context, ItemDetailActivity::class.java)
            intent.putExtra("name",item.name)
            intent.putExtra("msg",item.msg)
            intent.putExtra("imgId",item.imgId)

            // 28_ 액티비티 전환 시에 뷰들에 효과 주기 : ActivityOptions
            // 30_ 옵션의 종류는 여러가지가 있지만, 이번 테스트에서는 장면전환을 해보자.
            // 31_ 전환효과를 줄 뷰에게 별칭을 연결, 실행될 액티비티에도 같은 별칭을 특정 뷰에게 주면 둘이 연결됨.
            val options:ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(context as MainActivity,Pair(holder.iv,"iii"))



            // 29_ 번들로 전해줘야함.
            context.startActivity(intent,options.toBundle())
            // 25_ 이제 ItemDetail 작업하러가자
        }
    }
}