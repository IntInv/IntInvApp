package com.intinv.intinvapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.intinv.intinvapp.sections.quotes.QuotesPage
import com.intinv.intinvapp.sections.portfolio.PortfolioDetailPage
import com.intinv.intinvapp.sections.portfolio.PortfolioPage
import com.intinv.intinvapp.sections.transaction.AddTransactionDialog
import com.intinv.intinvapp.ui.theme.AppGray
import com.intinv.intinvapp.ui.theme.inter

@Composable
fun navigationMain(navHostController: NavHostController) {
    val selectedDetailName = remember {
        mutableStateOf("")
    }

    NavHost(
        navController = navHostController,
        startDestination = NavigationItem.Quotes.screen
    ) {
        composable(NavigationItem.Quotes.screen) {
            QuotesPage()
        }
        composable(NavigationItem.Portfolio.screen) {
            PortfolioPage(
                clickDetail = {
                    navHostController.navigate("PortfolioDetailScreen")
                    selectedDetailName.value = it
                }
            )
        }
        composable("PortfolioDetailScreen") {
            PortfolioDetailPage(
                clickBack = {
                    navHostController.navigate("PortfolioScreen")
                },
                selectedDetailName
            )
        }
    }
}

@Composable
fun menuBar(navController: NavController) {
    val listButton = listOf(
        NavigationItem.Portfolio,
        NavigationItem.Transaction,
        NavigationItem.Quotes,
    )

    val stateDialog = remember {
        mutableStateOf(false)
    }
    if (stateDialog.value) {
        AddTransactionDialog(
            clickBack = {
                stateDialog.value = false
            },
            stateDialog = stateDialog
        )
    }

    BottomNavigation(
        backgroundColor = AppGray
    ) {
        val selectedScreen by navController.currentBackStackEntryAsState()
        val currentRout = selectedScreen?.destination?.route
        listButton.forEach { item ->
            BottomNavigationItem(
                selected = currentRout == item.screen,
                onClick = {
                    if (item.title == NavigationItem.Transaction.title)
                        stateDialog.value = true
                    else
                        navController.navigate(item.screen)
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Composable
fun Logo() {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_vector3), contentDescription = "Icon",
            modifier = Modifier.size(20.dp)
        )
        Text(
            fontFamily = inter,
            fontSize = 18.0.sp,
            text = "IntInvApp"
        )
    }
}

sealed class NavigationItem(
    var title: String,
    var icon: ImageVector,
    val screen: String
) {
    object Portfolio : NavigationItem("Portfolio", Icons.Filled.AccountCircle, "PortfolioScreen")
    object Transaction : NavigationItem("AddTransaction", Icons.Filled.Add, "AddTransactionScreen")
    object Quotes : NavigationItem("Quotes", Icons.Filled.List, "QuotesScreen")
}

