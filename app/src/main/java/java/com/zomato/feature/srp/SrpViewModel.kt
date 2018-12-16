package java.com.zomato.feature.srp

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.com.zomato.model.City
import java.com.zomato.model.Restaurant
import java.com.zomato.util.SearchResultState
import java.com.zomato.util.SrpResultState
import javax.inject.Inject

class SrpViewModel @Inject constructor(private val searchApiAccess: SearchApiAccess) : ViewModel(){

  private val compositeDisposable = CompositeDisposable()
  val list: ObservableList<Restaurant> = ObservableArrayList()
  val loading: ObservableBoolean = ObservableBoolean(false)
  val error: ObservableBoolean = ObservableBoolean(false)


  fun getSrpResult(input : String){
    searchApiAccess.getSrpResult(input)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {  t: SrpResultState -> handelResponse(t) }
      .let { compositeDisposable.add(it) }
  }


  private fun handelResponse(searchResultState: SrpResultState) {
    when (searchResultState) {
      is SrpResultState.Success -> {
        renderSuccess(searchResultState.list)
      }
      is SrpResultState.Loading -> {
        renderLoading()
      }
      is SrpResultState.Error -> {
        renderError(searchResultState.throwable.message)
      }
      else -> throw IllegalStateException()
    }
  }

  private fun renderSuccess(listItems: List<Restaurant>) {
    list.clear()
    list.addAll(listItems)
    loading.set(false)
    error.set(false)
  }

  private fun renderLoading() {
    list.clear()
    error.set(false)
    loading.set(true)
  }

  private fun renderError(message: String?) {
    list.clear()
    loading.set(false)
    error.set(true)
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}