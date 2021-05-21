package com.example.dogexplorer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dogexplorer.R
import com.example.dogexplorer.databinding.ListItemBreedBinding
import com.example.dogexplorer.repository.data.Breed

class BreedsAdapter constructor(private val showDetails: (Breed) -> (Unit)) :
    RecyclerView.Adapter<BreedsAdapter.BreedViewHolder>() {

    private val breeds = ArrayList<Breed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BreedViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.list_item_breed,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bindView(breeds[position])
    }

    override fun getItemCount(): Int = breeds.size

    fun setBreeds(newBreeds: List<Breed>) {
        val diffResult = DiffUtil.calculateDiff(
            DiffCallback(breeds, newBreeds)
        )
        breeds.clear()
        breeds.addAll(newBreeds)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class BreedViewHolder(private val binding: ListItemBreedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                showDetails(breeds[adapterPosition])
            }
        }

        fun bindView(breed: Breed) {
            binding.imgUrl = breed.images.firstOrNull()

            val breedName = breed.breedName
            val subBreed = breed.subBreedName ?: ""

            binding.breed = when {
                subBreed.isNotBlank() -> "$subBreed $breedName"
                else -> breedName
            }
        }
    }

    class DiffCallback(private var oldItems: List<Breed>, private var newItems: List<Breed>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
                    && oldItems[oldItemPosition].breedName == newItems[newItemPosition].breedName
                    && oldItems[oldItemPosition].subBreedName == newItems[newItemPosition].subBreedName

    }

}