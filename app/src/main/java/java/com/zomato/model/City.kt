package java.com.zomato.model

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
)
