package com.komeyama.sample.design.material.ui.backdropfragment

import com.komeyama.sample.design.material.R
import com.komeyama.sample.design.material.ui.backdropfragment.BackDropRecycleView.BackDropTopSheetInformation
import com.komeyama.sample.design.material.ui.backdropfragment.BackDropUnderRecycleView.BackDropUnderSheetInformation

class BackDropTopSheetData {
    val backdropDummyItems: List<BackDropTopSheetInformation> = listOf(
        BackDropTopSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_01.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_02.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_03.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_04.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_05.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_06.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_07.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_08.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_09.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_10.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_11.designName),
        BackDropTopSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_12.designName)
    )
}

class BackDropUnderSheetData {
    val backdropBottomDummyItems: List<BackDropUnderSheetInformation> = listOf(
        BackDropUnderSheetInformation(SheetItemName.ITEM_01.designName),
        BackDropUnderSheetInformation(SheetItemName.ITEM_02.designName),
        BackDropUnderSheetInformation(SheetItemName.ITEM_03.designName)
    )
}

enum class SheetItemName(val designName: String){
    ITEM_01("item_1"),
    ITEM_02("item_2"),
    ITEM_03("item_3"),
    ITEM_04("item_4"),
    ITEM_05("item_5"),
    ITEM_06("item_6"),
    ITEM_07("item_7"),
    ITEM_08("item_8"),
    ITEM_09("item_9"),
    ITEM_10("item_10"),
    ITEM_11("item_11"),
    ITEM_12("item_12")
}

enum class BackDropType(val backDropTypeName: String){
    TYPE_01("type01"),
    TYPE_02("type02")
}