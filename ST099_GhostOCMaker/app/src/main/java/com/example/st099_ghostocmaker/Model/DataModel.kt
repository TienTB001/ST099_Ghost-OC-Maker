package com.chibimaker.create.avatar.cutechibi.model

import com.example.st099_ghostocmaker.Model.ItemNavCustomModel

data class DataModel(
    val avatarId: String,
    var pathInternal: String? = null,
    val isFlip: Boolean = false,
    // focus item
    val listItemNav: ArrayList<ArrayList<ItemNavCustomModel>> = arrayListOf(),
    // focus color
    val listColorItemNav: ArrayList<ArrayList<ItemColorModel>> = arrayListOf(),
    // Bộ phận đã chọn
    val listPartChibiSelected: ArrayList<String> = arrayListOf(),
    // Danh sách key item đã chọn của từng bộ phận
    val listKeySelectedItem: ArrayList<String> = arrayListOf(),
    val listIsSelectedItem: ArrayList<Boolean> = arrayListOf(),
    val listPositionColorItem: ArrayList<Int> = arrayListOf(),
//    val backgroundSave: BackgroundSaveModel? = null
)
