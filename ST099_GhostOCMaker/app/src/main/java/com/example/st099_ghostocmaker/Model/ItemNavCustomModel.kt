package com.example.st099_ghostocmaker.Model


data class ItemNavCustomModel (
        val path: String,
        var isSelected: Boolean = false,
        val listImageColor: ArrayList<ItemColorImageModel> = arrayListOf()
)