package com.chibimaker.create.avatar.cutechibi.model

import com.example.st099_ghostocmaker.Model.LayerModel

data class ChibiModel(
    val id: Int,
    val name: String,
    val avatar: String,
    val bowl: ArrayList<LayerModel> = arrayListOf(),
    val effect: ArrayList<LayerModel> = arrayListOf(),
    val tail: ArrayList<LayerModel> = arrayListOf(),
    val backhair: ArrayList<LayerModel> = arrayListOf(),
    val body: ArrayList<LayerModel> = arrayListOf(),
    val mouth: ArrayList<LayerModel> = arrayListOf(),
    val eye: ArrayList<LayerModel> = arrayListOf(),
    val eyebrow: ArrayList<LayerModel> = arrayListOf(),
    val middlehair: ArrayList<LayerModel> = arrayListOf(),
    val sidehair: ArrayList<LayerModel> = arrayListOf(),
    val glasses: ArrayList<LayerModel> = arrayListOf(),
    val fronthair: ArrayList<LayerModel> = arrayListOf(),
    val topofhair: ArrayList<LayerModel> = arrayListOf(),
    val accessory: ArrayList<LayerModel> = arrayListOf(),
    val sock: ArrayList<LayerModel> = arrayListOf(),
    val shoe: ArrayList<LayerModel> = arrayListOf(),
    val pant: ArrayList<LayerModel> = arrayListOf(),
    val shirt: ArrayList<LayerModel> = arrayListOf(),
    val hat: ArrayList<LayerModel> = arrayListOf(),
)