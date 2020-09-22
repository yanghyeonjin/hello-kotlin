package com.yanghyeonjin.hellokotlin.retrofit

import android.util.Log
import com.google.gson.JsonElement
import com.yanghyeonjin.hellokotlin.model.Photo
import com.yanghyeonjin.hellokotlin.util.ApiUnsplash
import com.yanghyeonjin.hellokotlin.util.Constants.TAG
import com.yanghyeonjin.hellokotlin.util.RESPONSE_STATUS
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat

class RetrofitManager {
    companion object {
        val instance = RetrofitManager()
    }

    // 레트로핏 인터페이스 가져오기
    private val retrofitInterface: RetrofitInterface? = RetrofitClient.getClient(ApiUnsplash.BASE_URL)?.create(RetrofitInterface::class.java)

    // API 호출
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATUS, ArrayList<Photo>?) -> Unit) {
        val term = searchTerm ?: ""
        val call = retrofitInterface?.searchPhotos(term).let {
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.e(TAG, "RetrofitManager - onResponse() called | response: ${response.body()}")

                // 결과 코드가 200 일 때에만 데이터 처리를 해준다.
                when(response.code()) {
                    200 -> {
                        response.body()?.let {
                            val photoDataArray = ArrayList<Photo>()

                            val body = it.asJsonObject
                            val results = body.getAsJsonArray("results")

                            val total = body.get("total").asInt
                            if (total == 0) {
                                completion(RESPONSE_STATUS.NO_CONTENT, null)
                            } else {
                                results.forEach { resultItem ->
                                    val resultItemObject = resultItem.asJsonObject

                                    // username
                                    val user = resultItemObject.get("user").asJsonObject
                                    val username = user.get("username").asString


                                    // 좋아요 수
                                    val likes = resultItemObject.get("likes").asInt

                                    // 썸네일
                                    val thumbnail = resultItemObject.get("urls").asJsonObject.get("thumb").asString

                                    // 생성날짜
                                    val createdAt = resultItemObject.get("created_at").asString
                                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                    val formatter = SimpleDateFormat("yyyy년\nMM월 dd일")
                                    val outputDateString = formatter.format(parser.parse(createdAt))

                                    // Photo 아이템 생성 후 배열에 넣기
                                    val photoItem = Photo(thumbnail, username, outputDateString, likes)
                                    photoDataArray.add(photoItem)
                                }

                                completion(RESPONSE_STATUS.OKAY, photoDataArray)
                            }
                        }
                    }
                }
            }

            // 응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.e(TAG, "RetrofitManager - onFailure() called | t: $t")

                completion(RESPONSE_STATUS.FAIL, null)
            }
        })
    }




    fun searchUsers(searchTerm: String?, completion: (String) -> Unit) {

    }
}