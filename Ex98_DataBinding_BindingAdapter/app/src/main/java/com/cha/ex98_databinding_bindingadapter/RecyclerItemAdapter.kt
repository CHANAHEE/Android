package com.cha.ex98_databinding_bindingadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cha.ex98_databinding_bindingadapter.databinding.RecyclerItemBinding

class RecyclerItemAdapter(val items: List<Item>): Adapter<RecyclerItemAdapter.VH>(){

    inner class VH(val binding: RecyclerItemBinding) : ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recycler_item,parent,false))


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        // 21_ 데이터 바인딩 되어 있기에 xml 에 선언한 변수 item 에 객체값만 설정해주면 알아서 모든 뷰들에 바인딩됨.
        holder.binding.item = items[position]

        // 24_ 아이템클릭 이벤트 처리 -- 뷰 바인딩..
        holder.binding.root.setOnClickListener {
            Toast.makeText(holder.binding.root.context, "${items[position].title}", Toast.LENGTH_SHORT).show()
        }
    }
}