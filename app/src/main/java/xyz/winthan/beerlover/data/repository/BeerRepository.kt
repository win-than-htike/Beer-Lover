package xyz.winthan.beerlover.data.repository

import io.reactivex.Flowable
import xyz.winthan.beerlover.data.db.BeerDao
import xyz.winthan.beerlover.data.repository.local.BeerLocalDataSource
import xyz.winthan.beerlover.data.repository.remote.BeerRemoteDataSource
import xyz.winthan.beerlover.vos.BeerVO

/**
 * Created by winthanhtike on 11/15/17.
 */
class BeerRepository(local : BeerLocalDataSource, remote : BeerRemoteDataSource) {

    var remote : BeerRemoteDataSource = remote
    var local : BeerLocalDataSource = local

    companion object {

        var objInstance : BeerRepository? = null

        fun instance(beerDao: BeerDao) : BeerRepository {
            if (objInstance == null){
                objInstance = BeerRepository(BeerLocalDataSource.getInstane(beerDao), BeerRemoteDataSource.instance)
            }
            return objInstance!!
        }

    }

    fun getCacheList(): Flowable<List<BeerVO>>? {
        return local.getAllBeer()
    }

    fun getBeerList() : Flowable<List<BeerVO>>? {
        return remote.loadBeerList().flatMap {
            t -> local.insertAll(t)
                 local.getAllBeer()
        }

    }

    fun getSingleBeer(id : Int) : Flowable<BeerVO>? {

        return local.getSingleBeer(id)

    }

}