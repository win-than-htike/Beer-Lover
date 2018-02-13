package xyz.winthan.beerlover.data.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.Flowable
import xyz.winthan.beerlover.data.db.AppDatabase
import xyz.winthan.beerlover.data.repository.BeerRepository
import xyz.winthan.beerlover.vos.BeerVO

/**
 * Created by winthanhtike on 11/16/17.
 */
class BeerViewModel(application: Application) : AndroidViewModel(application) {

    private var appDatabase : AppDatabase

    private var beerRepository : BeerRepository

    init {
        appDatabase = AppDatabase.getInMemoryDatabase(application.applicationContext)
        beerRepository = BeerRepository.instance(appDatabase.beerDao())
    }

    fun getBeers() : Flowable<List<BeerVO>>? {
        return beerRepository.getBeerList()
    }

    fun getSingleBeers(id : Int) : Flowable<BeerVO>? {
        return beerRepository.getSingleBeer(id)
    }

    override fun onCleared() {
        super.onCleared()
        AppDatabase.destroyInstance()
    }

}