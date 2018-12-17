package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
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
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString()
  ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(foodieColor)
    parcel.writeString(foodieLevel)
    parcel.writeValue(foodieLevelNum)
    parcel.writeString(name)
    parcel.writeString(profileDeeplink)
    parcel.writeString(profileImage)
    parcel.writeString(profileUrl)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<User> {
    override fun createFromParcel(parcel: Parcel): User {
      return User(parcel)
    }

    override fun newArray(size: Int): Array<User?> {
      return arrayOfNulls(size)
    }
  }
}
