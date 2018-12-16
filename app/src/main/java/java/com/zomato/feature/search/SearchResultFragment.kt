package java.com.zomato.feature.search


import android.app.Activity
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
import java.com.zomato.databinding.SearchFragmentDataBinding
import java.com.zomato.di.ViewModelFactory
import java.com.zomato.model.City
import javax.inject.Inject


class SearchResultFragment : Fragment(), SearchItemNavigator {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private var searchViewModel: SearchViewModel? = null

  lateinit var binding: SearchFragmentDataBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val binding = SearchFragmentDataBinding.inflate(inflater, container, false)
    binding.viewModel = searchViewModel
    binding.searchResultRv.adapter = SearchRecyclerAdapter(this)
    binding.searchResultRv.layoutManager = LinearLayoutManager(activity)
    return binding.root
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    AndroidSupportInjection.inject(this)
    searchViewModel = activity?.let {
      ViewModelProviders.of(it, viewModelFactory)
        .get(SearchViewModel::class.java)
    }
  }

  override fun onClick(city: City) {
    val returnIntent = Intent()
    returnIntent.putExtra(getString(R.string.city_object), city)
    activity?.setResult(Activity.RESULT_OK, returnIntent)
    activity?.finish()
  }

}
