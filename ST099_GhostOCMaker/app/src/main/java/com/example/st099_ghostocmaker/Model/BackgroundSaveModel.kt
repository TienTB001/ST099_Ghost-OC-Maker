package com.chibimaker.create.avatar.cutechibi.model

data class BackgroundSaveModel(
    val index: Int,

    val isColor: Boolean = true,
    // 0: Int, 1: String
    val type: Int,
    var backgroundSave: Any?,
    var isInternal: Boolean = false
)
