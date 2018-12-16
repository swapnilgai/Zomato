package java.com.zomato.util

import java.com.zomato.model.City


sealed class SearchResultState {
  object Loading : SearchResultState()
  class Error(val throwable: Throwable) : SearchResultState()
  class Success(val list: List<City>) : SearchResultState()
}
