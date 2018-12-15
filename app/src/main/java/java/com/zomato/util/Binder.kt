package java.com.zomato.util

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
  fun loadPropertyImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView)
      .load(imageUrl)
      .apply(options)
      .into(imageView);
  }

}