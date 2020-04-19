package com.komeyama.sample.design.material


class MainFragmentData {

    companion object {
        val mainItems: List<MainFragmentRecycleView.DesignInformation> = listOf(
            MainFragmentRecycleView.DesignInformation(
                R.drawable.top_image_bottom_bar,
                DesignName.APP_BARS_BOTTOM.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.top_image_backdrop,
                DesignName.BACKDROP.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.ic_launcher_foreground,
                DesignName.CARD.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.ic_launcher_foreground,
                DesignName.DIALOG.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.ic_launcher_foreground,
                DesignName.FLOATING_ACTION.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.ic_launcher_foreground,
                DesignName.TEXT_FIELD.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.ic_launcher_foreground,
                DesignName.COMING_SOON.designName
            )
        )
    }

    enum class DesignName(val designName: String){
        APP_BARS_BOTTOM("App bars: bottom"),
        BACKDROP("Backdrop"),
        CARD("Card"),
        DIALOG("Dialog"),
        FLOATING_ACTION("Floating Action"),
        TEXT_FIELD("Text Field"),
        COMING_SOON("Coming soon"),
    }

}