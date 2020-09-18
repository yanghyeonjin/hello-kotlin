package com.yanghyeonjin.hellokotlin

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yanghyeonjin.hellokotlin.databinding.ActivityLottieAnimationBinding

class LottieAnimationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLottieAnimationBinding


    private var isLiked: Boolean = false

    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLottieAnimationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // 애니메이션 full 재생
        binding.lottieViewLike.playAnimation()


        isLiked = false


        // 좋아요 버튼 클릭 리스너 달아준다
        binding.btnLike.setOnClickListener {
            Log.e(TAG, "LottieAnimationActivity - onCreate() called / 좋아요 버튼 클릭")


            if (!isLiked) {
                // 커스텀 애니메이션 재생
                // Custom animation speed or duration.
                // ofFloat: 시작지점/종료지점 (0f: 0%, 1f: 100%)
                // setDuration: 재생시간
                val animator = ValueAnimator.ofFloat(0f, 0.5f).setDuration(250)
                animator.addUpdateListener { animation: ValueAnimator ->
                    binding.btnLike.progress = animation.animatedValue as Float
                }
                animator.start()


                isLiked = true
            } else {
                // 좋아요 되어있을 때

                val animator = ValueAnimator.ofFloat(0.5f, 1f).setDuration(250)
                animator.addUpdateListener { animation: ValueAnimator ->
                    binding.btnLike.progress = animation.animatedValue as Float
                }
                animator.start()

                isLiked = false
            }


            Log.e(TAG, "LottieAnimationActivity - onCreate() called | isLiked: $isLiked")
        }
    }
}