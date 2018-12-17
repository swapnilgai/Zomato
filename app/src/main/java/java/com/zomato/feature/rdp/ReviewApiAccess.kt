package java.com.zomato.feature.rdp

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import java.com.zomato.R
import java.com.zomato.network.ApiAccess
import java.com.zomato.util.ReviewResultState
import javax.inject.Inject

class ReviewApiAccess @Inject constructor(val apiAccess: ApiAccess, val context: Context) {

  /**
   * Function gets app reviews for hotel for user selected item
   * @param id selected hotel
   * @return Observable<ReviewResultState> and observable which describes state for
   */
  fun getReviews(id: Long): Observable<ReviewResultState> {
    return apiAccess.getReviews(
      apiKey = context.getString(R.string.api_key),
      restId = id
    ).flatMap { body ->
      if (body.reviewsStart != null && !body.userReviews.isNullOrEmpty())
        Single.just(
          ReviewResultState.Success(
            start = body.reviewsStart,
            userReviews = body.userReviews
          )
        )
      else
        Single.just(ReviewResultState.Error(Throwable(context.getString(R.string.empty_list_error))))
    }
      .onErrorReturn { throwable: Throwable ->
        ReviewResultState.Error(throwable)
      }
      .toObservable()
      .startWith(ReviewResultState.Loading)
  }
}