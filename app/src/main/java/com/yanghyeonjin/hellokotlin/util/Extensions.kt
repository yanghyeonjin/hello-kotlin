package com.yanghyeonjin.hellokotlin.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

fun EditText.onMyTextChanged(completion: (Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(editable: Editable?) {
            completion(editable)
        }
    })
}

fun String?.isJsonObject():Boolean {
    return this?.startsWith("{") == true && this.endsWith("}")
}
fun String?.isJsonArray():Boolean {
    return this?.startsWith("[") == true && this.endsWith("]")
}

// 날짜 포맷
fun Date.getFormattedString() : String {
    val format = SimpleDateFormat("HH:mm:ss")
    return format.format(this) // 현재 시각 리턴
}