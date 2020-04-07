package com.komeyama.sample.design.material.ui.mainfragment

import com.komeyama.sample.design.material.R
import com.komeyama.sample.design.material.ui.mainfragment.MainFragmentRecycleView.*

class MainFragmentData {

    companion object {
        val mainItems: List<DesignInformation> = listOf(
            DesignInformation(R.drawable.top_image_bottom_bar, DesignName.APP_BARS_BOTTOM.designName),
            DesignInformation(R.drawable.top_image_backdrop, DesignName.BACKDROP.designName),
            DesignInformation(R.drawable.ic_launcher_foreground, DesignName.CARD.designName),
            DesignInformation(R.drawable.ic_launcher_foreground, DesignName.DIALOG.designName),
            DesignInformation(R.drawable.ic_launcher_foreground, DesignName.FLOATING_ACTION.designName),
            DesignInformation(R.drawable.ic_launcher_foreground, DesignName.COMING_SOON.designName)
        )
    }

    enum class DesignName(val designName: String){
        APP_BARS_BOTTOM("App bars: bottom"),
        BACKDROP("Backdrop"),
        CARD("Card"),
        DIALOG("Dialog"),
        FLOATING_ACTION("Floating Action"),
        COMING_SOON("Coming soon"),
    }

}