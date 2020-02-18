package com.komeyama.sample.design.material.ui.bottombarfragment

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_bottm_bar.*
import timber.log.Timber

class BottomBarFragment : Fragment() {

    private var users: List<UserInformation> = listOf(
        UserInformation(
            "dummy_0",
            "Hello"
        ),
        UserInformation(
            "dummy_1",
            "Hello!"
        ),
        UserInformation(
            "dummy_2",
            "Hello!!"
        ),
        UserInformation(
            "dummy_3",
            "Hello!!!"
        ),
        UserInformation(
            "dummy_4",
            "Hello!!!!"
        ),
        UserInformation(
            "dummy_5",
            "Hello!!!!!"
        ),
        UserInformation(
            "dummy_6",
            "Hello!!!!!!"
        ),
        UserInformation(
            "dummy_7",
            "Hello!!!!!!!"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_bottm_bar, container, false)
        val userListAdapter = UserListAdapter(users, UserClick {
            Timber.d("tap: %s",it)
        })
        root.findViewById<RecyclerView>(R.id.bottom_recycler_view).apply{
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context)
        }

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        bottom_bar.replaceMenu(R.menu.bottom_bar_menu)
        bottom_bar.setNavigationOnClickListener{
            Timber.d("tap navigation button")
        }
        bottom_bar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.bottom_bar_serch -> {
                    Timber.d("tap bottom bar search button")
                    true
                }
                R.id.bottom_bar_more_vert -> {
                    Timber.d("tap bottom more vert button")
                    true
                }
                else -> true
            }
        }
        bottom_fab.setOnClickListener {
            Timber.d("tap bottom fab button")
        }
    }
}

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