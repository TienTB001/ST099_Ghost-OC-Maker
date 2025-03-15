package com.example.st099_ghostocmaker.Model

data class LayerModel(
        val image: String,
        val isMoreColors: Boolean = false,
        var listColor: ArrayList<ColorModel> = arrayListOf()
)