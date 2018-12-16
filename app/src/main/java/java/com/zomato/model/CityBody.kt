package java.com.zomato.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityBody(
  @SerializedName("has_more")
  val hasMore: Long? = null,
  @SerializedName("has_total")
  val hasTotal: Long? = null,
  @SerializedName("location_suggestions")
  val cityList: List<City>? = null,
  @Expose
  val status: String? = null
)