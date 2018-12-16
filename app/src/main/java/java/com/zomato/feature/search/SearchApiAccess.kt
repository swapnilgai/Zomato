package java.com.zomato.feature.search

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.com.zomato.R
import java.com.zomato.network.ApiAccess
import java.com.zomato.util.SearchResultState
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class SearchApiAccess @Inject constructor(val apiAccess: ApiAccess, val context: Context) {

  companion object {
    private const val DEBOUNCE_INTERVAL: Long = 200
  }

  /**
   * Function performs search call on api for user entered input string
   * @param input user entered input string
   * @return Observable<SearchResultState> and observable which describes state for
   */
  fun getSearchResult(input: String): Observable<SearchResultState> {
    return apiAccess.getAutoSuggestResult(
      apiKey = context.getString(R.string.api_key),
      searchString = input
    )
      .debounce(DEBOUNCE_INTERVAL, MILLISECONDS)
      .switchMap { body ->
        if (!body.status.equals(context.getString(R.string.success)))
          Observable.just(SearchResultState.Error(Throwable(context.getString(R.string.server_error))))
        else if (body.cityList != null)
          Observable.just(SearchResultState.Success(body.cityList))
        else
          Observable.just(SearchResultState.Error(Throwable(context.getString(R.string.empty_list_error))))
      }
      .onErrorReturn { throwable: Throwable ->
        SearchResultState.Error(throwable)
      }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .startWith(SearchResultState.Loading)
  }
}