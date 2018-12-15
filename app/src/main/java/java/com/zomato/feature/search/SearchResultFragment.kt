package java.com.zomato.feature.search


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection

import java.com.zomato.R
import java.com.zomato.di.ViewModelFactory
import javax.inject.Inject


class SearchResultFragment : Fragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private var searchViewModel: SearchViewModel? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    //SearchFragmentDataBinding
    return inflater.inflate(R.layout.fragment_search_result, container, false)
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    AndroidSupportInjection.inject(this)
    searchViewModel = activity?.let {
      ViewModelProviders.of(it, viewModelFactory)
        .get(SearchViewModel::class.java)
    }
  }
}
