package java.com.zomato.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location(
  @Expose
  val address: String? = null,
  @Expose
  val city: String? = null,
  @SerializedName("city_id")
  val cityId: Long? = null,
  @SerializedName("country_id")
  val countryId: Long? = null,
  @Expose
  val latitude: String? = null,
  @Expose
  val locality: String? = null,
  @SerializedName("locality_verbose")
  val localityVerbose: String? = null,
  @Expose
  val longitude: String? = null,
  @Expose
  val zipcode: String? = null
)
