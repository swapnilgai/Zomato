package java.com.zomato.feature.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import java.com.zomato.model.City

object SearchItemBinder {

  @JvmStatic
  @BindingAdapter("items")
  fun setSchoolList(view: RecyclerView, list: List<City>) {
    if (view.adapter is SearchRecyclerAdapter) {
      val adapter = (view.adapter as SearchRecyclerAdapter)
      adapter.let {
        it.clear()
        it.addAll(list)
        it.notifyDataSetChanged()
      }
    }
  }
}