package java.com.zomato.feature.srp

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.com.zomato.model.Restaurant
import java.com.zomato.util.SrpResultState
import javax.inject.Inject

class SrpViewModel @Inject constructor(private val searchApiAccess: SearchApiAccess) : ViewModel() {

  private val compositeDisposable = CompositeDisposable()
  val list: ObservableList<Restaurant> = ObservableArrayList()
  val loading: ObservableBoolean = ObservableBoolean(false)
  val error: ObservableBoolean = ObservableBoolean(false)
  private var start: Long = 0
  private var id: Long = 0

  fun getSrpResult(id: Long) {
    searchApiAccess.getSrpResult(id, start + 20)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { t: SrpResultState -> handelResponse(t) }
      .let { compositeDisposable.add(it) }
  }

  fun onSwipeToRefresh() {
    start = 0
    getSrpResult(id)
  }

  private fun handelResponse(searchResultState: SrpResultState) {
    when (searchResultState) {
      is SrpResultState.Success -> {
        renderSuccess(searchResultState.list)
      }
      is SrpResultState.Loading -> {
        this.id = id
        renderLoading()
      }
      is SrpResultState.Error -> {
        renderError(searchResultState.throwable.message)
      }
      else -> throw IllegalStateException()
    }
  }

  private fun renderSuccess(listItems: List<Restaurant>) {
    if (start == 0L) {
      list.clear()
    }
    listItems.forEach { restaurant: Restaurant -> list.add(restaurant.restaurant) }
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