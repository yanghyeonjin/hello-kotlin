package com.yanghyeonjin.hellokotlin.util

object Constants {
    const val TAG: String = "로그"
}

enum class SEARCH_TYPE {
    PHOTO,
    USER
}

enum class RESPONSE_STATUS {
    OKAY,
    FAIL,
    NO_CONTENT
}

object ApiUnsplash {
    const val BASE_URL: String = "https://api.unsplash.com/"

    const val URL_SEARCH_PHOTOS: String = "search/photos"
    const val URL_SEARCH_USERS: String = "search/users"
}