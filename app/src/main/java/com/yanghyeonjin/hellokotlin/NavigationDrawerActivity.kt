package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.yanghyeonjin.hellokotlin.databinding.ActivityNavigationDrawerBinding

class NavigationDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityNavigationDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 네비게이션 오픈
        binding.btnOpenNavigation.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.START)
        }

        // 네비게이션 메뉴 아이템에 클릭 속성 부여
        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // 네비게이션 메뉴 아이템 클릭 시 수행
        when (item.itemId) {
            R.id.menu_home -> binding.tvPageTitle.text = getString(R.string.menu_home)
            R.id.menu_search -> binding.tvPageTitle.text = getString(R.string.menu_search)
            R.id.menu_my_page -> binding.tvPageTitle.text = getString(R.string.menu_my_page)
        }

        binding.drawer.closeDrawers() // 펼쳐져있는 네비게이션 닫기

        return false
    }

    override fun onBackPressed() {
        // 네비게이션 뷰가 열려있으면 닫기
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawers()
        } else {
            super.onBackPressed() // 일반 Back 버튼 기능 실행
        }
    }
}