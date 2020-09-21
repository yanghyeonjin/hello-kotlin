package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yanghyeonjin.hellokotlin.databinding.ActivitySingletonBinding
import com.yanghyeonjin.hellokotlin.model.MySQLOpenHelper
import com.yanghyeonjin.hellokotlin.model.MySQLOpenHelperSingleton
import com.yanghyeonjin.hellokotlin.model.NormalClass
import com.yanghyeonjin.hellokotlin.model.SingletonClass

class SingletonActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingletonBinding

    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingletonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val normalClass1 = NormalClass()
        val normalClass2 = NormalClass()
        binding.btnNormalClass.setOnClickListener{
            binding.tvClassA.text = normalClass1.toString()
            binding.tvClassB.text = normalClass2.toString()
        }

        val singletonClass1 = SingletonClass
        val singletonClass2 = SingletonClass
        binding.btnSingletonClass.setOnClickListener{
            binding.tvClassA.text = singletonClass1.toString()
            binding.tvClassB.text = singletonClass2.toString()
        }

        // 매개변수를 가진 클래스를 싱글턴으로 만들려고 할 때
        val sqlOpenHelper1 = MySQLOpenHelper(this)
        val sqlOpenHelper2 = MySQLOpenHelper(this)
        binding.btnNormalDBHelper.setOnClickListener {
            binding.tvClassA.text = sqlOpenHelper1.toString()
            binding.tvClassB.text = sqlOpenHelper2.toString()
        }

        val sqlOpenHelperSingleton1 = MySQLOpenHelperSingleton.getInstance(this)
        val sqlOpenHelperSingleton2 = MySQLOpenHelperSingleton.getInstance(this)
        binding.btnSingletonDBHelper.setOnClickListener {
            binding.tvClassA.text = sqlOpenHelperSingleton1.toString()
            binding.tvClassB.text = sqlOpenHelperSingleton2.toString()
        }
    }
}