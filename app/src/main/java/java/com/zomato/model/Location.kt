package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location(
  @Expose
  val address: String? = null,
  @Expose
  val city: String? = null,
  @SerializedName("city_id")
  val cityId: Long? = null,
  @SerializedName("country_id")
  val countryId: Long? = null,
  @Expose
  val latitude: String? = null,
  @Expose
  val locality: String? = null,
  @SerializedName("locality_verbose")
  val localityVerbose: String? = null,
  @Expose
  val longitude: String? = null,
  @Expose
  val zipcode: String? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(address)
    parcel.writeString(city)
    parcel.writeValue(cityId)
    parcel.writeValue(countryId)
    parcel.writeString(latitude)
    parcel.writeString(locality)
    parcel.writeString(localityVerbose)
    parcel.writeString(longitude)
    parcel.writeString(zipcode)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<Location> {
    override fun createFromParcel(parcel: Parcel): Location {
      return Location(parcel)
    }

    override fun newArray(size: Int): Array<Location?> {
      return arrayOfNulls(size)
    }
  }
}
