package xyz.winthan.beerlover.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import xyz.winthan.beerlover.viewholders.BaseViewHolder

/**
 * Created by winthanhtike on 11/11/17.
 */
abstract class BaseRecyclerAdapter<T : BaseViewHolder<*>, W>(mContext : Context) : RecyclerView.Adapter<T>() {

    protected var mLayoutInflater : LayoutInflater

    protected var mData : List<W>? = null

    init {
        mLayoutInflater = LayoutInflater.from(mContext)
        mData = ArrayList()
    }

    override fun getItemCount() : Int {
        return mData!!.size
    }

    fun setNewData(newData : List<W>?) {
        mData = newData
        notifyDataSetChanged()
    }

}