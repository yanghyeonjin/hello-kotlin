package com.yanghyeonjin.hellokotlin

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.yanghyeonjin.hellokotlin.adapter.PhotoGridViewAdapter
import com.yanghyeonjin.hellokotlin.databinding.ActivityPhotoCollectionBinding
import com.yanghyeonjin.hellokotlin.model.Photo
import com.yanghyeonjin.hellokotlin.model.SearchHistory
import com.yanghyeonjin.hellokotlin.util.SharedPrefManager
import com.yanghyeonjin.hellokotlin.util.getFormattedString
import java.util.*
import kotlin.collections.ArrayList

class PhotoCollectionActivity : AppCompatActivity(),
                                SearchView.OnQueryTextListener,
                                CompoundButton.OnCheckedChangeListener,
                                View.OnClickListener {
    private lateinit var binding: ActivityPhotoCollectionBinding

    private var photoList = ArrayList<Photo>()
    private lateinit var photoGridViewAdapter: PhotoGridViewAdapter

    // 서치뷰와 서치뷰의 EditText
    private lateinit var searchView: SearchView
    private lateinit var etSearchView: EditText

    // 검색 목록 담을 그릇
    private var searchHistoryList = ArrayList<SearchHistory>()

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



        // 액티비티에서 어떤 액션바를 사용할지 설정한다.
        setSupportActionBar(binding.topAppBar)


        binding.switchSearchHistorySaveMode.setOnCheckedChangeListener(this)
        binding.btnDeleteAllHistory.setOnClickListener(this)


        // 저장된 검색기록 가져오기
        this.searchHistoryList = SharedPrefManager.getSearchHistoryList() as ArrayList<SearchHistory>
        this.searchHistoryList.forEach {
            Log.e(TAG, "저장된 검색기록: ${it.term}, ${it.timestamp}")
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_app_bar, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.apply {
            this.queryHint = "검색어를 입력해주세요"
            this.setOnQueryTextFocusChangeListener { view, hasExpanded ->
                when(hasExpanded) {
                    true -> {
                        Log.e(TAG, "PhotoCollectionActivity - 서치뷰가 열렸다.")
                        binding.linearSearchHistory.visibility = View.VISIBLE
                    }
                    false -> {
                        Log.e(TAG, "PhotoCollectionActivity - 서치뷰가 닫혔다.")
                        binding.linearSearchHistory.visibility = View.INVISIBLE
                    }
                }
            }
            this.setOnQueryTextListener(this@PhotoCollectionActivity)


            etSearchView = this.findViewById(androidx.appcompat.R.id.search_src_text) // 서치뷰에서 EditText를 가져온다.

        }

        etSearchView.apply {
            this.filters = arrayOf(InputFilter.LengthFilter(12))
            this.setTextColor(Color.WHITE)
            this.setHintTextColor(Color.WHITE)
        }

        return true
    }


    // 서치뷰 검색어 입력 이벤트
    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.e(TAG, "PhotoCollectionActivity - query: $query")

        // 검색한 결과로 app bar title 다시 바꾸기
        if (!query.isNullOrEmpty()) {
            binding.topAppBar.title = query

            val searchHistory = SearchHistory(term = query, timestamp = Date().getFormattedString())
            this.searchHistoryList.add(searchHistory)

            SharedPrefManager.saveSearchHistoryList(searchHistoryList)
        }

        binding.topAppBar.collapseActionView()

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "PhotoCollectionActivity - newText: $newText")

        val userInputText = newText ?: ""

        if (userInputText.count() == 12) {
            Toast.makeText(this, "검색어는 12자까지만 입력가능합니다.", Toast.LENGTH_SHORT).show()
        }

        return true
    }


    // 스위치 변경 이벤트
    override fun onCheckedChanged(switch: CompoundButton?, isChecked: Boolean) {
        when(switch) {
            binding.switchSearchHistorySaveMode -> {
                if (isChecked) {
                    Log.e(TAG, "검색어 저장 On")
                } else {
                    Log.e(TAG, "검색어 저장 Off")
                }
            }
        }
    }

    // 뷰 클릭 이벤트
    override fun onClick(view: View?) {
        when (view) {
            binding.btnDeleteAllHistory -> {
                Log.e(TAG, "전체삭제 버튼이 클릭되었다.")
            }
        }
    }
}