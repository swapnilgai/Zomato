package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SrpBody(
  @Expose
  val restaurants: List<Restaurant>? = null,
  @SerializedName("results_found")
  val resultsFound: Long? = null,
  @SerializedName("results_shown")
  val resultsShown: Long? = null,
  @SerializedName("results_start")
  val resultsStart: Long? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.createTypedArrayList(Restaurant),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long
  ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeTypedList(restaurants)
    parcel.writeValue(resultsFound)
    parcel.writeValue(resultsShown)
    parcel.writeValue(resultsStart)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<SrpBody> {
    override fun createFromParcel(parcel: Parcel): SrpBody {
      return SrpBody(parcel)
    }

    override fun newArray(size: Int): Array<SrpBody?> {
      return arrayOfNulls(size)
    }
  }
}
