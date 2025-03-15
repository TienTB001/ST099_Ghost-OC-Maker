package com.example.st099_ghostocmaker.Language

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.st099_ghostocmaker.R
import com.example.st099_ghostocmaker.databinding.ItemLanguageBinding

class LanguageAdapter(

    private val languages: List<LanguageModel>,
    private val onLanguageSelected: (LanguageModel) -> Unit

) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    inner class LanguageViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: LanguageModel) {
            binding.txtName.text = language.name
            Glide.with(binding.root).load(language.flagResId).into(binding.imgName)
            binding.txtName.setTextColor(Color.WHITE)

            if (language.isSelected) {
                binding.liner.setBackgroundResource(R.drawable.bg_language_selected)
                Glide.with(binding.root).load(R.drawable.icon0).into(binding.imgcheck1)
            } else {
                binding.liner.setBackgroundResource(R.drawable.item_back)
                Glide.with(binding.root).load(R.drawable.icon1).into(binding.imgcheck1)

                binding.imgcheck1.setOnClickListener(null)
            }

            binding.root.setOnClickListener {
                languages.forEach {
                    it.isSelected = false
                }
                language.isSelected = true
                notifyDataSetChanged()
            }
            binding.imgcheck1.setOnClickListener {
                languages.forEach {
                    it.isSelected = false
                }
                language.isSelected = true
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding =
            ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)

    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(languages[position])
    }

    override fun getItemCount(): Int = languages.size
}
