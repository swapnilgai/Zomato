package java.com.zomato.feature.srp


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import java.com.zomato.R
import java.com.zomato.databinding.SrpFragmentDataBinding
import java.com.zomato.di.ViewModelFactory
import java.com.zomato.feature.rdp.RdpActivity
import java.com.zomato.model.Restaurant
import javax.inject.Inject


class SrpFragment : Fragment(), SrpItemNavigator {

  private var srpViewModel: SrpViewModel? = null

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding = SrpFragmentDataBinding.inflate(inflater, container, false)
    binding.viewModel = srpViewModel
    binding.srpResultRv.adapter = SrpRecyclerAdapter(this)
    binding.srpResultRv.layoutManager = LinearLayoutManager(activity)
    return binding.root
  }

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    srpViewModel = activity?.let {
      ViewModelProviders.of(it, viewModelFactory)
        .get(SrpViewModel::class.java)
    }
    super.onAttach(context)
  }

  override fun onClick(restaurant: Restaurant) {
    val intent = Intent(activity, RdpActivity::class.java).putExtra(
      getString(R.string.restaurant_object),
      restaurant
    )
    startActivity(intent)
  }
}
