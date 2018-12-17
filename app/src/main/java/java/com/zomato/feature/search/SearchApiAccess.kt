package java.com.zomato.feature.search

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import java.com.zomato.R
import java.com.zomato.network.ApiAccess
import java.com.zomato.util.SearchResultState
import javax.inject.Inject

class SearchApiAccess @Inject constructor(private val apiAccess: ApiAccess, private val context: Context) {


  /**
   * Function performs search call on api for user entered input string
   * @param input user entered input string
   * @return Observable<SearchResultState> and observable which describes state for
   */
  fun getSearchResult(input: String): Observable<SearchResultState> {
    return apiAccess.getAutoSuggestResult(
      apiKey = context.getString(R.string.api_key),
      searchString = input
    ).flatMap { body ->
      if (!body.status.equals(context.getString(R.string.success)))
        Single.just(SearchResultState.Error(Throwable(context.getString(R.string.server_error))))
      else if (body.cityList != null)
        Single.just(SearchResultState.Success(body.cityList))
      else
        Single.just(SearchResultState.Error(Throwable(context.getString(R.string.empty_list_error))))
    }
      .onErrorReturn { throwable: Throwable ->
        SearchResultState.Error(throwable)
      }
      .toObservable()
      .startWith(SearchResultState.Loading)
  }
}