package com.example.dogexplorer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dogexplorer.R
import com.example.dogexplorer.databinding.ListItemBreedImageBinding

class BreedImageAdapter : RecyclerView.Adapter<BreedImageAdapter.ImageViewHolder>() {

    private val images = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.list_item_breed_image,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(images[position])
    }

    override fun getItemCount(): Int = images.size

    fun setNewImages(newImages: List<String>) {
        val diffResult = DiffUtil.calculateDiff(
            DiffCallback(images, newImages)
        )
        images.clear()
        images.addAll(newImages)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ImageViewHolder(private val binding: ListItemBreedImageBinding) :
        RecyclerView.ViewHolder(binding.imageView) {

        fun bindView(image: String) {
            binding.imgUrl = image
        }
    }

    class DiffCallback(private var oldItems: List<String>, private var newItems: List<String>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}