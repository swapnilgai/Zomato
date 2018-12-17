package java.com.zomato.feature.rdp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import java.com.zomato.R
import java.com.zomato.databinding.DetailDataBinding
import java.com.zomato.di.ViewModelFactory
import java.com.zomato.model.Restaurant
import javax.inject.Inject

class RdpActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private var rdpViewModel: RdpViewModel? = null

  lateinit var binding: DetailDataBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_rdp)

    rdpViewModel = ViewModelProviders.of(this, viewModelFactory)
      .get(RdpViewModel::class.java)

    binding.viewModel = rdpViewModel

    val restaurant =
      intent.getParcelableExtra(getString(R.string.restaurant_object)) as Restaurant
    restaurant.id?.toLong()?.let {
      rdpViewModel?.restaurant?.set(restaurant)
      rdpViewModel?.getDetail(it)
    }
  }

}
