package xyz.winthan.beerlover.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import xyz.winthan.beerlover.vos.BeerVO

/**
 * Created by winthanhtike on 11/16/17.
 */
@Database(entities = arrayOf(BeerVO::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    private val DB_NAME = "KABYAR.DB"

    abstract fun beerDao(): BeerDao

    companion object {

        private val DB_NAME = "BEER.DB"

        private var INSTANCE: AppDatabase? = null

        fun getInMemoryDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.inMemoryDatabaseBuilder<AppDatabase>(context.applicationContext, AppDatabase::class.java)
                        .allowMainThreadQueries() //Remove this after testing. Access to DB should always be from background thread.
                        .build()
            }
            return INSTANCE as AppDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}