package com.yanghyeonjin.hellokotlin.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yanghyeonjin.hellokotlin.listener.SearchHistoryInterface
import com.yanghyeonjin.hellokotlin.model.SearchHistory
import com.yanghyeonjin.hellokotlin.util.Constants.TAG
import kotlinx.android.synthetic.main.item_search_history.view.*

class SearchHistoryViewHolder(itemView: View, searchHistoryInterface: SearchHistoryInterface): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val constraintSearchHistory = itemView.constraintSearchHistory
    private var tvSearchTerm = itemView.tvSearchTerm
    private var tvTimestamp = itemView.tvTimestamp
    private val btnDeleteHistory = itemView.btnDeleteHistory
    private var searchHistoryInterface: SearchHistoryInterface

    init {
        // 리스너 연결
        constraintSearchHistory.setOnClickListener(this)
        btnDeleteHistory.setOnClickListener(this)

        this.searchHistoryInterface = searchHistoryInterface
    }

    fun bindWithView(searchHistory: SearchHistory) {
        tvSearchTerm.text = searchHistory.term
        tvTimestamp.text = searchHistory.timestamp
    }

    override fun onClick(view: View?) {
        when(view) {
            btnDeleteHistory -> {
                Log.e(TAG, "검색기록 삭제가 클릭되었다.")
                this.searchHistoryInterface.onSearchItemDeleteClicked(adapterPosition)
            }
            constraintSearchHistory -> {
                Log.e(TAG, "검색어가 클릭되었다.")
                this.searchHistoryInterface.onSearchItemClicked(adapterPosition)
            }
        }
    }
}