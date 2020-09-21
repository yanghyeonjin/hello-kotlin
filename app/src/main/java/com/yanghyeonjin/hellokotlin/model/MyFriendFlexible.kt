package com.yanghyeonjin.hellokotlin.model

// 추가 생성자로 되어있는 친구 클래스
class MyFriendFlexible {

    // 멤버변수
    var name: String? = null
    var age: Int? = null
    var isMarried: Boolean? = null
    var nickname: String? = null


    init {

    }

    constructor(name: String) {
        this.name = name
    }

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}