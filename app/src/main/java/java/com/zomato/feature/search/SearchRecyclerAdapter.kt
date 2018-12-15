package java.com.zomato.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.com.zomato.R
import java.com.zomato.databinding.SearchItemDataBinding
import java.com.zomato.model.City
import java.com.zomato.util.ArrayRecyclerAdapter


class SearchRecyclerAdapter(val searchItemNavigator: SearchItemNavigator) :
  ArrayRecyclerAdapter<City, ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    val viewBinding = DataBindingUtil.inflate<SearchItemDataBinding>(
      LayoutInflater.from(parent.context),
      R.layout.search_item,
      parent,
      false
    )
    return DataResultHolder(viewBinding)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    if (holder is DataResultHolder) {
      val searchItemNavigator = object : SearchItemNavigator {
        override fun onClick(city: City) {
          searchItemNavigator.onClick(city)
        }
      }
      holder.binding.item = getItem(position)
      holder.binding.navigator = searchItemNavigator
    }
  }

  override fun getItemViewType(position: Int): Int {
    return position
  }

  inner class DataResultHolder(val binding: SearchItemDataBinding) :
    RecyclerView.ViewHolder(binding.root)
}