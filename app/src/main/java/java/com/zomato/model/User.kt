package java.com.zomato.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
  @SerializedName("foodie_color")
  val foodieColor: String? = null,
  @SerializedName("foodie_level")
  val foodieLevel: String? = null,
  @SerializedName("foodie_level_num")
  val foodieLevelNum: Long? = null,
  @Expose
  val name: String? = null,
  @SerializedName("profile_deeplink")
  val profileDeeplink: String? = null,
  @SerializedName("profile_image")
  val profileImage: String? = null,
  @SerializedName("profile_url")
  val profileUrl: String? = null
)
