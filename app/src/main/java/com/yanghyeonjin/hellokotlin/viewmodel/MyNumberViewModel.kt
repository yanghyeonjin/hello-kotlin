package com.yanghyeonjin.hellokotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ACTION_TYPE {
    PLUS,
    MINUS
}


// 데이터의 변경
// 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있고
class MyNumberViewModel : ViewModel() {
    // 뮤터블 라이브 데이터 - 수정 가능한 녀석
    // 일반 라이브 데이터 - 읽기 전용 녀석

    companion object {
        private const val TAG: String = "로그"
    }

    // 내부에서 설정하는 자료형은 뮤터블로
    // 변경가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    // 초깃값 설정
    init {
        Log.e(TAG, "MyNumberViewModel - 생성자 호출")
        _currentValue.value = 0
    }


    // 뷰모델이 가진 값을 변경하는 함수
    fun updateValue(actionType: ACTION_TYPE, input: Int) {
        when(actionType) {
            ACTION_TYPE.PLUS -> {
                _currentValue.value = _currentValue.value?.plus(input)
            }
            ACTION_TYPE.MINUS -> {
                _currentValue.value = _currentValue.value?.minus(input)
            }
        }
    }
}