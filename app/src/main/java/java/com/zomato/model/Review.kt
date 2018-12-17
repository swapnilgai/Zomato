package java.com.zomato.model

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
)
