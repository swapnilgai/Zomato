package java.com.zomato.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SrpBody {
  @Expose
  val restaurants: List<Restaurant>? = null
  @SerializedName("results_found")
  val resultsFound: Long? = null
  @SerializedName("results_shown")
  val resultsShown: Long? = null
  @SerializedName("results_start")
  val resultsStart: Long? = null
}
