package java.com.zomato.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.com.zomato.R


object Binder {

  @JvmStatic
  @BindingAdapter("imageUrl")
  fun loadImage(imageView: ImageView, imageUrl: String?) {
    GlideApp.with(imageView.context)
      .load(imageUrl)
      .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
      .error(R.drawable.fallback)
      .fallback(R.drawable.fallback)
      .into(imageView)
  }

  @JvmStatic
  @BindingAdapter("set_loading")
  fun setLoading(view: View, isLoading: Boolean) {
    if (isLoading)
      view.visibility = View.VISIBLE
    else
      view.visibility = View.GONE
  }
}