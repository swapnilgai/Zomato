package java.com.zomato.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import java.com.zomato.di.Scope.ActivityScope
import java.com.zomato.feature.search.SearchActivity
import java.com.zomato.feature.search.SearchResultFragment

@Module
abstract class ActivityBindingModule {

  @ActivityScope
  @ContributesAndroidInjector
  abstract fun contributeSearchActivity() : SearchActivity

  @ActivityScope
  @ContributesAndroidInjector
  abstract fun contributeSearchResultFragment() : SearchResultFragment
}