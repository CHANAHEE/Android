package com.example.ex93_kotlinopenapinaversearch

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.ex93_kotlinopenapinaversearch.databinding.RecyclerItemBinding

class MyAdapter(var context:Context,var items:MutableList<ShoppingItem>) : Adapter<MyAdapter.VH>() {
    inner class VH(var binding: RecyclerItemBinding) : ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(RecyclerItemBinding.inflate(LayoutInflater.from(context),parent,false))
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: VH, position: Int) {
        var item:ShoppingItem = items[position]
        
        // 16_ 제목 글씨 중에 html 태그문이 포함되어 있어서 지저분.. 이를 제거 하자
        var title:String = HtmlCompat.fromHtml(item.title,HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
        holder.binding.tvTitle.text = title

        holder.binding.tvLowPrice.text = "${item.lprice} 원"
        holder.binding.tvMallName.text = item.mallName
        Glide.with(context).load(item.image).into(holder.binding.iv)

        holder.binding.root.setOnClickListener {
            // 17_ 디바이스의 인터넷 앱을 실행해보자.
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.link)
            context.startActivity(intent)
        }
    }
}