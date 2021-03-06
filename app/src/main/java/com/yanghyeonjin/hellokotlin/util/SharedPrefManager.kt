package com.yanghyeonjin.hellokotlin.util

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.yanghyeonjin.hellokotlin.model.SearchHistory
import com.yanghyeonjin.hellokotlin.util.Constants.TAG

object SharedPrefManager {
    private const val SHARED_SEARCH_HISTORY = "SHARED_SEARCH_HISTORY"
    private const val KEY_SEARCH_HISTORY = "keySearchHistory"

    private const val SHARED_SEARCH_HISTORY_MODE = "SHARED_SEARCH_HISTORY_MODE"
    private const val KEY_SEARCH_HISTORY_MODE = "KEY_SEARCH_HISTORY_MODE"


    private const val DEFAULT_STRING = ""
    private const val DEFAULT_BOOLEAN = false


    // 저장모드 저장하기
    fun setSearchHistoryMode(isActivated: Boolean) {
        val sharedPref = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY_MODE, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(KEY_SEARCH_HISTORY_MODE, isActivated)
        editor.apply()
    }

    // 현재 저장모드 가져오기
    fun getSearchHistoryMode() : Boolean {
        val sharedPref = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY_MODE, Context.MODE_PRIVATE)
        val isActivated = sharedPref.getBoolean(KEY_SEARCH_HISTORY_MODE, DEFAULT_BOOLEAN)

        return isActivated
    }





    // 검색 목록 저장
    fun saveSearchHistoryList(searchHistoryList: MutableList<SearchHistory>) {
        Log.e(TAG, "SharedPrefManager - saveSearchHistoryList() called")

        // 매개변수로 들어온 배열을 -> 문자열로 변환
        val searchHistoryListString = Gson().toJson(searchHistoryList)
        Log.e(TAG, "searchHistoryListString: $searchHistoryListString")

        // 쉐어드 프리퍼런스에 저장하기
        val sharedPref = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(KEY_SEARCH_HISTORY, searchHistoryListString)
        editor.apply()
    }

    // 검색 목록 가져오기
    fun getSearchHistoryList() : MutableList<SearchHistory> {
        val sharedPref = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY, Context.MODE_PRIVATE)
        val searchHistoryListString = sharedPref.getString(KEY_SEARCH_HISTORY, DEFAULT_STRING)!! // 문자열임

        // 저장된 검색어가 있을 때
        // 문자열 -> MutableList로
        var arrayList = ArrayList<SearchHistory>()

        if (searchHistoryListString.isNotEmpty()) {
            arrayList = Gson().fromJson(searchHistoryListString, Array<SearchHistory>::class.java)
                .toMutableList() as ArrayList<SearchHistory>
        }


        return arrayList
    }

    // 검색목록 모두 지우기
    fun clearSearchHistoryList() {
        val sharedPref = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}