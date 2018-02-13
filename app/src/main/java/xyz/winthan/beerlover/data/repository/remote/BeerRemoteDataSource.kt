package xyz.winthan.beerlover.data.repository.remote

import io.reactivex.Flowable
import xyz.winthan.beerlover.utils.RetrofitUtil
import xyz.winthan.beerlover.vos.BeerVO

/**
 * Created by winthanhtike on 11/15/17.
 */
class BeerRemoteDataSource private constructor() {

    val mService : BeerService

    init {
        mService = RetrofitUtil.instance.retrofit.create(BeerService::class.java)
    }

    companion object {

        var objInstance : BeerRemoteDataSource? = null

        val instance : BeerRemoteDataSource
            get() {
                if (objInstance == null){
                    objInstance = BeerRemoteDataSource()
                }
                return objInstance!!
            }

    }

    fun loadBeerList() : Flowable<List<BeerVO>> {
        return mService.loadBeer(1)
    }

}