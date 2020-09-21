package com.yanghyeonjin.hellokotlin.model

import android.util.Log

class MyFriend {
    companion object {
        const val TAG: String = "로그"
    }

    // 멤버 변수
    var name: String? = null
    var age: Int? = null
    var isMarried: Boolean? = null
    var nickname: String? = null

    // 기본 생성자
    // 객체를 생성하면 init() 을 타게 된다.
    init {
        Log.e(TAG, "MyFriend - init() called")

        name = "홍길동"
        age = 20
        isMarried = false
        nickname = "아버지를 아버지라 못 부르는 사나이"
    }
}