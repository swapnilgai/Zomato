package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName

data class UserRating(
  @SerializedName("aggregate_rating")
  val aggregateRating: String? = null,
  @SerializedName("custom_rating_text")
  val customRatingText: String? = null,
  @SerializedName("custom_rating_text_background")
  val customRatingTextBackground: String? = null,
  @SerializedName("rating_color")
  val ratingColor: String? = null,
  @SerializedName("rating_text")
  val ratingText: String? = null,
  @SerializedName("rating_tool_tip")
  val ratingToolTip: String? = null,
  val votes: String? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(aggregateRating)
    parcel.writeString(customRatingText)
    parcel.writeString(customRatingTextBackground)
    parcel.writeString(ratingColor)
    parcel.writeString(ratingText)
    parcel.writeString(ratingToolTip)
    parcel.writeString(votes)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<UserRating> {
    override fun createFromParcel(parcel: Parcel): UserRating {
      return UserRating(parcel)
    }

    override fun newArray(size: Int): Array<UserRating?> {
      return arrayOfNulls(size)
    }
  }
}