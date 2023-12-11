package com.intinv.intinvapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.intinv.intinvapp.ui.theme.AppGray
import com.intinv.intinvapp.ui.theme.AppYellow


@Composable
fun navigationMain(navHostController: NavHostController){
    val selectedDetailName = remember {
        mutableStateOf("")
    }
    val stateDialog = remember {
        mutableStateOf(false)
    }
    NavHost(navController = navHostController, startDestination = "QuotesScreen"){
        composable("QuotesScreen"){
            QuotesPage()
        }
        composable("PortfolioScreen"){
            PortfolioPage(clickDetail = {
                navHostController.navigate("PortfolioDetailScreen")
                selectedDetailName.value = it
            })
        }
        composable("AddTransactionScreen"){
//            stateDialog.value = true
//            AddTransactionDialog(clickBack = {
//                navHostController.navigate("PortfolioScreen")
//            }, stateDialog = stateDialog)
        }
        composable("PortfolioDetailScreen"){
            PortfolioDetailPage(clickBack = {
                navHostController.navigate("PortfolioScreen")
            }, selectedDetailName)
            if (stateDialog.value){
                AddTransactionDialog(clickBack = {
                    navHostController.navigate("PortfolioScreen")
                }, nameTicket = selectedDetailName.value, stateDialog = stateDialog)
            }
        }
    }
}

@Composable
fun menuBar(navController: NavController){
    val listButton = listOf(
        ButtonMenu.Button1,
        ButtonMenu.Button2,
        ButtonMenu.Button3,
    )
    BottomNavigation(
        backgroundColor = AppGray
    ){
        val selectedScreen by navController.currentBackStackEntryAsState()
        val currentRout = selectedScreen?.destination?.route
        listButton.forEach { item ->
            BottomNavigationItem(selected = currentRout == item.screen,
                onClick = { navController.navigate(item.screen) },
                icon = {Icon(item.icon, contentDescription = null)},
                label = {
                    Text(text = item.title,
                        fontSize = 9.sp)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black
            )
        }

    }
}


@Composable
fun Logo(){
    Row(modifier = Modifier.padding(16.dp),
        verticalAlignment =  Alignment.CenterVertically){
        Image(painter = painterResource(id = R.drawable.home_vector3),  contentDescription = "Icon",
            modifier = Modifier.size(20.dp))
        Text(fontFamily=inter,
            fontSize = 18.0.sp,
            text = "IntInvApp")
    }
}


sealed class ButtonMenu(var title: String, var icon: ImageVector, val screen: String){
    object Button1: ButtonMenu("Portfolio", Icons.Filled.AccountCircle, "PortfolioScreen")
    object Button2: ButtonMenu("AddTransaction", Icons.Filled.Add, "AddTransactionScreen")
    object Button3: ButtonMenu("Quotes", Icons.Filled.List, "QuotesScreen")
}

