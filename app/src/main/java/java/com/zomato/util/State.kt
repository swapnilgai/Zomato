package java.com.zomato.util

import java.com.zomato.model.City
import java.com.zomato.model.DailyMenu
import java.com.zomato.model.Restaurant
import java.com.zomato.model.UserReview


sealed class SearchResultState {
  object Loading : SearchResultState()
  class Error(val throwable: Throwable) : SearchResultState()
  class Success(val list: List<City>) : SearchResultState()
}

sealed class SrpResultState {
  object Loading : SrpResultState()
  class Error(val throwable: Throwable) : SrpResultState()
  class Success(val list: List<Restaurant>, val start: Long?) : SrpResultState()
}

sealed class DailyMenuResultState {
  object Loading : DailyMenuResultState()
  class Error(val throwable: Throwable) : DailyMenuResultState()
  class Success(val menu: DailyMenu) : DailyMenuResultState()
}

sealed class ReviewResultState {
  object Loading : ReviewResultState()
  class Error(val throwable: Throwable) : ReviewResultState()
  class Success(val userReviews: List<UserReview>, val start: Long) : ReviewResultState()
}