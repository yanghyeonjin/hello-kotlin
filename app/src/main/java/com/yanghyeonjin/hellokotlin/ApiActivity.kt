package com.yanghyeonjin.hellokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.yanghyeonjin.hellokotlin.databinding.ActivityApiBinding
import com.yanghyeonjin.hellokotlin.retrofit.RetrofitManager
import com.yanghyeonjin.hellokotlin.util.Constants
import com.yanghyeonjin.hellokotlin.util.RESPONSE_STATUS
import com.yanghyeonjin.hellokotlin.util.SEARCH_TYPE
import com.yanghyeonjin.hellokotlin.util.onMyTextChanged

class ApiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApiBinding

    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.e(Constants.TAG, "ApiActivity - onCreate() called")


        // 라디오 그룹 설정
        binding.rgFilter.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId) {
                R.id.radioSearchPhoto -> {
                    binding.outlineEtSearch.hint = getString(R.string.radio_search_photo)
                    binding.outlineEtSearch.startIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_image_24)
                    currentSearchType = SEARCH_TYPE.PHOTO
                }
                R.id.radioSearchUser -> {
                    binding.outlineEtSearch.hint = getString(R.string.radio_search_user)
                    binding.outlineEtSearch.startIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_person_pin_black_24)
                    currentSearchType = SEARCH_TYPE.USER
                }
            }
        }


        // 텍스트가 변경이 되었을 때
        binding.etSearch.onMyTextChanged {
            if (it.toString().count() > 0) {
                binding.frameSearch.visibility = View.VISIBLE
            } else {
                binding.frameSearch.visibility = View.INVISIBLE
            }
        }


        // 검색 버튼 클릭
        binding.btnSearch.setOnClickListener {
            binding.progress.visibility = View.VISIBLE

            val userSearchInput = binding.etSearch.text.toString()

            RetrofitManager.instance.searchPhotos(userSearchInput) { responseState, responseBody ->
                when (responseState) {
                    RESPONSE_STATUS.OKAY -> {
                        Log.e(TAG, "API 호출 성공: $responseBody")

                        // 결과 화면으로 보여준다
                        val intent = Intent(this, PhotoCollectionActivity::class.java)
                        val bundle = Bundle()
                        bundle.putSerializable("photoArrayList", responseBody)
                        intent.putExtra("bundlePhoto", bundle)
                        intent.putExtra("searchTerm", userSearchInput)

                        startActivity(intent)
                    }
                    RESPONSE_STATUS.FAIL -> {
                        Toast.makeText(this, "API 호출 에러입니다.", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "API 호출 실패: $responseBody")
                    }
                    RESPONSE_STATUS.NO_CONTENT -> {
                        Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                binding.etSearch.setText("")
                binding.progress.visibility = View.INVISIBLE
            }
        }
    }
}