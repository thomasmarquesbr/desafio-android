package com.thomas.components

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageUrl", "placeHolder")
fun loadImage(view: CircleImageView, url: String?, placeHolder: Drawable? = null) {
    if (url.isNullOrEmpty().not()) {
        Glide.with(view.context).load(url).placeholder(placeHolder).into(view)
    } else {
        if (placeHolder != null) {
            view.setImageDrawable(placeHolder)
        }
    }
}
