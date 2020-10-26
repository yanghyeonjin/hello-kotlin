package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yanghyeonjin.hellokotlin.databinding.ActivityLiveDataViewModelBinding
import com.yanghyeonjin.hellokotlin.viewmodel.ACTION_TYPE
import com.yanghyeonjin.hellokotlin.viewmodel.MyNumberViewModel

class LiveDataViewModelActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val TAG: String = "로그"
    }


    // view binding
    private lateinit var binding: ActivityLiveDataViewModelBinding

    // 나중에 값이 설정될거라고 lateinit 으로 설정
    private lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataViewModelBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        // ViewModelProvider 를 통해 뷰모델 가져오기
        // 라이프사이클을 가지고 있는 녀석을 넣어줌. 즉 자기 자신
        // 우리가 가져오고 싶은 뷰모델 클래스를 넣어서 뷰모델을 가져오기
        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        // 뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터를 옵저빙한다.
        myNumberViewModel.currentValue.observe(this, Observer { 
            Log.e(TAG, "LiveDataViewModelActivity - currentValue 라이브 데이터 값 변경: $it")
            binding.tvNumber.text = it.toString()
        })


        // 리스너 연결
        binding.btnPlus.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)
    }


    // 뷰 클릭 리스너
    override fun onClick(view: View?) {
        val userInput = binding.etOperand.text.toString().toInt()

        when(view) {
            binding.btnPlus -> {
                myNumberViewModel.updateValue(actionType = ACTION_TYPE.PLUS, userInput)
            }
            binding.btnMinus -> {
                myNumberViewModel.updateValue(actionType = ACTION_TYPE.MINUS, userInput)
            }
        }
    }
}