package com.komeyama.sample.design.material.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = top_toolbar as Toolbar
        setSupportActionBar(toolbar)

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.topBarType01,
                R.id.topBarType02,
                R.id.topBarType03,
                R.id.bottomBarFragment,
                R.id.backDropTypeSelectFragment,
                R.id.backDropFragment,
                R.id.transitionCard,
                R.id.bottomNavigationType01Fragment,
                R.id.bottomNavigationType02Fragment
                    -> top_toolbar.visibility = View.GONE
                else -> top_toolbar.visibility = View.VISIBLE
            }
        }

        top_toolbar.setNavigationOnClickListener {
            top_drawer.openDrawer(Gravity.LEFT)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
