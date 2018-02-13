package xyz.winthan.beerlover.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import xyz.winthan.beerlover.R
import xyz.winthan.beerlover.itemclicklistener.OnItemClickListener
import xyz.winthan.beerlover.viewholders.BeerViewHolder
import xyz.winthan.beerlover.vos.BeerVO

/**
 * Created by winthanhtike on 11/14/17.
 */
class BeerListAdapter(mContext: Context, itemClickListener : OnItemClickListener) : BaseRecyclerAdapter<BeerViewHolder, BeerVO>(mContext) {

    val itemClickListener : OnItemClickListener = itemClickListener

    override fun onBindViewHolder(holder: BeerViewHolder?, position: Int) {
        holder!!.bind(mData!!.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BeerViewHolder {
        val view : View = mLayoutInflater.inflate(R.layout.list_item_beer, parent, false)
        return BeerViewHolder(view, itemClickListener)
    }

}