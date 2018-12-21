package java.com.zomato.feature.search

import java.com.zomato.model.City

interface SearchItemNavigator {
  fun onClick(city: City)
}