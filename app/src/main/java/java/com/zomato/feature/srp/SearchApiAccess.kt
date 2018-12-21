package java.com.zomato.feature.srp

import android.content.Context
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.com.zomato.R
import java.com.zomato.network.ApiAccess
import java.com.zomato.util.SrpResultState
import javax.inject.Inject

class SearchApiAccess @Inject constructor(private val apiAccess: ApiAccess, val context: Context) {

  /**
   * Function performs search call on api to get restaurant near user entered location
   * @param input user entered input string
   * @return Observable<SrpResultState> and observable which describes state for
   */
  fun getSrpResult(id: Long, start: Long): Observable<SrpResultState> {
    return apiAccess.getSrpResult(
      apiKey = context.getString(R.string.api_key),
      id = id,
      start = start

    ).switchMap { body ->
      if (body.restaurants.isNullOrEmpty())
        Observable.just(SrpResultState.Error(Throwable(context.getString(R.string.empty_list_error))))
      else
        Observable.just(SrpResultState.Success(list = body.restaurants, start = body.resultsStart))
    }
      .onErrorReturn { throwable: Throwable ->
        SrpResultState.Error(throwable)
      }
      .subscribeOn(Schedulers.io())
      .startWith(SrpResultState.Loading)
  }
}