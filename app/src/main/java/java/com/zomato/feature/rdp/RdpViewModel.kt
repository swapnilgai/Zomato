package java.com.zomato.feature.rdp

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.com.zomato.model.DailyMenu
import java.com.zomato.model.UserReview
import java.com.zomato.util.DailyMenuResultState
import java.com.zomato.util.ReviewResultState
import java.com.zomato.util.ReviewResultState.Error
import java.com.zomato.util.ReviewResultState.Success
import javax.inject.Inject

class RdpViewModel @Inject constructor(
  private val dailyMenuApiAccess: DailyMenuApiAccess,
  private val reviewApiAccess: ReviewApiAccess
) : ViewModel() {

  private val compositeDisposable = CompositeDisposable()

  sealed class ViewModelState {
    object Loading : ViewModelState()
    class Error(val message1: String, val message2: String) : ViewModelState()
    class Success(
      val menu: DailyMenu? = null,
      val userReviews: List<UserReview>? = null,
      val start: Long? = null
    ) : ViewModelState()
  }

  fun getDetail(id: Long) {
    Observable.zip(dailyMenuApiAccess.getDailyMenuResult(id),
      reviewApiAccess.getReviews(id), BiFunction { dailyMenuResultState: DailyMenuResultState,
        reviewResultState: ReviewResultState ->
        Pair(dailyMenuResultState, reviewResultState)
      }).map { partialStatePair: Pair<DailyMenuResultState, ReviewResultState> ->
      return@map if (partialStatePair.first is DailyMenuResultState.Success && partialStatePair.second is ReviewResultState.Success)
        ViewModelState.Success(
          menu = (partialStatePair.first as DailyMenuResultState.Success).menu,
          userReviews = (partialStatePair.second as Success).userReviews,
          start = (partialStatePair.second as Success).start
        )
      else if (partialStatePair.first is DailyMenuResultState.Success && partialStatePair.second is ReviewResultState.Error)
        ViewModelState.Success(menu = (partialStatePair.first as DailyMenuResultState.Success).menu)
      else if (partialStatePair.first is DailyMenuResultState.Error && partialStatePair.second is ReviewResultState.Success)
        ViewModelState.Success(
          userReviews = (partialStatePair.second as Success).userReviews,
          start = (partialStatePair.second as Success).start
        )
      else if (partialStatePair.first is DailyMenuResultState.Loading || partialStatePair.second is ReviewResultState.Loading)
        ViewModelState.Loading
      else
        ViewModelState.Error(
          (partialStatePair.first as DailyMenuResultState.Error).throwable.localizedMessage,
          (partialStatePair.second as Error).throwable.localizedMessage
        )
    }.subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { t -> renderUi(viewModelState = t) }
      .let { compositeDisposable.add(it) }
  }

  private fun renderUi(viewModelState: ViewModelState) {


  }
}