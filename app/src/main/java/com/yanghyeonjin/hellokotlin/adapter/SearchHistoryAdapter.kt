package com.yanghyeonjin.hellokotlin.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yanghyeonjin.hellokotlin.R
import com.yanghyeonjin.hellokotlin.listener.SearchHistoryInterface
import com.yanghyeonjin.hellokotlin.model.SearchHistory
import com.yanghyeonjin.hellokotlin.util.Constants.TAG
import com.yanghyeonjin.hellokotlin.viewholder.SearchHistoryViewHolder

class SearchHistoryAdapter(searchHistoryInterface: SearchHistoryInterface) : RecyclerView.Adapter<SearchHistoryViewHolder>() {

    private var searchHistoryList = ArrayList<SearchHistory>()
    private var searchHistoryInterface: SearchHistoryInterface? = null

    init {
        this.searchHistoryInterface = searchHistoryInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        return SearchHistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false), this.searchHistoryInterface!!)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bindWithView(searchHistoryList[position])

        Log.e(TAG, "SearchHistoryAdapter - onBindViewHolder() called")
    }

    override fun getItemCount(): Int {
        return searchHistoryList.size
    }

    fun submitList(searchHistoryList: ArrayList<SearchHistory>) {
        this.searchHistoryList = searchHistoryList
    }
}