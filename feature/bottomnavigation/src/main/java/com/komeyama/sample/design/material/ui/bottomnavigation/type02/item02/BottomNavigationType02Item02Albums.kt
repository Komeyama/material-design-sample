package com.komeyama.sample.design.material.ui.bottomnavigation.type02.item02

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import com.komeyama.sample.design.material.ui.bottomnavigation.databinding.BottomNavType02AlbumBinding
import com.komeyama.sample.design.material.ui.bottomnavigation.type02.BottomNavigationType02Item02Directions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type02_item02_albums.*
import timber.log.Timber

class BottomNavigationType02Item02Albums :
    Fragment(R.layout.fragment_bottom_navigation_type02_item02_albums) {

    private lateinit var spinnerType: ArrayList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val groupAdapter = GroupAdapter<ViewHolder<*>>()
        bottom_nav_type02_item02_recycler_view.adapter = groupAdapter
        val items: MutableList<BottomNavType02ItemAlbum> = mutableListOf()
        for (index in 0 until 20) {
            Timber.d("bottom type02: %s", index)
            items.add(
                BottomNavType02ItemAlbum(
                    "Album name $index",
                    "Artist name $index",
                    (index + 1).toString()
                )
            )
        }
        groupAdapter.update(items)
        bottom_nav_type02_item02_recycler_view.addOnScrollListener(onScrollListener)

        // set album total num
        current_album_num.text = items.size.toString()

        // set spinner
        spinnerType = arrayListOf(
            activity!!.getString(R.string.bottom_navigation_album_order_type01),
            activity!!.getString(R.string.bottom_navigation_album_order_type02),
            activity!!.getString(R.string.bottom_navigation_album_order_type03),
            activity!!.getString(R.string.bottom_navigation_album_order_type04)
        )
        album_display_order_spinner.adapter =
            ArrayAdapter<String>(activity!!, R.layout.album_order_list, spinnerType)
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }
    }
}

class BottomNavType02ItemAlbum(
    private val albumName: String,
    private val artistName: String,
    private val albumTime: String
) : BindableItem<BottomNavType02AlbumBinding>() {
    override fun getLayout() = R.layout.bottom_nav_type02_album

    override fun bind(viewBinding: BottomNavType02AlbumBinding, position: Int) {
        viewBinding.albumName.text = albumName
        viewBinding.artistName.text = artistName
        viewBinding.albumTime.text = albumTime

        viewBinding.root.setOnClickListener {
            Timber.d("on click album position:%s, title:%s ", position, viewBinding.albumName.text)
            val action = BottomNavigationType02Item02Directions.
                actionBottomNavigationType02FragmentItem02ToBottomNavigationType02FragmentItem02Album(
                    viewBinding.albumName.text.toString(),
                    viewBinding.artistName.text.toString(),
                    viewBinding.albumTime.text.toString()
                )
            viewBinding.root.findNavController().navigate(action)
        }
    }
}