package xyz.winthan.beerlover.data.repository.remote

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.winthan.beerlover.vos.BeerVO

/**
 * Created by winthanhtike on 11/15/17.
 */
interface BeerService {

    @GET("beers")
    fun loadBeer(@Query("page") page : Int) : Flowable<List<BeerVO>>

}