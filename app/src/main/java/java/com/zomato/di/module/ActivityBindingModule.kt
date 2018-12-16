package java.com.zomato.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import java.com.zomato.di.Scope.ActivityScope
import java.com.zomato.feature.MainActivity
import java.com.zomato.feature.search.SearchActivity
import java.com.zomato.feature.search.SearchResultFragment
import java.com.zomato.feature.srp.SrpFragment

@Module
abstract class ActivityBindingModule {

  @ActivityScope
  @ContributesAndroidInjector
  abstract fun contributeSearchActivity(): SearchActivity

  @ActivityScope
  @ContributesAndroidInjector
  abstract fun contributeSearchResultFragment(): SearchResultFragment

  @ActivityScope
  @ContributesAndroidInjector
  abstract fun contributeMainActivity(): MainActivity

  @ActivityScope
  @ContributesAndroidInjector
  abstract fun contributeSrpFragment(): SrpFragment
}