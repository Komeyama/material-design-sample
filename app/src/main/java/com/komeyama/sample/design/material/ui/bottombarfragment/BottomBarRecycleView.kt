package com.komeyama.sample.design.material.ui.bottombarfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.komeyama.sample.design.material.R

class BottomBarRecycleView {
    class UserListAdapter(private val users:List<UserInformation>, private val userClick: UserClick): RecyclerView.Adapter<UserListHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return UserListHolder(layoutInflater.inflate(R.layout.list_item_user, parent, false))
        }

        override fun getItemCount() = users.size

        override fun onBindViewHolder(holder: UserListHolder, position: Int) {
            holder.userName.text = users[position].userName
            holder.greetingWord.text = users[position].greetingWord
            holder.view.setOnClickListener {
                userClick.onClick(users[position])
            }
        }
    }


    class UserListHolder(val view: View): RecyclerView.ViewHolder(view) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.list_item_user
        }
        val userName: TextView = view.findViewById(R.id.user_name)
        val greetingWord: TextView = view.findViewById(R.id.greeting_word)
    }

    class UserClick(val item:(UserInformation) -> Unit) {
        fun onClick(item: UserInformation) {
            item(item)
        }
    }

    data class UserInformation(val userName: String, val greetingWord: String)
}