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
    val disposables = remember { CompositeDisposable() }

    DisposableEffect(Unit) {
        onDispose {
            disposables.clear()
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.BeerList.route
    ) {
        composable(Screen.BeerList.route) {
            var beers by remember { mutableStateOf<List<BeerVO>>(emptyList()) }

            LaunchedEffect(Unit) {
                viewModel.getBeers()
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { beerList ->
                        beers = beerList
                    }?.let { disposables.add(it) }
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

            LaunchedEffect(beerId) {
                viewModel.getSingleBeers(beerId)
                    ?.subscribeOn(Schedulers.newThread())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { beerData ->
                        beer = beerData
                    }?.let { disposables.add(it) }
            }

            BeerDetailScreen(
                beer = beer,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
