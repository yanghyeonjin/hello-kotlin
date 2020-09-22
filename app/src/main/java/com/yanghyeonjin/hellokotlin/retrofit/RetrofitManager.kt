package com.yanghyeonjin.hellokotlin.retrofit

import android.util.Log
import com.google.gson.JsonElement
import com.yanghyeonjin.hellokotlin.util.ApiUnsplash
import com.yanghyeonjin.hellokotlin.util.Constants.TAG
import com.yanghyeonjin.hellokotlin.util.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {
    companion object {
        val instance = RetrofitManager()
    }

    // 레트로핏 인터페이스 가져오기
    private val retrofitInterface: RetrofitInterface? = RetrofitClient.getClient(ApiUnsplash.BASE_URL)?.create(RetrofitInterface::class.java)

    // API 호출
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATE, String) -> Unit) {
        val term = searchTerm ?: ""
        val call = retrofitInterface?.searchPhotos(term).let {
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.e(TAG, "RetrofitManager - onResponse() called | response: ${response.body()}")

                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            // 응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.e(TAG, "RetrofitManager - onFailure() called | t: $t")

                completion(RESPONSE_STATE.FAIL, t.toString())
            }
        })
    }




    fun searchUsers(searchTerm: String?, completion: (String) -> Unit) {

    }
}