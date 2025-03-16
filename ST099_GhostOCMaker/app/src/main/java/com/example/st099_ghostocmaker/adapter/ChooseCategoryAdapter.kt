package com.chibimaker.create.avatar.cutechibi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chibimaker.create.avatar.cutechibi.extension.pxToDp
import com.chibimaker.create.avatar.cutechibi.model.CategoryDataModel
import com.chibimaker.create.avatar.cutechibi.model.CategoryModel
import com.example.st099_ghostocmaker.databinding.ItemChooseCategoryBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChooseCategoryAdapter(val context: Context) : RecyclerView.Adapter<ChooseCategoryAdapter.ChooseCategoryViewAdapter>() {
    var listCategory: ArrayList<CategoryModel> = arrayListOf()

    inner class ChooseCategoryViewAdapter(val binding: ItemChooseCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryModel, position: Int) {
            val listLayer: ArrayList<CategoryDataModel?> = arrayListOf(
                item.bowl,
                item.effect,
                item.tail,
                item.backhair,
                item.body,
                item.mouth,
                item.eye,
                item.eyebrow,
                item.middlehair,
                item.sidehair,
                item.glasses,
                item.fronthair,
                item.topofhair,
                item.sock,
                item.shoe,
                item.pant,
                item.shirt,
                item.hat,
            )

            /*CoroutineScope(Dispatchers.Main).launch {
                // Thực hiện việc tải hình ảnh trong luồng nền
                val bitmap = withContext(Dispatchers.IO) {
                    try {
                        // Lấy Bitmap từ Glide
                        Glide.with(context)
                            .load(item.eye?.pathKey)
                            .submit()
                            .get()  // Gọi phương thức này trên luồng nền
                    } catch (e: Exception) {
                        Log.e("GlideError", "Error loading image: ${e.message}")
                        null  // Xử lý lỗi nếu cần
                    }
                }

                // Cập nhật UI trên luồng chính
                bitmap?.let {
                    Glide.with(context)
                        .load(it)
                        .into(binding.pathEye)  // Tải Bitmap vào ImageView
                }
            }*/


            binding.apply {
//                val listCustomView = arrayListOf(
//                    pathBowl,        // 0. bowl
//                    pathEffect,      // 1. effect
//                    pathTail,        // 2. tail
//                    pathBackhair,    // 3. backhair
//                    pathBody,        // 4. body
//                    pathMouth,       // 5. mouth
//                    pathEye,         // 6. eye
//                    pathEyeBrow,     // 7. eye brow
//                    pathMiddleHair,  // 8. middle hair
//                    pathSideHair,    // 9. side hair
//                    pathGlasses,     // 10. glasses
//                    pathFrontHair,   // 11. front hair
//                    pathTopOfHair,   // 12. top of hair
//                    pathSock,        // 13. sock
//                    pathShoe,        // 14. shoe
//                    pathPant,        // 15. pant
//                    pathShirt,       // 16. shirt
//                    pathHat          // 17. hat
//                )

//                for (i in 0 until listLayer.size) {
//                    if (listLayer[i] != null) {
//                        Glide.with(binding.root).load(listLayer[i]!!.path).into(listCustomView[i])
//                    }
//
//                }
                val listBitmap: ArrayList<Bitmap?> = arrayListOf()
                var width: Int? = null
                var height: Int? = null
                val handleExceptionCoroutine = CoroutineExceptionHandler { _, throwable ->
                    Log.e("nbhieu", "e: ${throwable}")
                }
                CoroutineScope(SupervisorJob() + Dispatchers.IO + handleExceptionCoroutine).launch {
                    val job1 = async {
                        for (i in 0 until listLayer.size) {
                            if (listLayer[i] != null) {
                                val bitmap = Glide.with(context).load(listLayer[i]!!.path)
                                    .submit().get().toBitmap()
                                listBitmap.add(bitmap)

                            } else {
                                listBitmap.add(null)
                            }

                        }
                        return@async (listLayer.size == listBitmap.size)
                    }
                    val job2 = async {
                        if (job1.await()) {
                            width = pxToDp(context, 300)
                            height = pxToDp(context, 300)
                        }
                        return@async true
                    }
                    launch(Dispatchers.Main) {
                        if (job1.await() && job2.await()) {

                            val combinedBitmap = Bitmap.createBitmap(pxToDp(context, 300), pxToDp(context, 300), Bitmap.Config.ARGB_8888)
                            val canvas = Canvas(combinedBitmap)

                            for (i in 0 until listBitmap.size) {
                                if (listBitmap[i] != null) {
                                    val bitmap = listBitmap[i]!!
                                    val left = (width!! - bitmap.width) / 2f
                                    val top = (height!! - bitmap.height) / 2f
                                    canvas.drawBitmap(bitmap, left, top, null)

                                }
                            }
                            ctView.setImageBitmap(combinedBitmap)
                        }
                    }
                }


            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCategoryViewAdapter {
        return ChooseCategoryViewAdapter(ItemChooseCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    override fun onBindViewHolder(holder: ChooseCategoryViewAdapter, position: Int) {
        val item = listCategory[position]
        holder.bind(item, position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<CategoryModel>) {
        listCategory.clear()
        listCategory.addAll(list)
        notifyDataSetChanged()
    }


}