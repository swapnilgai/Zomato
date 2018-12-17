package java.com.zomato.util

import java.com.zomato.model.City
import java.com.zomato.model.Restaurant


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
