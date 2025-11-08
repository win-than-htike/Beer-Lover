package xyz.winthan.beerlover.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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