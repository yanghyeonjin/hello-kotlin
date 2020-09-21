package com.yanghyeonjin.hellokotlin.model

import android.util.Log

// 매개변수를 가지는 친구 클래스
class MyFriendWithParams(var name: String?,
                         var age: Int?,
                         var isMarried: Boolean?,
                         var nickname: String?) {

    val TAG: String = "로그"

    var address: String? = null

    init {
        Log.e(TAG, "MyFriendWithParams - init() called")

        this.name = ""
    }


    // 추가 생성자를 만들고 싶을 때
    constructor(name: String?, age: Int?, isMarried: Boolean?, nickname: String?, address: String) : this(name, age, isMarried, nickname) {
        this.address = address
    }
}