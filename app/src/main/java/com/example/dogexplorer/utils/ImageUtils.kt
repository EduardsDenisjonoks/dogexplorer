package com.example.dogexplorer.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.dogexplorer.R
import timber.log.Timber

@BindingAdapter(value = ["app:url_image", "app:round_corners", "app:centre_crop"], requireAll = false)
fun AppCompatImageView.loadUrlImage(url: String?, roundCorners: Boolean = false, centerCrop: Boolean = true) {
    try {
        when {
            url.isNullOrBlank() -> Glide.with(this).clear(this)
            else -> Glide
                .with(this)
                .load(url)
                .apply {
                    when {
                        roundCorners && centerCrop -> {
                            this.transform(
                                CenterCrop(),
                                RoundedCorners(context.resources.getDimensionPixelSize(R.dimen.image_corners))
                            )
                        }
                        roundCorners && !centerCrop -> {
                            this.transform(
                                RoundedCorners(context.resources.getDimensionPixelSize(R.dimen.image_corners))
                            )
                        }
                        !roundCorners && centerCrop -> {
                            this.centerCrop()
                        }
                    }
                }
                .into(this)
        }
    } catch (ex: Exception) {
        Timber.e(ex, "Unable to load image url: $url")
    }
}
