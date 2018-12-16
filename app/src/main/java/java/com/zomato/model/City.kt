package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName

data class City(
  @SerializedName("country_flag_url")
  val countryFlagUrl: String? = null,
  @SerializedName("country_id")
  val countryId: Long? = null,
  @SerializedName("country_name")
  val countryName: String? = null,
  @SerializedName("discovery_enabled")
  val discoveryEnabled: Long? = null,
  @SerializedName("has_new_ad_format")
  val hasNewAdFormat: Long? = null,
  private var id: Long? = null,
  @SerializedName("is_state")
  val isState: Long? = null,
  val name: String? = null,
  @SerializedName("should_experiment_with")
  val shouldExperimentWith: Long? = null,
  @SerializedName("state_code")
  val stateCode: String? = null,
  @SerializedName("state_id")
  val stateId: Long? = null,
  @SerializedName("state_name")
  val stateName: String? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString()
  ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(countryFlagUrl)
    parcel.writeValue(countryId)
    parcel.writeString(countryName)
    parcel.writeValue(discoveryEnabled)
    parcel.writeValue(hasNewAdFormat)
    parcel.writeValue(id)
    parcel.writeValue(isState)
    parcel.writeString(name)
    parcel.writeValue(shouldExperimentWith)
    parcel.writeString(stateCode)
    parcel.writeValue(stateId)
    parcel.writeString(stateName)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<City> {
    override fun createFromParcel(parcel: Parcel): City {
      return City(parcel)
    }

    override fun newArray(size: Int): Array<City?> {
      return arrayOfNulls(size)
    }
  }
}
