package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yanghyeonjin.hellokotlin.databinding.ActivityLifeCycleBinding

class LifeCycleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLifeCycleBinding

    companion object {
        const val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifeCycleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.e(TAG, "LifeCycleActivity - onCreate() called")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "LifeCycleActivity - onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "LifeCycleActivity - onResume() called")

    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "LifeCycleActivity - onPause() called")

        binding.tvSplash.visibility = View.VISIBLE
        binding.tvSplash.text = getString(R.string.splash_text)
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "LifeCycleActivity - onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "LifeCycleActivity - onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "LifeCycleActivity - onRestart() called")
    }
}