package com.komeyama.sample.design.material.ui.backdropfragment

import com.komeyama.sample.design.material.R
import com.komeyama.sample.design.material.ui.backdropfragment.BackDropRecycleView.BackDropSheetInformation

class BackDropData {
    val backdropDummyItems: List<BackDropSheetInformation> = listOf(
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_01.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_02.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_03.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_04.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_05.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_06.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_07.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_08.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_09.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_background, SheetItemName.ITEM_10.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_11.designName),
        BackDropSheetInformation(R.drawable.ic_launcher_foreground, SheetItemName.ITEM_12.designName)
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