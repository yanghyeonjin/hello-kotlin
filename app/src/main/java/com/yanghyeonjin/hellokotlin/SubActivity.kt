package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yanghyeonjin.hellokotlin.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (intent.hasExtra("msg")) {
            // 넘어온 인텐트에 msg가 있다면
            binding.tvMsg.text = intent.getStringExtra("msg")
        }

        binding.btnGoToBack.setOnClickListener {
            finish() // 자기 자신 액티비티를 파괴한다.
        }
    }
}