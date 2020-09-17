package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yanghyeonjin.hellokotlin.databinding.ActivityMainBinding
import com.yanghyeonjin.hellokotlin.fragment.HongdroidFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var hongdroidFragment: HongdroidFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // 처음 화면은 홍드로이드 탭
        hongdroidFragment = HongdroidFragment.getInstance()
        supportFragmentManager.beginTransaction().add(R.id.frameMain, hongdroidFragment).commit()
    }
}