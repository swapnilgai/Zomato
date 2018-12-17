package java.com.zomato.feature.search

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.com.zomato.model.City
import java.com.zomato.util.SearchResultState
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchApiAccess: SearchApiAccess) : ViewModel() {

  private val compositeDisposable = CompositeDisposable()
  val list: ObservableList<City> = ObservableArrayList()
  val loading: ObservableBoolean = ObservableBoolean(false)
  val error: ObservableBoolean = ObservableBoolean(false)
  val intentReceiver: PublishSubject<String> = PublishSubject.create()

  companion object {
    private const val DEBOUNCE_INTERVAL: Long = 200
  }

  init {
    intentReceiver
      .debounce(DEBOUNCE_INTERVAL, MILLISECONDS)
      .observeOn(Schedulers.io())
      .switchMap { searchApiAccess.getSearchResult(it) }
      .distinctUntilChanged()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { t: SearchResultState -> handelResponse(t) }
      .let { compositeDisposable.add(it) }
  }

  fun getSearchResult(input: String) {
    intentReceiver.onNext(input)
  }

  private fun handelResponse(searchResultState: SearchResultState) {
    when (searchResultState) {
      is SearchResultState.Success -> {
        renderSuccess(searchResultState.list)
      }
      is SearchResultState.Loading -> {
        renderLoading()
      }
      is SearchResultState.Error -> {
        renderError(searchResultState.throwable.message)
      }
      else -> throw IllegalStateException()
    }
  }

  private fun renderSuccess(listItems: List<City>) {
    list.clear()
    list.addAll(listItems)
    loading.set(false)
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