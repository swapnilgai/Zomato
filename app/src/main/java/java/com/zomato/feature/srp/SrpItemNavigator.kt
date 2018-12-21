package java.com.zomato.feature.srp

import java.com.zomato.model.Restaurant

interface SrpItemNavigator {
  fun onClick(restaurant: Restaurant)
}