package java.com.zomato.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

  @Provides
  @Singleton
  fun providesPreferenceUtils(application: Application): SharedPreferences =
    PreferenceManager.getDefaultSharedPreferences(application)

  @Provides
  @Singleton
  fun providesContext(application: Application): Context = application.applicationContext
}
