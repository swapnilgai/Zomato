package java.com.zomato.model

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
)
