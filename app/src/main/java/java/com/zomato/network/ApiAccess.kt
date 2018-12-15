package java.com.zomato.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.com.zomato.model.City


interface ApiAccess {
  @GET("cities")
  fun getAutoSuggestResult(@Query("q") searchString: String): Observable<List<City>>
}