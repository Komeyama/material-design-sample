package com.komeyama.sample.design.material


class MainFragmentData {

    companion object {
        val mainItems: List<MainFragmentRecycleView.DesignInformation> = listOf(
            MainFragmentRecycleView.DesignInformation(
                R.drawable.top_image_bottom_bar,
                DesignName.APP_BARS_BOTTOM.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.ic_launcher_foreground,
                DesignName.APP_BARS_TOP.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.top_image_backdrop,
                DesignName.BACKDROP.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.top_image_card,
                DesignName.CARD.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.top_image_dialog,
                DesignName.DIALOG.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.top_image_floating_action,
                DesignName.FLOATING_ACTION.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.top_image_text_field,
                DesignName.TEXT_FIELD.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.top_image_selection_control,
                DesignName.SELECTION_CONTROL.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.ic_launcher_foreground,
                DesignName.COMING_SOON.designName
            ),
            MainFragmentRecycleView.DesignInformation(
                R.drawable.ic_launcher_foreground,
                DesignName.COMING_SOON.designName
            )
        )
    }

    enum class DesignName(val designName: String){
        APP_BARS_BOTTOM("App bars: bottom"),
        APP_BARS_TOP("App bars: top"),
        BACKDROP("Backdrop"),
        CARD("Card"),
        DIALOG("Dialog"),
        FLOATING_ACTION("Floating Action"),
        TEXT_FIELD("Text Field"),
        SELECTION_CONTROL("Selection Control"),
        COMING_SOON("Coming soon")
    }

}