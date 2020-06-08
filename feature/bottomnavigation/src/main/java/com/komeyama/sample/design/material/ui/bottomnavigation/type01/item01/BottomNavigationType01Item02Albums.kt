package com.komeyama.sample.design.material.ui.bottomnavigation.type01.item01

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import com.komeyama.sample.design.material.ui.bottomnavigation.databinding.BottomNavAlbumBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type01_item02_album.*
import timber.log.Timber

class BottomNavigationType01Item02Albums: Fragment(R.layout.fragment_bottom_navigation_type01_item02_album) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        bottom_nav_type01_item02_recycler_view.adapter = groupAdapter
        val items: MutableList<BottomNavType01ItemAlbum> = mutableListOf()
        for (index in 0 until 20) {
            Timber.d("bottom type02: %s", index)
            items.add(
                BottomNavType01ItemAlbum(
                    "Album name $index",
                    "Artist name $index",
                    (index + 1).toString()
                )
            )
        }
        groupAdapter.update(items)
    }

    inner class BottomNavType01ItemAlbum(
        private val albumName: String,
        private val artistName: String,
        private val albumTime: String
    ) : BindableItem<BottomNavAlbumBinding>() {
        override fun getLayout() = R.layout.bottom_nav_album

        override fun bind(viewBinding: BottomNavAlbumBinding, position: Int) {
            viewBinding.albumName.text = albumName
            viewBinding.artistName.text = artistName
            viewBinding.albumTime.text = albumTime

            viewBinding.root.setOnClickListener {
                Timber.d("on click album position:%s, title:%s ", position, viewBinding.albumName.text)
            }
        }
    }
}