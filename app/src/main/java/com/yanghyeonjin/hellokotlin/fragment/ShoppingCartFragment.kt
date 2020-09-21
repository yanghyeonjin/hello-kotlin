package com.yanghyeonjin.hellokotlin.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yanghyeonjin.hellokotlin.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment: Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    companion object {
        const val TAG: String = "로그"
        
        fun getInstance(): ShoppingCartFragment {
            return ShoppingCartFragment()
        }
    }

    // 프래그먼트가 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "ShoppingCartFragment - onCreate() called")
    }

    // 프래그먼트가 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "ShoppingCartFragment - onAttach() called")
    }

    // 액티비티의 onCreate() 에 해당하는 것
    // 뷰가 생성되었을 때, 프래그먼트와 레이아웃을 연결시켜주는 부분이다.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val view = binding.root


        Log.e(TAG, "ShoppingCartFragment - onCreateView() called")


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}