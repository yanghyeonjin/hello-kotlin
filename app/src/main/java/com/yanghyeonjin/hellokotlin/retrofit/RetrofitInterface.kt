package com.yanghyeonjin.hellokotlin.retrofit

import com.google.gson.JsonElement
import com.yanghyeonjin.hellokotlin.util.ApiUnsplash
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET(ApiUnsplash.URL_SEARCH_PHOTOS)
    fun searchPhotos(@Query("query") searchTerm: String) : Call<JsonElement>

    @GET(ApiUnsplash.URL_SEARCH_USERS)
    fun searchUsers(@Query("query") searchTerm: String) : Call<JsonElement>
}