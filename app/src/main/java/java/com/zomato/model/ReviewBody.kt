package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName

data class ReviewBody(
  @SerializedName("Respond to reviews via Zomato Dashboard")
  val respondToReviewsViaZomatoDashboard: String? = null,
  @SerializedName("reviews_count")
  val reviewsCount: Long? = null,
  @SerializedName("reviews_shown")
  val reviewsShown: Long? = null,
  @SerializedName("reviews_start")
  val reviewsStart: Long? = null,
  @SerializedName("user_reviews")
  val userReviews: List<UserReview>? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    TODO("userReviews")
  ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(respondToReviewsViaZomatoDashboard)
    parcel.writeValue(reviewsCount)
    parcel.writeValue(reviewsShown)
    parcel.writeValue(reviewsStart)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<ReviewBody> {
    override fun createFromParcel(parcel: Parcel): ReviewBody {
      return ReviewBody(parcel)
    }

    override fun newArray(size: Int): Array<ReviewBody?> {
      return arrayOfNulls(size)
    }
  }
}
