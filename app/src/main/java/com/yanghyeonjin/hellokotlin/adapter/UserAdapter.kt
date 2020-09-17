package com.yanghyeonjin.hellokotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.yanghyeonjin.hellokotlin.R
import com.yanghyeonjin.hellokotlin.model.User

class UserAdapter(private val context: Context, private val users: ArrayList<User>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // item_user 뷰를 붙였다.
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_user, null)

        val ivProfile = view.findViewById<ImageView>(R.id.ivProfile)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvAge = view.findViewById<TextView>(R.id.tvAge)
        val tvGreet = view.findViewById<TextView>(R.id.tvGreet)

        val user = users[position] // 넘어온 Array에 담긴 데이터들로 하나하나씩 User를 만든다.

        ivProfile.setImageResource(user.profile)
        tvName.text = user.name
        tvAge.text = user.age
        tvGreet.text = user.greet

        return view
    }

    override fun getItem(position: Int): Any {
        return users[position]
    }

    override fun getItemId(position: Int): Long {
        return 0 // 사용 안하기 때문에 0으로 설정
    }

    override fun getCount(): Int {
        return users.size
    }

}
