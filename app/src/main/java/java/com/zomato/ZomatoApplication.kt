package java.com.zomato

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import java.com.zomato.di.component.DaggerAppComponent
import javax.inject.Inject

class ZomatoApplication : Application(), HasActivityInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    DaggerAppComponent.builder()
      .app(this)
      .build().inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return dispatchingAndroidInjector
  }
}