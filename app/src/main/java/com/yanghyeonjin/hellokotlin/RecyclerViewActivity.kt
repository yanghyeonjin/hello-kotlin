package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.yanghyeonjin.hellokotlin.adapter.RecyclerViewAdapter
import com.yanghyeonjin.hellokotlin.databinding.ActivityRecyclerViewBinding
import com.yanghyeonjin.hellokotlin.listener.RecyclerViewInterface
import com.yanghyeonjin.hellokotlin.model.RecyclerViewModel

class RecyclerViewActivity : AppCompatActivity(), RecyclerViewInterface {
    private lateinit var binding: ActivityRecyclerViewBinding

    private var modelList = ArrayList<RecyclerViewModel>()
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter


    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 데이터 담는 작업
        for (i in 1..10) {
            val item = RecyclerViewModel(userName = "쩡대리 $i", profileImage = "https://img1.kakaocdn.net/thumb/C100x100.mplusfriend/?fname=http%3A%2F%2Fk.kakaocdn.net%2Fdn%2FIxxPp%2FbtqC9MkM3oH%2FPpvHOkfOiOpKUwvvWcxhJ0%2Fimg_s.jpg")
            modelList.add(item)
        }

        recyclerViewAdapter = RecyclerViewAdapter(this)
        recyclerViewAdapter.submitList(modelList)

        // 리사이클러뷰 설정
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity, LinearLayoutManager.VERTICAL, false)
            adapter = recyclerViewAdapter
        }
    }

    override fun onItemClicked(position: Int) {
        val title = modelList[position].userName ?: ""

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage("$title 와 함께하는 빡코딩!")
            .setPositiveButton("오케이") { dialog, id ->
                Log.e(TAG, "다이얼로그 확인 버튼 클릭했음.")
            }
            .show()
    }
}