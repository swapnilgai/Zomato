package java.com.zomato.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import java.com.zomato.di.ViewModelFactory
import java.com.zomato.di.ViewModelKey
import java.com.zomato.feature.rdp.RdpViewModel
import java.com.zomato.feature.search.SearchViewModel
import java.com.zomato.feature.srp.SrpViewModel

@Module
abstract class ViewModelModule {

  @IntoMap
  @ViewModelKey(SearchViewModel::class)
  @Binds
  abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

  @IntoMap
  @ViewModelKey(SrpViewModel::class)
  @Binds
  abstract fun bindSrpViewModel(srpViewModel: SrpViewModel): ViewModel

  @IntoMap
  @ViewModelKey(RdpViewModel::class)
  @Binds
  abstract fun bindRdpViewModel(rdpViewModel: RdpViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}