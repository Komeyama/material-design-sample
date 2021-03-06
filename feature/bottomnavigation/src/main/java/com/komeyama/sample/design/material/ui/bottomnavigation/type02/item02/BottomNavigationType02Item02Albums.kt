package com.komeyama.sample.design.material.ui.bottomnavigation.type02.item02

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.komeyama.sample.design.material.ui.bottomnavigation.BottomNavigationType02
import com.komeyama.sample.design.material.ui.bottomnavigation.R
import com.komeyama.sample.design.material.ui.bottomnavigation.databinding.BottomNavAlbumBinding
import com.komeyama.sample.design.material.ui.bottomnavigation.type02.BottomNavigationType02Item02Directions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.fragment_bottom_navigation_type02_item02_albums.*
import timber.log.Timber

class BottomNavigationType02Item02Albums :
    Fragment(R.layout.fragment_bottom_navigation_type02_item02_albums) {

    private lateinit var spinnerType: ArrayList<String>
    lateinit var bottomNavigationType02: BottomNavigationType02

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

    /**
     * Todo: refactor
     */
    override fun onResume() {
        super.onResume()
        bottomNavigationType02 = (parentFragment?.parentFragment?.parentFragment as BottomNavigationType02)
        bottomNavigationType02.initBottomNavigation()
    }

    inner class BottomNavType02ItemAlbum(
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
                viewBinding.albumItemTop.transitionName = "transition_album_container"
                val extras = FragmentNavigatorExtras(
                    viewBinding.albumItemTop to viewBinding.albumItemTop.transitionName
                )

                this@BottomNavigationType02Item02Albums.bottomNavigationType02.hideBottomNavigation()
                val action = BottomNavigationType02Item02Directions.
                    actionBottomNavigationType02FragmentItem02ToBottomNavigationType02FragmentItem02Album(
                        viewBinding.albumName.text.toString(),
                        viewBinding.artistName.text.toString(),
                        viewBinding.albumTime.text.toString()
                    )
                viewBinding.root.findNavController().navigate(action, extras)
            }
        }
    }
}

