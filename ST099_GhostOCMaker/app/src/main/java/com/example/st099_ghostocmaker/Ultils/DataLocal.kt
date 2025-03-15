package com.example.st099_ghostocmaker.Ultils

import android.content.Context
import android.os.Build.VERSION_CODES.P
import com.example.st099_ghostocmaker.Model.ColorModel
import com.example.st099_ghostocmaker.Model.GhostModel
import com.example.st099_ghostocmaker.Model.LayerModel

object DataLocal {
    val PATH1 = "file:///android_asset/data/ghost1"
    val PATH2 = "file:///android_asset/data/ghost2"
    val PATH3 = "file:///android_asset/data/ghost3"
    val PATH4 = "file:///android_asset/data/ghost4"
    val PATH5 = "file:///android_asset/data/ghost5"
    val PATH6 = "file:///android_asset/data/ghost6"
    val PATH7 = "file:///android_asset/data/ghost6"
    val PATH8 = "file:///android_asset/data/ghost8"
    val PATH9 = "file:///android_asset/data/ghost9"
    val PATH10 = "file:///android_asset/data/ghost10"
    val PATH11 = "file:///android_asset/data/ghost11"
    val PATH12 = "file:///android_asset/data/ghost12"
    val PATH13 = "file:///android_asset/data/ghost13"
    val PATH14 = "file:///android_asset/data/ghost14"
    val PATH15 = "file:///android_asset/data/ghost15"
    val PATH16 = "file:///android_asset/data/ghost16"

    val GHOST1 = "data/ghost1"
    val GHOST2 = "data/ghost2"
    val GHOST3 = "data/ghost3"
    val GHOST4 = "data/ghost4"
    val GHOST5 = "data/ghost5"
    val GHOST6 = "data/ghost6"
    val GHOST7 = "data/ghost7"
    val GHOST8 = "data/ghost8"
    val GHOST9 = "data/ghost9"
    val GHOST10 = "data/ghost10"
    val GHOST11 = "data/ghost11"
    val GHOST12 = "data/ghost12"
    val GHOST13 = "data/ghost13"
    val GHOST14 = "data/ghost14"
    val GHOST15 = "data/ghost15"
    val GHOST16 = "data/ghost16"

    val PATH_BACKKGROUND = "file:///android_asset/background"

    fun dataGhost(context: Context): ArrayList<GhostModel> {
        val dataGhost: ArrayList<GhostModel> = arrayListOf(
            GhostModel(
                id = 1,
                name = "Ghost 1",
                accessories = getData(6, PATH1, "accessories"),
                bg = getData(9, PATH1, "bg"),
                body = getData( 14, PATH1, "body"),
                bodyPaint = getData(12, PATH1, "bodyPaint"),
                eyes = getData( 12, PATH1, "eyes"),
                lapelPin = getData( 19, PATH1, "labelpin"),
                legs = getData( 7, PATH1, "legs"),
                shoes = getData( 12, PATH1, "shoes"),
                socks = getData( 9, PATH1, "socks"),
            ),
            GhostModel(
                id = 2,
                name = "Ghost 2",
                backHair = getData(20, PATH2, "backHair"),
                body = getData(6, PATH2, "body"),
                eyes = getData( 6, PATH2, "eyes"),
                frontHair =  getData(44, PATH2, "fronthair"),
                glass = getData( 11, PATH2, "glass"),
                hat = getData( 35, PATH2, "hat"),
                head = getData( 4, PATH2, "head"),
                mouth = getData( 8, PATH2, "mouth"),
            ),
            GhostModel(
                id = 3,
                name = "Ghost 3",
                bg = getData(2, PATH3, "bg"),
                bg1 = getData( 2, PATH3, "bg1"),
                body = getData(1, PATH3, "body"),
                hat = getData( 8, PATH3, "hat"),
            ),
            GhostModel(
                id = 4,
                name = "Ghost 4",
                accessories = getData(12, PATH4, "accessories"),
                bg = getData(56, PATH4, "bg"),
                blush = getData( 9, PATH4, "blush"),
                body = getData(54, PATH4, "body"),
                ear = getData( 19, PATH4, "ear"),
                eyes = getData( 10, PATH4, "eyes"),
                mouth = getData( 10, PATH4, "mouth"),
                shoes = getData( 12, PATH4, "shoes"),
                // thieu
                socks = getData( 9, PATH4, "socks"),
            ),
            GhostModel(
                id = 5,
                name = "Ghost 5",
                bg = getData(13, PATH5, "bg"),
                blush = getData( 6, PATH5, "blush"),
                body = getData(3, PATH5, "body"),
                ear = getData( 12, PATH5, "ear"),
                effect = getData( 6, PATH5, "effect"),
                eyes = getData( 24, PATH5, "eyes"),
                hair = getData( 7, PATH5, "hair"),
                hands = getData( 5, PATH5, "hands"),
                mouth = getData( 29, PATH5, "mouth"),
            ),

            GhostModel(
                id = 6,
                name = "Ghost 6",
                bg = getData(6, PATH6, "bg"),
                charm = getData( 22, PATH6, "charm"),
                cloak = getData(40, PATH6, "cloak"),
                collar = getData( 20, PATH6, "collar"),
                effect = getData( 28, PATH6, "effect"),
                eyes = getData( 19, PATH6, "eyes"),
                facePaint = getData( 25, PATH6, "facepaint"),
                head = getData( 12, PATH6, "head"),
                horns = getData( 62, PATH6, "horns"),
                horns1 = getData( 27, PATH6, "horns1"),
                nail = getData( 17, PATH6, "nail"),
                pet = getData( 12, PATH6, "pet"),
                wings = getData( 12, PATH6, "wings"),
            ),
            GhostModel(
                id = 7,
                name = "Ghost 7",
                accessories = getData(3, PATH7, "accessories"),
                bg = getData( 9, PATH7, "bg"),
                body = getData(7, PATH7, "body"),
                eyebrows = getData( 4, PATH7, "eyebrows"),
                eyes = getData( 10, PATH7, "eyes"),
                facePaint = getData( 4, PATH7, "facepaint"),
                hair = getData( 4, PATH7, "hair"),
                hat = getData( 11, PATH7, "hat"),
                mouth = getData( 18, PATH7, "mouth"),
                whisker = getData( 4, PATH7, "whisker"),
            ),
            GhostModel(
                id = 8,
                name = "Ghost 8",
                accessories = getData(4, PATH8, "accessories"),
                bg = getData( 11, PATH8, "bg"),
                blush = getData(4, PATH8, "blush"),
                body = getData(14, PATH8, "body"),
                eyes = getData( 6, PATH8, "eyes"),
                facePaint = getData( 3, PATH8, "facepaint"),
                glass = getData( 3, PATH8, "glass"),
                hands = getData( 2, PATH8, "hands"),
                hat = getData( 12, PATH8, "hat"),
                head = getData( 8, PATH8, "head"),
                mouth = getData( 9, PATH8, "mouth"),
                wings = getData( 2, PATH8, "wings"),
            ),GhostModel(
                id = 9,
                name = "Ghost 9",
                armFringe = getData(60, PATH9, "armfringe"),
                bg = getData( 4, PATH9, "bg"),
                body = getData(24, PATH9, "body"),
                collar = getData( 24, PATH9, "collar"),
                effect = getData( 14, PATH9, "effect"),
                eyeHoles = getData( 16, PATH9, "eyeholes"),
                eyes = getData( 21, PATH9, "eyes"),
                fringe = getData( 61, PATH9, "fringe"),
                glass = getData( 16, PATH9, "glass"),
                hands = getData( 28, PATH9, "hands"),
                hat = getData( 36, PATH9, "hat"),
                legs = getData( 14, PATH9, "legs"),
                mouth = getData( 31, PATH9, "mouth"),
                shadow = getData( 4, PATH9, "shadow"),
                tail = getData( 9, PATH9, "tail"),
                wings = getData( 4, PATH9, "wings"),
            ),GhostModel(
                id = 10,
                name = "Ghost 10",
                accessories = getData(10, PATH10, "accessories"),
                bg = getData( 7, PATH10, "bg"),
                bg1 = getData(10, PATH10, "bg1"),
                blush = getData( 40, PATH10, "blush"),
                body = getData( 32, PATH10, "body"),
                collar = getData(10, PATH10,"collar"),
                effect = getData( 9, PATH10, "effect"),
                eyebrows = getData( 16, PATH10, "eyebrows"),
                eyes = getData( 33, PATH10, "eyes"),
                hat = getData( 40, PATH10, "hat"),
                leftHand = getData( 13, PATH10, "lefthand"),
                mouth = getData( 36, PATH10, "mouth"),
                nose= getData( 9, PATH10, "nose"),
                other = getData( 11, PATH10, "other"),
                rightHand = getData( 13, PATH10, "righthand"),
            ),GhostModel(
                id = 11,
                name = "Ghost 11",
                accessories = getData(5, PATH11, "accessories"),
                body = getData( 7, PATH11, "body"),
                ear = getData(7, PATH11, "ear"),
                eyes = getData( 7, PATH11, "eyes"),
                glass = getData( 2, PATH11, "glass"),
                hat = getData( 8, PATH11, "hat"),
                lapelPin = getData( 9, PATH11, "labelpin"),
                wings = getData( 2, PATH11, "wings"),
            ),
            GhostModel(
                id = 12,
                name = "Ghost 12",
                accessories = getData(2, PATH12, "accessories"),
                bg = getData( 35, PATH12, "bg"),
                blush = getData( 9, PATH12, "blush"),
                body = getData( 6, PATH11, "body"),
                collar = getData( 4, PATH12, "collar"),
                eyes = getData( 7, PATH12, "eyes"),
                glass = getData( 16, PATH12, "glass"),
                hands = getData( 4, PATH12, "eyebrows"),
                hat = getData( 11, PATH12, "hat"),
                mouth = getData( 3, PATH12, "mouth"),
                other = getData( 4, PATH12, "other"),
            ),
            GhostModel(
                id = 13,
                name = "Ghost 13",
                accessories = getData(7, PATH13, "accessories"),
                bg = getData( 14, PATH13, "bg"),
                body = getData(10, PATH13, "body"),
                collar = getData( 9, PATH13, "collar"),
                effect = getData( 13, PATH13, "effect"),
                eyes = getData( 9, PATH13, "eyes"),
                facePaint = getData( 8, PATH13, "facepaint"),
                hands = getData( 7, PATH13, "hands"),
                hat = getData( 20, PATH13, "hat"),
                mouth = getData( 8, PATH13, "mouth"),
                wings = getData( 4, PATH13, "wings"),
            ),
            GhostModel(
                id = 14,
                name = "Ghost 14",
                bg = getData( 6, PATH14, "bg"),
                bg1 = getData(4, PATH14, "bg1"),
                blush = getData( 2, PATH14, "blush"),
                body = getData(6, PATH14, "body"),
                eyes = getData( 8, PATH14, "eyes"),
                hat = getData( 40, PATH14, "hat"),
                leftHand = getData( 10, PATH14, "lefthand"),
                mouth = getData( 9, PATH14, "mouth"),
                other = getData( 4, PATH14, "other"),
                rightHand = getData( 11, PATH14, "righthand"),
                wings = getData( 10, PATH14, "wings"),
            ),
            GhostModel(
                id = 15,
                name = "Ghost 15",
                accessories = getData(14, PATH15, "accessories"),
                bg = getData( 11, PATH15, "bg"),
                body = getData(9, PATH15, "body"),
                effect = getData( 6, PATH15, "effect"),
                eyes = getData( 21, PATH15, "eyes"),
                hands = getData( 7, PATH15, "blush"),
                horns = getData( 10, PATH15, "horns"),
                mouth = getData( 27, PATH15, "mouth"),
                shirt = getData( 11, PATH15, "shirt"),
            ),
            GhostModel(
                id = 16,
                name = "Ghost 16",
                bg = getData( 9, PATH16, "bg"),
                body = getData(12, PATH16, "body"),
                effect = getData( 9, PATH16, "effect"),
                eyes = getData( 11, PATH16, "eyes"),
                hat = getData( 11, PATH16, "hat"),
                horns = getData(28, PATH16, "horns"),
                mouth = getData( 23, PATH16, "mouth"),
            ),

        )
        return dataGhost
    }

    private fun getData(end: Int, path: String, folder: String, numColors: Int = 0): ArrayList<LayerModel> {
        val output = arrayListOf<LayerModel>()

        for (i in 1..end) {
            // Nếu không có nhiều lớp màu (numColors = 0), chỉ lấy ảnh mà không tạo danh sách màu sắc
            if (numColors == 0) {
                output.add(
                    LayerModel(
                        image = "$path/$folder/${folder}_$i.png",
                        isMoreColors = false,
                        listColor = arrayListOf()
                    )
                )
            } else {
                // Nếu có nhiều lớp màu, tạo danh sách ColorModel tương ứng với numColors
                val getListColor = arrayListOf<ColorModel>()
                for (j in 1..numColors) {
                    getListColor.add(ColorModel("#FFFFFF", "$path/$folder/${folder}$j/${folder}_$i.png"))
                }

                output.add(
                    LayerModel(
                        image = "$path/$folder/${folder}1/${folder}_$i.png",
                        isMoreColors = true,
                        listColor = getListColor
                    )
                )
            }
        }
        return output
    }

}
