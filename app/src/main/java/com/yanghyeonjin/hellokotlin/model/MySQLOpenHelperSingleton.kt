package com.yanghyeonjin.hellokotlin.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySQLOpenHelperSingleton private constructor(context: Context): SQLiteOpenHelper(context, "MyDB", null, 1) {

    companion object {
        const val TAG: String = "로그"
        // 자기 자신 변수 선언
        // @Volatile: 값을 가져올 때 캐시를 거치지 않고 메모리를 참조한다. 항상 최신 값을 가져올 수 있음.
        @Volatile private var instance: MySQLOpenHelperSingleton? = null

        // 자기 자신 가져오기
        fun getInstance(context: Context): MySQLOpenHelperSingleton {
            return instance ?: synchronized(this) {
                instance ?: MySQLOpenHelperSingleton(context).also {
                    instance = it
                }
            }
        }
    }

    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}