package java.com.zomato.feature.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_search.container
import kotlinx.android.synthetic.main.activity_search.search_edit_text
import java.com.zomato.R
import java.com.zomato.di.ViewModelFactory
import java.com.zomato.util.afterTextChanged
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private var searchViewModel: SearchViewModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)
    setContentView(R.layout.activity_search)

    searchViewModel = ViewModelProviders.of(this, viewModelFactory)
      .get(SearchViewModel::class.java)

    search_edit_text.afterTextChanged { s: String -> searchViewModel?.getSearchResult(s) }

    supportFragmentManager.beginTransaction().add(container.id, SearchResultFragment()).commit()
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment> {
    return supportFragmentInjector
  }
}
