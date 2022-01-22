package com.slailati.android.travelappdesign.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.slailati.android.travelappdesign.R
import com.slailati.android.travelappdesign.databinding.ItemPopularPlaceBinding
import com.slailati.android.travelappdesign.model.PopularPlaceModel
import com.slailati.android.travelappdesign.ui.adapter.PopularPlaceAdapter.PopularPlaceViewHolder

class PopularPlaceAdapter (val context: Context) :
    RecyclerView.Adapter<PopularPlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularPlaceViewHolder {
        val binding =
            ItemPopularPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularPlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularPlaceViewHolder, position: Int) {
        val holderItem = popularPlaceList[position]
        holder.bind(holderItem)
    }

    override fun getItemCount(): Int = popularPlaceList.size

    inner class PopularPlaceViewHolder(private val binding: ItemPopularPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PopularPlaceModel) {
            binding.run {
                ivPhotoPopularPlace.setImageDrawable(ContextCompat.getDrawable(context, item.photoDrawableId))
                tvNamePopularPlace.text = item.name
                tvRatingValuePopularPlace.text = item.rating.toString()
            }
        }
    }

    companion object {
        val popularPlaceList: List<PopularPlaceModel> = listOf(
            PopularPlaceModel(R.drawable.bg_popular_royal_hotel_bogor, "Royal Hotel Bogor", 4.9F),
            PopularPlaceModel(R.drawable.bg_popular_bumi_katulampa, "Bumi katulampa", 4.9F),
            PopularPlaceModel(R.drawable.bg_popular_villa_sawah, "Villa Sawah", 4.9F)
        )
    }

}