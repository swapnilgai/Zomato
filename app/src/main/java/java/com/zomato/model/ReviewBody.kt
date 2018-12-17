package java.com.zomato.model

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
)
