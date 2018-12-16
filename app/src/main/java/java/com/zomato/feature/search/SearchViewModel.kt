package java.com.zomato.feature.search

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.com.zomato.model.City
import java.com.zomato.util.SearchResultState
import javax.inject.Inject

class SearchViewModel @Inject constructor(val searchApiAccess: SearchApiAccess) : ViewModel() {

  private val compositeDisposable = CompositeDisposable()
  val list: ObservableList<City> = ObservableArrayList()
  val loading: ObservableBoolean = ObservableBoolean(false)
  val error: ObservableBoolean = ObservableBoolean(false)

  fun getSearchResult(input: String) {
    searchApiAccess
      .getSearchResult(input)
      .subscribe { t: SearchResultState -> handelResponse(t) }
      .let { compositeDisposable.add(it) }
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