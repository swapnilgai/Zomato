package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Restaurant(
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
  val restaurant: Restaurant? = null,
  @Expose
  val location: Location? = null,
  @SerializedName("menu_url")
  val menuUrl: String? = null,
  @SerializedName("mezzo_provider")
  val mezzoProvider: String? = null,
  @Expose
  val name: String? = null,
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
  @SerializedName("switch_to_order_menu")
  val switchToOrderMenu: Long? = null,
  @Expose
  val thumb: String? = null,
  @Expose
  val url: String? = null,
  @SerializedName("user_rating")
  val userRating: UserRating? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readParcelable(Restaurant::class.java.classLoader),
    parcel.readParcelable(Location::class.java.classLoader),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readString(),
    parcel.readParcelable(UserRating::class.java.classLoader)
  ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(apikey)
    parcel.writeValue(averageCostForTwo)
    parcel.writeString(bookAgainUrl)
    parcel.writeString(bookFormWebViewUrl)
    parcel.writeString(cuisines)
    parcel.writeString(currency)
    parcel.writeString(deeplink)
    parcel.writeString(eventsUrl)
    parcel.writeString(featuredImage)
    parcel.writeValue(hasOnlineDelivery)
    parcel.writeValue(hasTableBooking)
    parcel.writeString(id)
    parcel.writeValue(includeBogoOffers)
    parcel.writeValue(isBookFormWebView)
    parcel.writeValue(isDeliveringNow)
    parcel.writeValue(isTableReservationSupported)
    parcel.writeValue(isZomatoBookRes)
    parcel.writeParcelable(restaurant, flags)
    parcel.writeParcelable(location, flags)
    parcel.writeString(menuUrl)
    parcel.writeString(mezzoProvider)
    parcel.writeString(name)
    parcel.writeValue(opentableSupport)
    parcel.writeString(orderDeeplink)
    parcel.writeString(orderUrl)
    parcel.writeString(photosUrl)
    parcel.writeValue(priceRange)
    parcel.writeValue(switchToOrderMenu)
    parcel.writeString(thumb)
    parcel.writeString(url)
    parcel.writeParcelable(userRating, flags)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<Restaurant> {
    override fun createFromParcel(parcel: Parcel): Restaurant {
      return Restaurant(parcel)
    }

    override fun newArray(size: Int): Array<Restaurant?> {
      return arrayOfNulls(size)
    }
  }
}