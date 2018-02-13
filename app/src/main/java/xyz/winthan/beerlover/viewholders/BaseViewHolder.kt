package xyz.winthan.beerlover.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

/**
 * Created by winthanhtike on 11/11/17.
 */
abstract class BaseViewHolder<T>(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {

        ButterKnife.bind(this, itemView!!)

        itemView.setOnClickListener(this)

        val eventBus = EventBus.getDefault()
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this)
        }

    }

    override fun onClick(v: View?) {

    }

    abstract fun bind(data : T)

    @Subscribe
    fun onEvent(obj : Objects){

    }

}