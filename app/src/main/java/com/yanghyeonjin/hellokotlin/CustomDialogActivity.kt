package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yanghyeonjin.hellokotlin.customview.CustomDialog
import com.yanghyeonjin.hellokotlin.databinding.ActivityCustomDialogBinding
import com.yanghyeonjin.hellokotlin.listener.CustomDialogInterface

class CustomDialogActivity : AppCompatActivity(), CustomDialogInterface {
    private lateinit var binding: ActivityCustomDialogBinding

    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomDialogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnShowDialog.setOnClickListener {
            val customDialog = CustomDialog(this, this)
            customDialog.show()
        }
    }

    override fun onSubScribeClicked() {
        Toast.makeText(this, "구독 버튼이 클릭되었습니다!", Toast.LENGTH_SHORT).show()
    }

    override fun onLikeClicked() {
        Toast.makeText(this, "좋아요 버튼이 클릭되었습니다!", Toast.LENGTH_SHORT).show()
    }
}