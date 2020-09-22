package com.yanghyeonjin.hellokotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yanghyeonjin.hellokotlin.R
import com.yanghyeonjin.hellokotlin.model.Photo
import com.yanghyeonjin.hellokotlin.viewholder.PhotoViewHolder

class PhotoGridViewAdapter: RecyclerView.Adapter<PhotoViewHolder>() {
    private var photoList = ArrayList<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindWithView(this.photoList[position])
    }

    override fun getItemCount(): Int {
        return this.photoList.size
    }

    fun setPhotoList(photoList: ArrayList<Photo>) {
        this.photoList = photoList
    }
}