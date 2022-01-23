package com.slailati.android.travelappdesign.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.slailati.android.travelappdesign.R
import com.slailati.android.travelappdesign.databinding.ItemNearbyResidenceBinding
import com.slailati.android.travelappdesign.model.NearbyResidenceModel
import java.text.DecimalFormat
import java.util.*

class NearbyResidenceAdapter(val context: Context) :
    RecyclerView.Adapter<NearbyResidenceAdapter.NearbyResidenceViewHolder>() {

    inner class NearbyResidenceViewHolder(private val binding: ItemNearbyResidenceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NearbyResidenceModel) {
            binding.run {
                ivPhotoNearbyResidence.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        item.photoDrawableId
                    )
                )
                tvNameNearbyResidence.text = item.name
                tvLocationNearbyResidence.text = item.locationName
                tvRatingValueNearbyResidence.text = item.placeRating.toString()
                tvValuePerDayNearbyResidence.text = context.getString(
                    R.string.nearby_residence_value_per_day,
                    try {
                        currencyFormatter.format(item.valuePerDay.toDouble())
                    } catch (e: IllegalFormatException) {
                        "?"
                    }
                )

                setFavoriteIcon(item.isFavorite)
                cvFavoriteNearbyResidence.setOnClickListener {
                    item.isFavorite = !item.isFavorite
                    setFavoriteIcon(item.isFavorite)
                }
            }
        }

        private fun ItemNearbyResidenceBinding.setFavoriteIcon(isFavorite: Boolean) {
            ivFavoriteNearbyResidence.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    if (isFavorite)
                        R.drawable.ic_favorite
                    else
                        R.drawable.ic_favorite_cleared
                )
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NearbyResidenceViewHolder {
        val binding =
            ItemNearbyResidenceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return NearbyResidenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NearbyResidenceViewHolder, position: Int) {
        val holderItem = nearbyResidenceList[position]
        holder.bind(holderItem)
    }

    override fun getItemCount(): Int = nearbyResidenceList.size

    companion object {
        val currencyFormatter: DecimalFormat = DecimalFormat("###,###")
        val nearbyResidenceList: List<NearbyResidenceModel> = listOf(
            NearbyResidenceModel(
                R.drawable.bg_nearby_residence_camp_batu_gede,
                "Camp Batu Gede",
                "Cisarua, Bogor",
                150_000F,
                4.9F,
                true
            ),
            NearbyResidenceModel(
                R.drawable.bg_nearby_residence_camp_hulu_cai,
                "Hulu Cai",
                "Cibeduk, Bogor",
                150_000F,
                4.9F,
                true
            ),
        )
    }
}