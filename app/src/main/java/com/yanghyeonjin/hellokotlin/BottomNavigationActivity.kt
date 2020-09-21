package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yanghyeonjin.hellokotlin.databinding.ActivityBottomNavigationBinding
import com.yanghyeonjin.hellokotlin.fragment.HomeFragment
import com.yanghyeonjin.hellokotlin.fragment.MyPageFragment
import com.yanghyeonjin.hellokotlin.fragment.ShoppingCartFragment

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavigationBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var shoppingCartFragment: ShoppingCartFragment
    private lateinit var myPageFragment: MyPageFragment

    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // 처음 화면은 홈 화면으로
        // 처음엔 add, 클릭했을 땐 replace
        homeFragment = HomeFragment.getInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame, homeFragment).commit()


        // bottom navigation 클릭 리스너 셋팅
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    Log.e(TAG, "BottomNavigationActivity - home click")

                    homeFragment = HomeFragment.getInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, homeFragment).commit()

                    true
                }
                R.id.menu_shopping_cart -> {
                    Log.e(TAG, "BottomNavigationActivity - shopping cart click")

                    shoppingCartFragment = ShoppingCartFragment.getInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, shoppingCartFragment).commit()

                    true
                }
                R.id.menu_my_page -> {
                    Log.e(TAG, "BottomNavigationActivity - my page click")

                    myPageFragment = MyPageFragment.getInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, myPageFragment).commit()

                    true
                }
                else -> false
            }
        }
    }
}