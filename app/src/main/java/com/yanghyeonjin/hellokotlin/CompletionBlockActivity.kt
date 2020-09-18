package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.yanghyeonjin.hellokotlin.databinding.ActivityCompletionBlockBinding

class CompletionBlockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompletionBlockBinding


    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompletionBlockBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnCallMethod.setOnClickListener {
            someCallbackMethod(completion = {
                Log.e(TAG, "CallbackActivity - 컴플레션 블럭 호출됨 | $it")

                binding.tvCurrentStatus.text = "정상적으로 처리되었습니다."
            })
        }
    }

    // () -> Unit: 파라미터도 없고 리턴값도 없는 function
    private fun someCallbackMethod(completion: (String) -> Unit) {
        Log.e(TAG, "CallbackActivity - someCallbackMethod() called")

        binding.tvCurrentStatus.text = "처리중..."

        // postDelayed(callback, delay): 콜백, 지연시간을 파라미터로 넘겨줌
        Handler().postDelayed({
            completion("하하 끝났다.")
        }, 3000)
    }
}