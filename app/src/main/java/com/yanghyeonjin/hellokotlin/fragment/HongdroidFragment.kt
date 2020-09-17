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
import com.yanghyeonjin.hellokotlin.databinding.FragmentHongdroidBinding

class HongdroidFragment: Fragment() {

    private var _binding: FragmentHongdroidBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContext: Context

    companion object {
        fun getInstance(): HongdroidFragment {
            return HongdroidFragment()
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
        _binding = FragmentHongdroidBinding.inflate(inflater, container, false)
        val view = binding.root

        Glide.with(mContext)
            .load("https://yt3.ggpht.com/a/AATXAJzwJME0qk0LT2_ispF1tbwLW_eS_Z0wMK3yWGjv=s100-c-k-c0xffffffff-no-rj-mo")
            .placeholder(R.drawable.unknown_profile)
            .into(binding.ivHongdroidAvatar)



        // 각각 강의로 이동
        binding.btnCh3.setOnClickListener {
            val intent = Intent(mContext, SubActivity::class.java)

            intent.putExtra("msg", "메시지 받아랏!")
            mContext.startActivity(intent)
        }

        binding.btnCh5.setOnClickListener {
            val intent = Intent(mContext, ListViewActivity::class.java)
            mContext.startActivity(intent)
        }

        binding.btnCh6.setOnClickListener {
            val intent = Intent(mContext, NavigationDrawerActivity::class.java)
            mContext.startActivity(intent)
        }

        binding.btnCh7.setOnClickListener {
            val intent = Intent(mContext, SharedPrefActivity::class.java)
            mContext.startActivity(intent)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}