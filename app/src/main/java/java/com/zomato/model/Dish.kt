package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Dish(
  @SerializedName("dish_id")
  val dishId: String? = null,
  @Expose
  val name: String? = null,
  @Expose
  val price: String? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readString()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(dishId)
    parcel.writeString(name)
    parcel.writeString(price)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<Dish> {
    override fun createFromParcel(parcel: Parcel): Dish {
      return Dish(parcel)
    }

    override fun newArray(size: Int): Array<Dish?> {
      return arrayOfNulls(size)
    }
  }
}
