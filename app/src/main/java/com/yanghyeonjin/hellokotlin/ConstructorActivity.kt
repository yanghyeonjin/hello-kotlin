package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yanghyeonjin.hellokotlin.databinding.ActivityConstructorBinding
import com.yanghyeonjin.hellokotlin.model.MyFriend
import com.yanghyeonjin.hellokotlin.model.MyFriendFlexible
import com.yanghyeonjin.hellokotlin.model.MyFriendWithParams

class ConstructorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConstructorBinding


    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstructorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // 기본 생성자
        val myFriend = MyFriend()
        binding.btnBasicConstructor.setOnClickListener {
            binding.tvName.text = myFriend.name
            binding.tvAge.text = myFriend.age.toString()
            binding.tvIsMarried.text = myFriend.isMarried.toString()
            binding.tvNickname.text = myFriend.nickname
        }


        // 매개변수가 들어가는 객체 생성
        val myFriend2 = MyFriendWithParams("영수", 20, false, "국영수", "서울특별시 성북구")
        binding.btnConstructorWithParams.setOnClickListener {
            binding.tvName.text = myFriend2.name
            binding.tvAge.text = myFriend2.age.toString()
            binding.tvIsMarried.text = myFriend2.isMarried.toString()
            binding.tvNickname.text = myFriend2.nickname
        }

        // 더 유연한 객체 생성
        val myFriendFlex = MyFriendFlexible("제임스")
        binding.btnFlexibleConstructor.setOnClickListener {
            binding.tvName.text = myFriendFlex.name
        }
    }
}