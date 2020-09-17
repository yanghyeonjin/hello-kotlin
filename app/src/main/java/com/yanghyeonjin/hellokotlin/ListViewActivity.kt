package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.yanghyeonjin.hellokotlin.adapter.UserAdapter
import com.yanghyeonjin.hellokotlin.databinding.ActivityListViewBinding
import com.yanghyeonjin.hellokotlin.model.User
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 기본 리스트뷰
        val basicItemList = arrayOf("사과", "망고", "복숭아")
        binding.listViewBasic.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, basicItemList)


        // 커스텀 리스트뷰
        val userList = arrayListOf<User>(
            User(R.drawable.android_character, "홍길동1", "21", "안녕하세요!"),
            User(R.drawable.android_character, "홍길동2", "22", "안녕하세요!!"),
            User(R.drawable.android_character, "홍길동3", "23", "안녕하세요!!!"),
            User(R.drawable.android_character, "홍길동4", "24", "안녕하세요!!!!"),
            User(R.drawable.android_character, "홍길동5", "25", "안녕하세요!!!!!"),
            User(R.drawable.android_character, "홍길동6", "26", "안녕하세요!!!!!!")
        )
        val userAdapter = UserAdapter(this, userList)
        binding.listViewCustom.apply {
            adapter = userAdapter
            onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val selectedItem = adapterView.getItemAtPosition(position) as User
                Toast.makeText(this@ListViewActivity, selectedItem.name, Toast.LENGTH_SHORT).show()
            }
        }

    }
}