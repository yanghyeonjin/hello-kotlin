package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.yanghyeonjin.hellokotlin.adapter.EmployeeAdapter
import com.yanghyeonjin.hellokotlin.databinding.ActivityEmployeeListBinding
import com.yanghyeonjin.hellokotlin.model.Employee

class EmployeeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 리사이클러뷰 데이터
        val employeeList = arrayListOf(
            Employee(0, "홍길동1", 21, "서버 관리자"),
            Employee(1, "홍길동2", 22, "리액트 개발자"),
            Employee(0, "홍길동3", 23, "Node.js 개발자"),
            Employee(1, "홍길동4", 24, "데이터베이스 엔지니어"),
            Employee(0, "홍길동5", 25, "안드로이드 개발자"),
            Employee(1, "홍길동6", 26, "아이폰 개발자")
        )

        // 리사이클러뷰 셋팅
        binding.rvEmployeeList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvEmployeeList.setHasFixedSize(true)
        binding.rvEmployeeList.adapter = EmployeeAdapter(employeeList)
    }
}