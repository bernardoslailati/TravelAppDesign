package com.slailati.android.travelappdesign.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.slailati.android.travelappdesign.R
import com.slailati.android.travelappdesign.databinding.ItemCategoryBinding
import com.slailati.android.travelappdesign.model.CategoryModel
import com.slailati.android.travelappdesign.model.CategoryType.*

class CategoryAdapter(val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val holderItem = categoryList[position]
        holder.bind(holderItem)
    }

    override fun getItemCount(): Int = categoryList.size

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryModel) {
            binding.run {
                ivIcon.setImageDrawable(
                    when (item.type) {
                        HOUSE -> ContextCompat.getDrawable(context, R.drawable.ic_category_house)
                        CAMPING -> ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_category_camping
                        )
                        VILLA -> ContextCompat.getDrawable(context, R.drawable.ic_category_villa)
                        HOTEL -> ContextCompat.getDrawable(context, R.drawable.ic_category_hotel)
                    }
                )
                tvTitle.text = item.title
            }
        }
    }

    companion object {
        val categoryList: List<CategoryModel> = listOf(
            CategoryModel(HOUSE, "House"),
            CategoryModel(CAMPING, "Camping"),
            CategoryModel(VILLA, "Villa"),
            CategoryModel(HOTEL, "Hotel"),
        )
    }

}