package com.yanghyeonjin.hellokotlin.model

import java.io.Serializable

data class Photo(var thumbnail: String?, var author: String?, var createdAt: String?, var likes: Int) : Serializable {
}