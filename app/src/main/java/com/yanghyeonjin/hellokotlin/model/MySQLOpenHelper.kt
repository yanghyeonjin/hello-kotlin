package com.yanghyeonjin.hellokotlin.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySQLOpenHelper(context: Context): SQLiteOpenHelper(context, "MyDB", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}