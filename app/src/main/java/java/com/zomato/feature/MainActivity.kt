package java.com.zomato.feature

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.search_tv
import java.com.zomato.R
import java.com.zomato.R.layout
import java.com.zomato.di.ViewModelFactory
import java.com.zomato.feature.search.SearchActivity
import java.com.zomato.feature.srp.SrpViewModel
import java.com.zomato.model.City
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private var srpViewModel: SrpViewModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    srpViewModel = ViewModelProviders.of(this, viewModelFactory)
      .get(SrpViewModel::class.java)

    search_tv.setOnClickListener {
      //Intent(this, SearchActivity::class.java).putExtra("", it.toString())
      startActivityForResult(Intent(this, SearchActivity::class.java), 1)

//      it.text?.let {
//      }
    }
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment> {
    return supportFragmentInjector
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == 1) {
      if (resultCode == Activity.RESULT_OK) {
        val result: City? = data?.getParcelableExtra(getString(R.string.city_object))
        result?.id?.let {
          srpViewModel?.getSrpResult(it)
        }
      }
    }
  }
}
