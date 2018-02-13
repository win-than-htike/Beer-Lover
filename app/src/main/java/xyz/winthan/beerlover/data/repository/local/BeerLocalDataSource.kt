package xyz.winthan.beerlover.data.repository.local

import io.reactivex.Flowable
import xyz.winthan.beerlover.data.db.BeerDao
import xyz.winthan.beerlover.vos.BeerVO

/**
 * Created by winthanhtike on 11/15/17.
 */
class BeerLocalDataSource private constructor(beerDao: BeerDao) {

    var beerDao : BeerDao? = null

    init {
        this.beerDao = beerDao
    }

    companion object {

        private var objInstance : BeerLocalDataSource? = null

        fun getInstane(beerDao: BeerDao): BeerLocalDataSource {
            if (objInstance == null) {
                objInstance = BeerLocalDataSource(beerDao)
            }
            return objInstance!!
        }

    }

    fun insertAll(beerList : List<BeerVO>) {
        beerDao?.insertKabyars(beerList)
    }

    fun getAllBeer() : Flowable<List<BeerVO>>? {
        return beerDao?.getAllBeers()
    }

    fun getSingleBeer(id : Int) : Flowable<BeerVO>? {
        return beerDao?.getSingleBeer(id)
    }

}