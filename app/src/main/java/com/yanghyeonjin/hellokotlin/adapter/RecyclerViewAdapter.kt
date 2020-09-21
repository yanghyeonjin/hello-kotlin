package com.yanghyeonjin.hellokotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yanghyeonjin.hellokotlin.R
import com.yanghyeonjin.hellokotlin.listener.RecyclerViewInterface
import com.yanghyeonjin.hellokotlin.model.RecyclerViewModel
import com.yanghyeonjin.hellokotlin.viewholder.RecyclerViewCustomViewHolder

class RecyclerViewAdapter(recyclerViewInterface: RecyclerViewInterface) : RecyclerView.Adapter<RecyclerViewCustomViewHolder>() {

    private var modelList = ArrayList<RecyclerViewModel>()
    private var recyclerViewInterface: RecyclerViewInterface? = null

    // 생성자
    init {
        this.recyclerViewInterface = recyclerViewInterface
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewCustomViewHolder {
        return RecyclerViewCustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false), this.recyclerViewInterface!!)
    }

    // 뷰와 뷰홀더가 묶였을 때
    override fun onBindViewHolder(holder: RecyclerViewCustomViewHolder, position: Int) {
        holder.bind(modelList[position])

        // 클릭 리스너 장착 방법 1
        // 비용이 많이 드는 방법이다 -> 인터페이스 활용
//        holder.itemView.setOnClickListener {
//            Toast.makeText(App.instance, "클릭됨! ${modelList[position].userName}", Toast.LENGTH_SHORT).show()
//        }
    }

    // 아이템 갯수
    override fun getItemCount(): Int {
        return modelList.size
    }

    fun submitList(modelList: ArrayList<RecyclerViewModel>) {
        this.modelList = modelList
    }
}