package java.com.zomato.feature.srp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.com.zomato.R
import java.com.zomato.databinding.SrpItemDataBinding
import java.com.zomato.model.Restaurant
import java.com.zomato.util.ArrayRecyclerAdapter

class SrpRecyclerAdapter(private val srpItemNavigator: SrpItemNavigator) :
  ArrayRecyclerAdapter<Restaurant, ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    val viewBinding = DataBindingUtil.inflate<SrpItemDataBinding>(
      LayoutInflater.from(parent.context),
      R.layout.srp_item,
      parent,
      false
    )
    return DataResultHolder(viewBinding)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder is DataResultHolder) {
      holder.binding.item = getItem(position)
      holder.binding.navigator = srpItemNavigator
    }
  }

  override fun getItemViewType(position: Int): Int {
    return position
  }

  inner class DataResultHolder(val binding: SrpItemDataBinding) :
    RecyclerView.ViewHolder(binding.root)
}