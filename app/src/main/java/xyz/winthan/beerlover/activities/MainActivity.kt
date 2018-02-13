package xyz.winthan.beerlover.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import xyz.winthan.beerlover.R
import xyz.winthan.beerlover.adapters.BeerListAdapter
import xyz.winthan.beerlover.data.viewmodels.BeerViewModel
import xyz.winthan.beerlover.itemclicklistener.OnItemClickListener
import xyz.winthan.beerlover.vos.BeerVO

class MainActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var mAdapter : BeerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        mAdapter = BeerListAdapter(this, this)

        var beerViewModel : BeerViewModel = ViewModelProviders.of(this).get(BeerViewModel::class.java)
        beerViewModel.getBeers()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe {
                    t -> mAdapter.setNewData(t)
        }

        rv_beer!!.setHasFixedSize(true)
        rv_beer!!.layoutManager = GridLayoutManager(this, 2)
        rv_beer!!.adapter = mAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTapSong(beerVO: BeerVO?) {
        startActivity(BeerDetailActivity.newIntent(this, beerVO?.id!!))
    }

}
