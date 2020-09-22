package com.yanghyeonjin.hellokotlin.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yanghyeonjin.hellokotlin.R
import com.yanghyeonjin.hellokotlin.model.Photo
import com.yanghyeonjin.hellokotlin.util.App
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val ivThumbnail = itemView.ivThumbnail
    private val tvCreatedAt = itemView.tvCreatedAt
    private val tvLikes = itemView.tvLikes

    fun bindWithView(photoItem: Photo) {
        tvCreatedAt.text = photoItem.createdAt
        tvLikes.text = photoItem.likes.toString()

        Glide.with(App.instance)
            .load(photoItem.thumbnail)
            .placeholder(R.drawable.unknown_profile)
            .into(ivThumbnail)
    }
}