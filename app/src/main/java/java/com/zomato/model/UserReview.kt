package java.com.zomato.model

import com.google.gson.annotations.Expose

data class UserReview(
  @Expose
  val review: Review? = null
)
