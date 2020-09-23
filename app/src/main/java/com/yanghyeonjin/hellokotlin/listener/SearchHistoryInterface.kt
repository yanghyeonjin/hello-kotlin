package com.yanghyeonjin.hellokotlin.listener

interface SearchHistoryInterface {
    fun onSearchItemClicked(position: Int)
    fun onSearchItemDeleteClicked(position: Int)
}