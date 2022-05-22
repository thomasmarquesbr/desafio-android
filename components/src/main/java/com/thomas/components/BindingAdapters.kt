package com.thomas.components

import android.graphics.drawable.Drawable
import android.view.View
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

@BindingAdapter("app:visible_or_invisible")
fun setVisibleOrInvisible(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("app:visible_or_gone")
fun setVisibleOrGone(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.GONE
}
