package java.com.zomato.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.com.zomato.model.CityBody
import java.com.zomato.model.SrpBody


interface ApiAccess {
  @GET("cities")
  fun getAutoSuggestResult(@Header("user-key") apiKey: String, @Query("q") searchString: String): Observable<CityBody>

  @GET("search")
  fun getSrpResult(@Header("user-key") apiKey: String, @Query("q") searchString: String): Observable<SrpBody>
}