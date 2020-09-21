package com.yanghyeonjin.hellokotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yanghyeonjin.hellokotlin.R
import com.yanghyeonjin.hellokotlin.model.IntroPage
import com.yanghyeonjin.hellokotlin.viewholder.IntroViewHolder

class IntroAdapter(private var pageList: ArrayList<IntroPage>) : RecyclerView.Adapter<IntroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        return IntroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_intro_page, parent, false))
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bindWithView(pageList[position])
    }

    override fun getItemCount(): Int {
        return pageList.size
    }
}