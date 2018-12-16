package java.com.zomato.feature.srp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import java.com.zomato.feature.search.SearchRecyclerAdapter
import java.com.zomato.model.Restaurant

object SrpItemBinder {
  @JvmStatic
  @BindingAdapter("items")
  fun setSrpList(view: RecyclerView, list: List<Restaurant>) {
    if (view.adapter is SearchRecyclerAdapter) {
      val adapter = (view.adapter as SrpRecyclerAdapter)
      adapter.let {
        it.clear()
        it.addAll(list)
        it.notifyDataSetChanged()
      }
    }
  }
}