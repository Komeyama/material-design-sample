package com.komeyama.sample.design.material.ui.card

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.komeyama.sample.design.material.R
import kotlinx.android.synthetic.main.fragment_card_type02.*

class CardType02: Fragment(R.layout.fragment_card_type02){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card_favorite.setOnClickListener {
            if (card_favorite.drawable.constantState == ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_pink_24dp, null)?.constantState) {
                card_favorite.startFavoriteAnimation(1.2f, R.drawable.ic_favorite_24dp)
            } else {
                card_favorite.startFavoriteAnimation(0.2f, R.drawable.ic_favorite_pink_24dp)
            }
        }
    }
}