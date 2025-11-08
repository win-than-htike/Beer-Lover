package xyz.winthan.beerlover.ui.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import xyz.winthan.beerlover.data.viewmodels.BeerViewModel
import xyz.winthan.beerlover.ui.screens.BeerDetailScreen
import xyz.winthan.beerlover.ui.screens.BeerListScreen
import xyz.winthan.beerlover.vos.BeerVO

sealed class Screen(val route: String) {
    object BeerList : Screen("beer_list")
    object BeerDetail : Screen("beer_detail/{beerId}") {
        fun createRoute(beerId: Int) = "beer_detail/$beerId"
    }
}

@Composable
fun BeerNavGraph(
    navController: NavHostController,
    viewModel: BeerViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BeerList.route
    ) {
        composable(Screen.BeerList.route) {
            var beers by remember { mutableStateOf<List<BeerVO>>(emptyList()) }
            val disposables = remember { CompositeDisposable() }

            DisposableEffect(Unit) {
                val subscription = viewModel.getBeers()
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { beerList ->
                        beers = beerList
                    }
                subscription?.let { disposables.add(it) }

                onDispose {
                    disposables.clear()
                }
            }

            BeerListScreen(
                beers = beers,
                onBeerClick = { beerId ->
                    navController.navigate(Screen.BeerDetail.createRoute(beerId))
                }
            )
        }

        composable(
            route = Screen.BeerDetail.route,
            arguments = listOf(navArgument("beerId") { type = NavType.IntType })
        ) { backStackEntry ->
            val beerId = backStackEntry.arguments?.getInt("beerId") ?: 0
            var beer by remember { mutableStateOf<BeerVO?>(null) }
            val disposables = remember { CompositeDisposable() }

            DisposableEffect(beerId) {
                val subscription = viewModel.getSingleBeers(beerId)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { beerData ->
                        beer = beerData
                    }
                subscription?.let { disposables.add(it) }

                onDispose {
                    disposables.clear()
                }
            }

            BeerDetailScreen(
                beer = beer,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
