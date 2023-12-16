package com.intinv.intinvapp.sections.portfolio

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.intinv.intinvapp.LOG_TAG
import com.intinv.intinvapp.Logo
import com.intinv.intinvapp.R
import com.intinv.intinvapp.Screen
import com.intinv.intinvapp.sections.portfolio.domain.LoadPortfolio
import com.intinv.intinvapp.sections.portfolio.viewModel.PortfolioViewModel
import com.intinv.intinvapp.ui.theme.AppYellow
import com.intinv.intinvapp.ui.theme.inter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PortfolioPage(
    navController: NavHostController,
    viewModel: PortfolioViewModel
) {
    val screenState = viewModel.screenState.collectAsState().value
    Log.d(LOG_TAG, "screenState = $screenState")

    val pullRefreshState = rememberPullRefreshState(
        refreshing = screenState.isLoading,
        onRefresh = {
            viewModel.handleIntent(LoadPortfolio)
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val clickDetail: (String) -> Unit = {
                navController.navigate("${Screen.PortfolioDetail.label}/$it")
            }

            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Logo()
                }
                item {
                    Portfolio( // TODO - use data from screenState.portfolioData
                        fullValue = 3366924.0,
                        fullProfLossValue = -37410.0,
                        captioValue = -2.3,
                        openValue = 23231.0
                    )
                }
            }
            PortfolioStockList(clickDetail)
        }

        PullRefreshIndicator(
            refreshing = screenState.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}


@Composable
fun Portfolio(
    fullValue: Double,
    fullProfLossValue: Double,
    captioValue: Double,
    openValue: Double
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 167.dp, max = 167.dp)
            .padding(
                PaddingValues(
                    start = 23.0.dp,
                    top = 0.0.dp,
                    end = 23.0.dp,
                    bottom = 0.0.dp
                )
            )
            .background(
                AppYellow,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            fontFamily = inter,
            fontSize = 16.0.sp,
            text = "Portfolio"
        )
        Text(
            fontFamily = inter,
            fontSize = 24.0.sp,
            text = "$fullValue ₽"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "Profit/Loss"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "$fullProfLossValue ₽",
                    color = if (fullProfLossValue > 0) Color.Green else Color.Red
                )
            }
            Column {
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "Cation gain"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "$captioValue ₽",
                    color = if (captioValue > 0) Color.Green else Color.Red
                )
            }
            Column {
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "Open"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "$openValue  ₽"
                )
            }
        }
    }
}


@Composable
fun PortfolioStockList(
    clickDetail: (name: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO - screenState.StockItem.foreach
        item {
            PortfolioStockItem(
                "GOTO",
                "GoTo Gojek Tokopedia Tbk",
                1674678.0,
                -52742.0,
                20.0,
                clickDetail
            )
        }
        item { PortfolioStockItem("PTBA", "Bukit Asam Tbk", 237588.0, 2765.0, 20.0, clickDetail) }
        item {
            PortfolioStockItem(
                "ACES",
                "Ace Hardware Indonesia Tbk",
                1454658.0,
                12567.0,
                20.0,
                clickDetail
            )
        }
    }
}


@Composable
fun PortfolioStockItem(
    name: String,
    fullName: String,
    invValue: Double,
    profValue: Double,
    alocateValue: Double,
    clickDetail: (name: String) -> Unit
) {
    Column(modifier = Modifier
        .heightIn(min = 60.dp, max = 100.dp)
        .clickable {
            clickDetail(name)
        }
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.home_icon1), contentDescription = "Icon",
                modifier = Modifier
                    .size(45.dp)
                    .padding(5.dp)
            )
            Column(
                modifier = Modifier
                    .heightIn(max = 45.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    fontFamily = inter,
                    fontSize = 14.0.sp,
                    text = name
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = fullName,
                    color = Color.Gray
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "Investment"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "Profit/Loss"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "Allocate"
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = "$invValue ₽"
            )
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = "$profValue ₽",
                color = if (profValue > 0) Color.Green else Color.Red
            )
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = "$alocateValue Lot"
            )
        }
    }
}


