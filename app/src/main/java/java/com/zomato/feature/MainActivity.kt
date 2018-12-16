package java.com.zomato.feature

import android.app.Activity
import android.os.Bundle
import java.com.zomato.R.layout

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
  }
}
