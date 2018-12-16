package java.com.zomato.model

import com.google.gson.annotations.SerializedName

class UserRating(
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
)
