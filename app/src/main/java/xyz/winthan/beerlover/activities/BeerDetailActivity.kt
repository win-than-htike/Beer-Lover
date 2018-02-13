package xyz.winthan.beerlover.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_beer_detail.*
import xyz.winthan.beerlover.GlideApp
import xyz.winthan.beerlover.R
import xyz.winthan.beerlover.data.viewmodels.BeerViewModel

class BeerDetailActivity : AppCompatActivity() {

    companion object {

        private val BEERID : String = "BEERID"

        fun newIntent(context: Context, id: Int) : Intent {
            val intent = Intent(context, BeerDetailActivity::class.java)
            intent.putExtra(BEERID, id)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_detail)

        val id : Int = intent.getIntExtra(BEERID, 0)

        var viewModels = ViewModelProviders.of(this).get(BeerViewModel::class.java)
        viewModels.getSingleBeers(id)?.subscribeOn(Schedulers.newThread())?.observeOn(AndroidSchedulers.mainThread())?.subscribe {
            t ->
            tv_desc.text = t.description
            GlideApp.with(applicationContext)
                    .load(t.imageUrl)
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_error)
                    .into(iv_beer_image)
        }

    }
}
