package java.com.zomato.feature.rdp

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import java.com.zomato.R
import java.com.zomato.network.ApiAccess
import java.com.zomato.util.DailyMenuResultState
import javax.inject.Inject

class DailyMenuApiAccess @Inject constructor(val apiAccess: ApiAccess, val context: Context) {

  /**
   * Function gets app reviews for hotel for user selected item
   * @param id selected hotel
   * @return Observable<DailyMenuResultState> and observable which describes state for
   */
  fun getDailyMenuResult(id: Long): Observable<DailyMenuResultState> {
    return apiAccess.getDailyMenu(
      apiKey = context.getString(R.string.api_key),
      restId = id
    ).flatMap { body ->
      if (body.dailyMenu.isNullOrEmpty())
        Single.just(DailyMenuResultState.Error(Throwable(context.getString(R.string.empty_list_error))))
      else
        Single.just(DailyMenuResultState.Success(body))
    }
      .onErrorReturn { throwable: Throwable ->
        DailyMenuResultState.Error(throwable)
      }
      .toObservable()
      .startWith(DailyMenuResultState.Loading)
  }
}