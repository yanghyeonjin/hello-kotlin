package com.yanghyeonjin.hellokotlin.customview

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.yanghyeonjin.hellokotlin.databinding.DialogCustomBinding
import com.yanghyeonjin.hellokotlin.listener.CustomDialogInterface

class CustomDialog(context: Context, customDialogInterface: CustomDialogInterface): Dialog(context) {

    companion object {
        const val TAG: String = "로그"
    }

    private lateinit var binding: DialogCustomBinding

    // 클릭을 알려줄 수 있는 인터페이스
    private var customDialogInterface: CustomDialogInterface? = null

    init {
        this.customDialogInterface = customDialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCustomBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // 배경 투명
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        binding.btnSubscribe.setOnClickListener {
            this.customDialogInterface?.onSubScribeClicked()
        }
        binding.btnLike.setOnClickListener {
            this.customDialogInterface?.onLikeClicked()
        }
    }
}