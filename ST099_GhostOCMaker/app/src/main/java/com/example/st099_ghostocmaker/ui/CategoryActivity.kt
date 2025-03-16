package com.example.st099_ghostocmaker.ui

import androidx.appcompat.app.AppCompatActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.chibimaker.create.avatar.cutechibi.adapter.ChooseCategoryAdapter
import com.chibimaker.create.avatar.cutechibi.extension.handleBack
import com.chibimaker.create.avatar.cutechibi.extension.restartActivity
import com.chibimaker.create.avatar.cutechibi.extension.stopActivity
import com.chibimaker.create.avatar.cutechibi.model.CategoryModel
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.FROM_CATEGORY
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.INTENT_KEY
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.isFromOtherScreen
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.onSingleClickOrigin
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.onSingleClickOut
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.setLocale
import com.example.st099_ghostocmaker.R
import com.example.st099_ghostocmaker.Ultils.DataLocal
import com.example.st099_ghostocmaker.Ultils.DataLocal.getListCategory
import com.example.st099_ghostocmaker.baseActivity.BaseActivity
import com.example.st099_ghostocmaker.databinding.ActivityCategoryBinding
import kotlin.math.abs

class CategoryActivity : BaseActivity<ActivityCategoryBinding>() {
    private val categoryAdapter by lazy {
        ChooseCategoryAdapter(this@CategoryActivity)
    }
    var pos = 0
    lateinit var item: CategoryModel
    private val listCategory: ArrayList<CategoryModel> = arrayListOf()

    override fun setViewBinding(): ActivityCategoryBinding {
        return ActivityCategoryBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        initData()
//        initMusic(binding.btnMusic)
    }

    override fun viewListener() {
        binding.apply {
            btnBack.onSingleClickOrigin {
                handleBack()
            }
            btnMusic.onSingleClickOrigin {
//                handleMusic(btnMusic)
            }
            btnChoose.onSingleClickOut {
                handleCategoryClick(item)
            }
        }
        handleRcv()
    }

    override fun dataObservable() {

    }

    override fun initText() {

    }

    private fun initData() {
        val getPath = intent.getStringExtra(INTENT_KEY)
//        if (getPath != null) {
            listCategory.addAll(getListCategory(this@CategoryActivity))
            initRcv()
//        }
    }

    private fun initRcv() {
        binding.apply {
            viewPager.adapter = categoryAdapter
            categoryAdapter.submitList(listCategory)

            /*val dialogLoading = DialogLoading(this@CategoryActivity)
            setLocale(this@CategoryActivity)
            dialogLoading.show()*/
            if (listCategory.size % 2 == 0) {
                pos = listCategory.size / 2 - 1
            } else {
                pos = listCategory.size / 2
            }
            item = listCategory[pos]
            viewPager.offscreenPageLimit = 3

            viewPager.currentItem = pos
            val handle = Handler()
            handle.postDelayed({
//                dialogLoading.dismiss()
            }, 2000)


            var transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            transformer.addTransformer { page, position ->
                var r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.14f
            }
            binding.viewPager.setPageTransformer(transformer)

        }
    }

    private fun handleRcv() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                pos = position
                item = listCategory[position]
            }
        })


    }

    private fun handleCategoryClick(itemCategory: CategoryModel) {
        /*val intent = Intent(this@CategoryActivity, EditActivity::class.java)
        intent.putExtra(INTENT_KEY, itemCategory.avatarChibi)
        intent.putExtra(FROM_CATEGORY, FROM_CATEGORY)
        dataChooseCategory = itemCategory
        isFromOtherScreen = true
        startActivity(intent)*/
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onStop() {
        super.onStop()
        stopActivity()
    }

    override fun onRestart() {
        super.onRestart()
        restartActivity()
//        initMusic(binding.btnMusic)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        handleBack()
    }
}