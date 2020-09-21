package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.yanghyeonjin.hellokotlin.adapter.IntroAdapter
import com.yanghyeonjin.hellokotlin.databinding.ActivityIntroBinding
import com.yanghyeonjin.hellokotlin.model.IntroPage

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    private var introPageList = ArrayList<IntroPage>()
    private lateinit var introAdapter: IntroAdapter

    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 데이터 배열을 준비
        introPageList.add(IntroPage(R.color.colorOrange, R.drawable.intro_apple, "안녕하세요!\n개발하는 정대리입니다!"))
        introPageList.add(IntroPage(R.color.colorBlue, R.drawable.intro_burger, "구독, 좋아요 눌러주세요!"))
        introPageList.add(IntroPage(R.color.colorWhite, R.drawable.intro_pizza, "알람설정 부탁드립니다!"))

        // 어답터 인스턴스 설정
        introAdapter = IntroAdapter(introPageList)

        // 뷰페이저 설정
        binding.viewPagerIntro.apply {
            adapter = introAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL

            // indicator 뷰페이저2 연결
            binding.dotIndicatorIntro.setViewPager2(this)
        }

        // 뷰페이저 버튼 클릭 시 이동
        binding.btnBack.setOnClickListener {
            binding.viewPagerIntro.currentItem -= 1
        }
        binding.btnNext.setOnClickListener {
            binding.viewPagerIntro.currentItem += 1
        }
    }
}