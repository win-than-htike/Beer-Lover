package xyz.winthan.beerlover.itemclicklistener

import xyz.winthan.beerlover.vos.BeerVO

/**
 * Created by winthanhtike on 11/30/17.
 */
interface OnItemClickListener {
    fun onTapSong(beerVO: BeerVO?)
}