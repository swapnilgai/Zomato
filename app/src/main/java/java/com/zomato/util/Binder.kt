package java.com.zomato.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


object Binder {

  private val options by lazy {
    RequestOptions()
      .centerCrop()
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .priority(Priority.HIGH)
      .dontAnimate()
      .dontTransform();
  }

  @JvmStatic
  @BindingAdapter("imageUrl")
  fun loadImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView)
      .load(imageUrl)
      .apply(options)
      .into(imageView);
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