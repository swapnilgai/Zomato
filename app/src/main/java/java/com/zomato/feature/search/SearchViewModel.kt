package java.com.zomato.feature.search

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.com.zomato.R
import java.com.zomato.network.ApiAccess
import java.com.zomato.util.SearchResultState
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class SearchViewModel @Inject constructor(apiAccess: ApiAccess, val context: Context) : ViewModel(), ApiAccess by apiAccess {

  private val intentReceiver  = PublishSubject.create<String>()
  private val compositeDisposable = CompositeDisposable()
  val list  : ObservableList<String> = ObservableArrayList()
  val loading : ObservableBoolean = ObservableBoolean(true)
  val error : ObservableBoolean = ObservableBoolean(false)

  companion object {
    private const val DEBOUNCE_INTERVAL: Long = 200
  }

  fun getSearchResult(input : String){
    intentReceiver
      .debounce(DEBOUNCE_INTERVAL, MILLISECONDS)
      .switchMap { getSearchResultApiCall(input) }
      .distinctUntilChanged()
      .doOnNext { onNext() }
      .subscribe{}
      .let { compositeDisposable.add(it) }
  }

  /**
   * Function performs search call on api for user entered input string
   * @param input user entered input string
   * @return Observable<SearchResultState> and observable which describes state for
   */
  private fun getSearchResultApiCall(input : String): Observable<SearchResultState> {
    return getAutoSuggestResult(input)
      .flatMap { list ->
        return@flatMap if(list == null || list.isEmpty())
          Observable.just(SearchResultState.Error(Throwable(context.getString(R.string.empty_list_error))))
        else
          Observable.just(SearchResultState.Success(list))
      }
      .onErrorReturn { throwable: Throwable -> SearchResultState.Error(throwable) }
      .subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .startWith(SearchResultState.Loading)
  }

  private fun onSuccess(listItems : List<String>){
    list.clear()
    list.addAll(listItems)
    loading.set(false)
  }

  private fun onNext(){
    list.clear()
    error.set(false)
    loading.set(true)
  }

  private fun onError(){
    list.clear()
    loading.set(false)
    error.set(true)
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}