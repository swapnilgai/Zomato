package java.com.zomato.feature.srp

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.com.zomato.R
import java.com.zomato.network.ApiAccess
import java.com.zomato.util.SearchResultState
import java.com.zomato.util.SrpResultState
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class SearchApiAccess  @Inject constructor(val apiAccess: ApiAccess, val context: Context) {

  companion object {
    private const val DEBOUNCE_INTERVAL: Long = 200
  }

  /**
   * Function performs search call on api for user entered input string
   * @param input user entered input string
   * @return Observable<SearchResultState> and observable which describes state for
   */
  fun getSrpResult(input: String): Observable<SrpResultState> {
    return apiAccess.getSrpResult(
      apiKey = context.getString(R.string.api_key),
      searchString = input
    ).switchMap { body ->
        if (body.restaurants.isNullOrEmpty())
          Observable.just(SrpResultState.Error(Throwable(context.getString(R.string.empty_list_error))))
        else
          Observable.just(SrpResultState.Success(list= body.restaurants, start = body.resultsStart))
      }
      .onErrorReturn { throwable: Throwable ->
        SrpResultState.Error(throwable)
      }
      .subscribeOn(Schedulers.io())
      .startWith(SrpResultState.Loading)
  }
}