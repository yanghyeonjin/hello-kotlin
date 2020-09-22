package com.yanghyeonjin.hellokotlin.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yanghyeonjin.hellokotlin.util.App
import com.yanghyeonjin.hellokotlin.R
import com.yanghyeonjin.hellokotlin.listener.RecyclerViewInterface
import com.yanghyeonjin.hellokotlin.model.RecyclerViewModel
import kotlinx.android.synthetic.main.item_recycler_view.view.*

class RecyclerViewCustomViewHolder(itemView: View, recyclerViewInterface: RecyclerViewInterface) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    companion object {
        const val TAG: String = "로그"
    }

    private val tvUserName = itemView.tvUserName
    private val ivProfile = itemView.ivProfile

    private var recyclerViewInterface: RecyclerViewInterface? = null

    // 기본 생성자
    init {
        Log.e(TAG, "RecyclerViewCustomViewHolder - init() called")

        this.recyclerViewInterface = recyclerViewInterface
        itemView.setOnClickListener(this)
    }

    // 실제 데이터와 뷰를 묶는다
    fun bind(myModel: RecyclerViewModel) {
        Log.e(TAG, "RecyclerViewCustomViewHolder - bind() called")

        tvUserName.text = myModel.userName
        Glide.with(App.instance)
            .load(myModel.profileImage)
            .placeholder(R.drawable.unknown_profile) // 가져온 이미지가 없을 경우 대신 보여줄 이미지
            .into(ivProfile)
    }

    override fun onClick(p0: View?) {
        recyclerViewInterface?.onItemClicked(adapterPosition)
    }
}