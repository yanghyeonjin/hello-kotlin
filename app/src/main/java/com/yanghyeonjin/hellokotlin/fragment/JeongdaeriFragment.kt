package com.yanghyeonjin.hellokotlin.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.yanghyeonjin.hellokotlin.*
import com.yanghyeonjin.hellokotlin.databinding.FragmentJeongdaeriBinding

class JeongdaeriFragment: Fragment() {
    private var _binding: FragmentJeongdaeriBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContext: Context

    companion object {
        fun getInstance(): JeongdaeriFragment {
            return JeongdaeriFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJeongdaeriBinding.inflate(inflater, container, false)
        val view = binding.root

        Glide.with(mContext)
            .load("https://yt3.ggpht.com/a/AATXAJwJaJaSpMBOVf8crMLcPEyqrOy6rGGNM7QR9xjB=s100-c-k-c0xffffffff-no-rj-mo")
            .placeholder(R.drawable.unknown_profile)
            .into(binding.ivJeongdaeriAvatar)


        // 강의 화면으로 이동
        binding.btnCh3.setOnClickListener {
            val intent = Intent(mContext, LifeCycleActivity::class.java)
            startActivity(intent)
        }
        binding.btnCh4.setOnClickListener {
            val intent = Intent(mContext, LayoutPractice1Activity::class.java)
            startActivity(intent)
        }
        binding.btnCh5.setOnClickListener {
            val intent = Intent(mContext, LayoutPractice2Activity::class.java)
            startActivity(intent)
        }
        binding.btnCh7.setOnClickListener {
            val intent = Intent(mContext, LottieAnimationActivity::class.java)
            startActivity(intent)
        }
        binding.btnAdMob.setOnClickListener {
            val intent = Intent(mContext, AdMobActivity::class.java)
            startActivity(intent)
        }
        binding.btnShakeDetect.setOnClickListener {
            val intent = Intent(mContext, ShakeDetectActivity::class.java)
            startActivity(intent)
        }
        binding.btnCompletionBlock.setOnClickListener {
            val intent = Intent(mContext, CompletionBlockActivity::class.java)
            startActivity(intent)
        }
        binding.btnCh8.setOnClickListener {
            val intent = Intent(mContext, ConstructorActivity::class.java)
            startActivity(intent)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}