package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.yanghyeonjin.hellokotlin.databinding.ActivityMainBinding
import com.yanghyeonjin.hellokotlin.fragment.HongdroidFragment
import com.yanghyeonjin.hellokotlin.fragment.JeongdaeriFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var hongdroidFragment: HongdroidFragment
    private lateinit var jeongdaeriFragment: JeongdaeriFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // 처음 화면은 홍드로이드 탭
        hongdroidFragment = HongdroidFragment.getInstance()
        supportFragmentManager.beginTransaction().add(R.id.frameMain, hongdroidFragment).commit()

        // 탭 클릭 리스너
        binding.tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        hongdroidFragment = HongdroidFragment.getInstance()
                        supportFragmentManager.beginTransaction().replace(R.id.frameMain, hongdroidFragment).commit()
                    }
                    1 -> {
                        jeongdaeriFragment = JeongdaeriFragment.getInstance()
                        supportFragmentManager.beginTransaction().replace(R.id.frameMain, jeongdaeriFragment).commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}