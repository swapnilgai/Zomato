package java.com.zomato.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import java.com.zomato.ZomatoApplication
import java.com.zomato.di.module.ActivityBindingModule
import java.com.zomato.di.module.AppModule
import java.com.zomato.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton

@Component(modules = [AppModule::class, ActivityBindingModule::class, ViewModelModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<ZomatoApplication> {
  fun injectj(application: ZomatoApplication)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun app(application: Application): Builder

    fun build(): AppComponent
  }
}