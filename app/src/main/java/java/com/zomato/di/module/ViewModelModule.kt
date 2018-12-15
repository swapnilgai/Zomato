package java.com.zomato.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import java.com.zomato.di.Scope.ActivityScope
import java.com.zomato.di.ViewModelFactory
import java.com.zomato.di.ViewModelKey
import java.com.zomato.feature.search.SearchViewModel
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

  @IntoMap
  @ViewModelKey(SearchViewModel::class)
  @ActivityScope
  @Binds
  abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

  @Binds
  @Singleton
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}