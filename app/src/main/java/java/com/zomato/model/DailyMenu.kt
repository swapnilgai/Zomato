package java.com.zomato.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DailyMenu(
  @SerializedName("daily_menu")
  val dailyMenu: List<DailyMenu>? = null,
  @SerializedName("daily_menu_id")
  val dailyMenuId: String? = null,
  @Expose
  val dishes: List<Dish>? = null,
  @SerializedName("end_date")
  val endDate: String? = null,
  @Expose
  val name: String? = null,
  @SerializedName("start_date")
  val startDate: String? = null
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.createTypedArrayList(CREATOR),
    parcel.readString(),
    parcel.createTypedArrayList(Dish),
    parcel.readString(),
    parcel.readString(),
    parcel.readString()
  ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeTypedList(dailyMenu)
    parcel.writeString(dailyMenuId)
    parcel.writeTypedList(dishes)
    parcel.writeString(endDate)
    parcel.writeString(name)
    parcel.writeString(startDate)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<DailyMenu> {
    override fun createFromParcel(parcel: Parcel): DailyMenu {
      return DailyMenu(parcel)
    }

    override fun newArray(size: Int): Array<DailyMenu?> {
      return arrayOfNulls(size)
    }
  }
}