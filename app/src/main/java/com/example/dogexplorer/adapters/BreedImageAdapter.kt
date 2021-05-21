package com.example.dogexplorer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dogexplorer.R
import com.example.dogexplorer.databinding.ListItemBreedImageBinding

class BreedImageAdapter constructor(
    private val images: List<String>
) : RecyclerView.Adapter<BreedImageAdapter.ImageViewHolder>() {


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


    inner class ImageViewHolder(private val binding: ListItemBreedImageBinding) :
        RecyclerView.ViewHolder(binding.imageView) {

        fun bindView(image: String) {
            binding.imgUrl = image
        }
    }

}