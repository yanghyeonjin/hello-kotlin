package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.yanghyeonjin.hellokotlin.adapter.PhotoGridViewAdapter
import com.yanghyeonjin.hellokotlin.databinding.ActivityPhotoCollectionBinding
import com.yanghyeonjin.hellokotlin.model.Photo

class PhotoCollectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoCollectionBinding

    private var photoList = ArrayList<Photo>()
    private lateinit var photoGridViewAdapter: PhotoGridViewAdapter

    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoCollectionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.e(TAG, "PhotoCollectionActivity - onCreate() called")

        // 번들로 넘어온 photoList 꺼내기
        val bundle = intent.getBundleExtra("bundlePhoto")
        photoList = bundle?.getSerializable("photoArrayList") as ArrayList<Photo>

        // top app bar 변경
        val searchTerm = intent.getStringExtra("searchTerm")
        binding.topAppBar.title = searchTerm


        // 리사이클러뷰 설정
        photoGridViewAdapter = PhotoGridViewAdapter()
        photoGridViewAdapter.setPhotoList(photoList)
        binding.recyclerViewPhotos.apply {
            this.layoutManager = GridLayoutManager(this@PhotoCollectionActivity, 2, GridLayoutManager.VERTICAL, false)
            this.adapter = photoGridViewAdapter
        }

    }
}