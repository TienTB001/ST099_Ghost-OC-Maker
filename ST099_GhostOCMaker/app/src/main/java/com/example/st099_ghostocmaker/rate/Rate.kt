package com.example.st099_ghostocmaker.rate

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.st099_ghostocmaker.R
import com.example.st099_ghostocmaker.databinding.FragmentRateBinding
import com.willy.ratingbar.BaseRatingBar
import com.willy.ratingbar.ScaleRatingBar

class Rate(val inVisibleCardView: ()-> Unit) : DialogFragment() {
    private lateinit var binding: FragmentRateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(R.drawable.image0).into(binding.imgIcon)
        binding.txRateTitle.setText(R.string.TitleRate)
        binding.txtRateDesc.setText(R.string.DescRate)

        binding.simpleRatingBar.setOnRatingChangeListenerExt { _, rating ->
            handleRating(rating.toInt(), requireContext())
        }
    }

    private fun handleRating(rating: Int, context: Context) {
        val ratingInfo = when (rating) {
            1 -> RatingInfo(R.drawable.image1, context.getString(R.string.onestart), context.getString(R.string.onestartdesc))
            2 -> RatingInfo(R.drawable.image2, context.getString(R.string.onestart), context.getString(R.string.onestartdesc))
            3 -> RatingInfo(R.drawable.image3, context.getString(R.string.onestart), context.getString(R.string.onestartdesc))
            4 -> RatingInfo(R.drawable.image4, context.getString(R.string.fowstart), context.getString(R.string.forstartdesc))
            5 -> RatingInfo(R.drawable.image5, context.getString(R.string.fowstart), context.getString(R.string.forstartdesc))
            else -> RatingInfo(R.drawable.image0, context.getString(R.string.TitleRate), context.getString(R.string.DescRate))
        }

        binding.imgIcon.setImageResource(ratingInfo.iconRes)
        binding.txRateTitle.text = ratingInfo.title
        binding.txtRateDesc.text = ratingInfo.desc

        Log.d("tien", ratingInfo.toString())
    }

    private fun ScaleRatingBar.setOnRatingChangeListenerExt(listener: (ratingBar: BaseRatingBar?, rating: Float) -> Unit) {
        this.setOnRatingChangeListener(object : BaseRatingBar.OnRatingChangeListener {
            override fun onRatingChange(ratingBar: BaseRatingBar?, rating: Float, fromUser: Boolean) {
                if (fromUser) {
                    listener(ratingBar, rating)
                }
            }
        })
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        inVisibleCardView()
    }

    // Cấu hình hiển thị DialogFragment (Tùy chọn)

}
