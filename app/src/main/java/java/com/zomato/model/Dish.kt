package java.com.zomato.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Dish(
  @SerializedName("dish_id")
  val dishId: String? = null,
  @Expose
  val name: String? = null,
  @Expose
  val price: String? = null
)
