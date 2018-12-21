package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityBody(
  @SerializedName("has_more")
  val hasMore: Long? = null,
  @SerializedName("has_total")
  val hasTotal: Long? = null,
  @SerializedName("location_suggestions")
  val cityList: List<City>? = null,
  @Expose
  val status: String? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.createTypedArrayList(City),
    parcel.readString()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(hasMore)
    parcel.writeValue(hasTotal)
    parcel.writeTypedList(cityList)
    parcel.writeString(status)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<CityBody> {
    override fun createFromParcel(parcel: Parcel): CityBody {
      return CityBody(parcel)
    }

    override fun newArray(size: Int): Array<CityBody?> {
      return arrayOfNulls(size)
    }
  }
}