package xyz.winthan.beerlover.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import xyz.winthan.beerlover.vos.BeerVO

/**
 * Created by winthanhtike on 11/16/17.
 */
@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKabyar(beer: BeerVO): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKabyars(beers: List<BeerVO>): LongArray

    @Query("SELECT * FROM beers")
    fun getAllBeers() : Flowable<List<BeerVO>>

    @Query("SELECT * FROM beers WHERE id = :id")
    fun getSingleBeer(id : Int) : Flowable<BeerVO>

    @Query("DELETE FROM beers")
    fun deleteAll()

}