package java.com.zomato.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Restaurant(
  @Expose
  val apikey: String? = null,
  @SerializedName("average_cost_for_two")
  val averageCostForTwo: Long? = null,
  @SerializedName("book_again_url")
  val bookAgainUrl: String? = null,
  @SerializedName("book_form_web_view_url")
  val bookFormWebViewUrl: String? = null,
  @Expose
  val cuisines: String? = null,
  @Expose
  val currency: String? = null,
  @Expose
  val deeplink: String? = null,
  @SerializedName("establishment_types")
  val establishmentTypes: List<Any>? = null,
  @SerializedName("events_url")
  val eventsUrl: String? = null,
  @SerializedName("featured_image")
  val featuredImage: String? = null,
  @SerializedName("has_online_delivery")
  val hasOnlineDelivery: Long? = null,
  @SerializedName("has_table_booking")
  val hasTableBooking: Long? = null,
  @Expose
  val id: String? = null,
  @SerializedName("include_bogo_offers")
  val includeBogoOffers: Boolean? = null,
  @SerializedName("is_book_form_web_view")
  val isBookFormWebView: Long? = null,
  @SerializedName("is_delivering_now")
  val isDeliveringNow: Long? = null,
  @SerializedName("is_table_reservation_supported")
  val isTableReservationSupported: Long? = null,
  @SerializedName("is_zomato_book_res")
  val isZomatoBookRes: Long? = null,
  @Expose
  val location: Location? = null,
  @SerializedName("menu_url")
  val menuUrl: String? = null,
  @SerializedName("mezzo_provider")
  val mezzoProvider: String? = null,
  @Expose
  val name: String? = null,
  @Expose
  val offers: List<Any>? = null,
  @SerializedName("opentable_support")
  val opentableSupport: Long? = null,
  @SerializedName("order_deeplink")
  val orderDeeplink: String? = null,
  @SerializedName("order_url")
  val orderUrl: String? = null,
  @SerializedName("photos_url")
  val photosUrl: String? = null,
  @SerializedName("price_range")
  val priceRange: Long? = null,
  @Expose
  val restaurant: Restaurant? = null,
  @SerializedName("switch_to_order_menu")
  val switchToOrderMenu: Long? = null,
  @Expose
  val thumb: String? = null,
  @Expose
  val url: String? = null,
  @SerializedName("user_rating")
  val userRating: UserRating? = null
)
