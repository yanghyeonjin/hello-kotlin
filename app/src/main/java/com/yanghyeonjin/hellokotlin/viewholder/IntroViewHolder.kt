package com.yanghyeonjin.hellokotlin.viewholder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yanghyeonjin.hellokotlin.R
import com.yanghyeonjin.hellokotlin.model.IntroPage
import kotlinx.android.synthetic.main.item_intro_page.view.*

class IntroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val ivIntroImage = itemView.ivIntroImage
    private val tvIntroText = itemView.tvIntroText
    private val layoutIntroBg = itemView.layoutIntroBg

    fun bindWithView(introPage: IntroPage) {
        ivIntroImage.setImageResource(introPage.imageSrc)
        tvIntroText.text = introPage.content
        layoutIntroBg.setBackgroundResource(introPage.bgColor)

        // 배경색이 흰색이 아닌 경우에만 글자색 바꾸기
        if (introPage.bgColor != R.color.colorWhite) {
            tvIntroText.setTextColor(Color.WHITE)
        }

    }
}