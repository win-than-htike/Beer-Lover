package xyz.winthan.beerlover.utils

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by winthanhtike on 11/15/17.
 */
class RetrofitUtil private constructor(){

    val retrofit : Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    companion object {

        private var objInstance : RetrofitUtil? = null

        val instance : RetrofitUtil
            get() {
                if (objInstance == null) {
                    objInstance = RetrofitUtil()
                }
                return objInstance!!
            }

    }

}