package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Review(
  @SerializedName("comments_count")
  val commentsCount: Long? = null,
  @Expose
  val id: Long? = null,
  @Expose
  val likes: Long? = null,
  @Expose
  val rating: Long? = null,
  @SerializedName("rating_color")
  val ratingColor: String? = null,
  @SerializedName("rating_text")
  val ratingText: String? = null,
  @SerializedName("review_text")
  val reviewText: String? = null,
  @SerializedName("review_time_friendly")
  val reviewTimeFriendly: String? = null,
  @Expose
  val timestamp: Long? = null,
  @Expose
  val user: User? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    TODO("user")
  ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(commentsCount)
    parcel.writeValue(id)
    parcel.writeValue(likes)
    parcel.writeValue(rating)
    parcel.writeString(ratingColor)
    parcel.writeString(ratingText)
    parcel.writeString(reviewText)
    parcel.writeString(reviewTimeFriendly)
    parcel.writeValue(timestamp)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<Review> {
    override fun createFromParcel(parcel: Parcel): Review {
      return Review(parcel)
    }

    override fun newArray(size: Int): Array<Review?> {
      return arrayOfNulls(size)
    }
  }
}