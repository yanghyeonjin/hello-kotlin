package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yanghyeonjin.hellokotlin.databinding.ActivitySharedPrefBinding

class SharedPrefActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySharedPrefBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPrefBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()

        // 종료될 때, SharedPreferences에 저장한다.
        saveData()
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        binding.etHello.setText(pref.getString("name", "")) // name 값이 없을 땐, 기본 값 ""을 리턴 한다.
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0) // mode: 0 -> MODE_PRIVATE
        val edit = pref.edit() // 수정모드
        edit.putString("name", binding.etHello.text.toString())
        edit.apply()
    }
}