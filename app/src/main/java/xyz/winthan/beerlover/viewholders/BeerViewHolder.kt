package xyz.winthan.beerlover.viewholders

import android.view.View
import kotlinx.android.synthetic.main.list_item_beer.view.*
import xyz.winthan.beerlover.GlideApp
import xyz.winthan.beerlover.R
import xyz.winthan.beerlover.itemclicklistener.OnItemClickListener
import xyz.winthan.beerlover.vos.BeerVO

/**
 * Created by winthanhtike on 11/14/17.
 */
class BeerViewHolder(itemView: View?, itemClickListener : OnItemClickListener) : BaseViewHolder<BeerVO>(itemView) {

    val itemClickListener : OnItemClickListener = itemClickListener

    var mData : BeerVO? = null

    override fun bind(data: BeerVO) {

        this.mData = data

        GlideApp.with(itemView.context)
                .load(data.imageUrl)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .into(itemView.iv_beer_image)

        itemView.tv_beer_name.text = data.name

    }

    override fun onClick(v: View?) {
        super.onClick(v)

        itemClickListener.onTapSong(mData)

    }

}