package com.intinv.intinvapp.sections.portfolioDetail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.intinv.intinvapp.LOG_TAG
import com.intinv.intinvapp.sections.transaction.AddTransactionDialog
import com.intinv.intinvapp.R
import com.intinv.intinvapp.domain.PortfolioDetail
import com.intinv.intinvapp.domain.Transaction
import com.intinv.intinvapp.sections.portfolio.domain.LoadPortfolio
import com.intinv.intinvapp.sections.portfolioDetail.domain.LoadPortfolioDetail
import com.intinv.intinvapp.sections.portfolioDetail.viewModel.PortfolioDetailViewModel
import com.intinv.intinvapp.sections.transaction.viewModel.AddTransactionViewModel
import com.intinv.intinvapp.ui.theme.AppGray
import com.intinv.intinvapp.ui.theme.AppYellow
import com.intinv.intinvapp.ui.theme.inter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PortfolioDetailPage(
    addTransactionViewModel: AddTransactionViewModel,
    navController: NavHostController,
    viewModel: PortfolioDetailViewModel,
    selectedDetailName: String? = null,
) {
    val cal = Calendar.getInstance()
    cal.time = Date(1565209665L)

    val screenState = viewModel.screenState.collectAsState().value
    Log.d(LOG_TAG, "screenState = $screenState")

    val pullRefreshState = rememberPullRefreshState(
        refreshing = screenState.isLoading,
        onRefresh = {
            viewModel.handleIntent(LoadPortfolioDetail, selectedDetailName?:"")
        }
    )

    val stateDialog = remember {
        mutableStateOf(false)
    }
    if (stateDialog.value && selectedDetailName != null) {
        AddTransactionDialog(
            addTransactionViewModel,
            clickBack = {
                navController.navigate("Portfolio")
            },
            stateDialog = stateDialog,
            nameTicket = selectedDetailName
        )
    } else {
        if(stateDialog.value ) {
            AlertDialog(modifier = Modifier.fillMaxWidth(),
                onDismissRequest = { },
                buttons = { },
                title = {
                    IconButton(
                        onClick = { navController.navigate("Portfolio") }
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(2.dp)
                        )
                    }
                    Text(text = "Ошибка в получении данных.")
                }
            )
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .pullRefresh(pullRefreshState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
                .background(Color.White)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.93F),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(2.dp)
                                .clickable {
                                    navController.popBackStack()
                                }
                        )
                    }
                    Text(
                        fontFamily = inter,
                        fontSize = 16.0.sp,
                        text = "Investment Detail"
                    )
                }
                screenState.portfolioDetailData?.let { data ->
                    Title(
                        modifier = Modifier.fillMaxWidth(),
                        name = data.ticket,
                        fullName = data.fullName,
                        allocateValue = data.allocate,
                        profValue = data.profValue,
                        profDrop = data.profDrop
                    )
                    Detail(
                        modifier = Modifier,
                        userName = data.userName,
                        allocateValue = data.allocateValue,
                        lotValue = data.lotValue,
                        averagePriceValue = data.averagePriceValue,
                        invValue = data.invValue,
                        profValue = data.profValue,
                        profDrop = data.profDrop
                    )
                    History(data.historyTransaction)
                }
            }
            Button(
                onClick = {
                    stateDialog.value = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(top = 5.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AppYellow
                )

            ) {
                Text("Sell", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun Title(
    modifier: Modifier,
    name: String,
    fullName: String,
    allocateValue: Int,
    profValue: Double,
    profDrop: Double
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_icon1),
            contentDescription = "Icon",
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
        Column(
            modifier = Modifier
                .heightIn(max = 45.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = allocateValue.toString()
            )
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = profValue.toString() + " (" + profDrop.toString() + " %" + ")",
                color = if (profValue > 0) Color.Green else Color.Red
            )
        }
    }
}

@Composable
fun Detail(
    modifier: Modifier,
    userName: String,
    allocateValue: Double,
    lotValue: Int,
    averagePriceValue: Double,
    invValue: Double,
    profValue: Double,
    profDrop: Double
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 167.dp, max = 167.dp)
            .padding(
                PaddingValues(
                    start = 0.0.dp,
                    top = 10.0.dp,
                    end = 0.0.dp,
                    bottom = 0.0.dp
                )
            )
            .background(
                AppGray,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Text(
            modifier = Modifier.padding(PaddingValues(start = 10.dp)),
            fontFamily = inter,
            fontSize = 16.0.sp,
            text = userName
        )
        Row()
        {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5F)
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "Allocate"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "Lot"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "Average Price"
                )
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
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "$allocateValue %"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = lotValue.toString()
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = averagePriceValue.toString()
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "$invValue ₽"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = "$profValue ₽ ($profDrop%)",
                    color = if (profValue > 0) Color.Green else Color.Red
                )
            }
        }
    }
}

@Composable
fun History(historyTransaction: List<Transaction>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                PaddingValues(
                    start = 0.0.dp,
                    top = 10.0.dp,
                    end = 0.0.dp,
                    bottom = 0.0.dp
                )
            )
            .background(
                AppGray,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            fontFamily = inter,
            fontSize = 16.0.sp,
            text = "Transaction History"
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            historyTransaction.forEach {
                item {
                    itemHistory(
                        dateTransaction = it.dateTransaction,
                        typeTransaction = it.type,
                        locValueTransaction = it.quantity,
                        invValueTransaction = it.price
                    )
                }
            }
        }
    }
}

@Composable
fun itemHistory(
    dateTransaction: Long,
    typeTransaction: String,
    locValueTransaction: Int,
    invValueTransaction: Double
) {
    Column {
        Text(text = SimpleDateFormat("dd MMMM yyyy").format(Date(dateTransaction)).toString())
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = typeTransaction.toString()
            )
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = "$locValueTransaction Lot"
            )
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = "$invValueTransaction ₽"
            )
        }
    }
}

