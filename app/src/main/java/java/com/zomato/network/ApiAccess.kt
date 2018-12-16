package java.com.zomato.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.com.zomato.model.CityBody
import java.com.zomato.model.SrpBody


interface ApiAccess {
  @GET("cities")
  fun getAutoSuggestResult(@Header("user-key") apiKey: String, @Query("q") searchString: String): Single<CityBody>

  @GET("search")
  fun getSrpResult(@Header("user-key") apiKey: String, @Query("entity_id") id: Long, @Query("start") start: Long): Observable<SrpBody>
}