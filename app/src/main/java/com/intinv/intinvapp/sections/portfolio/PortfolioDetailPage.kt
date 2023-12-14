package com.intinv.intinvapp.sections.portfolio


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intinv.intinvapp.sections.transaction.AddTransactionDialog
import com.intinv.intinvapp.R
import com.intinv.intinvapp.domain.DataPortfolioDetail
import com.intinv.intinvapp.domain.DataTransaction
import com.intinv.intinvapp.ui.theme.AppGray
import com.intinv.intinvapp.ui.theme.AppYellow
import com.intinv.intinvapp.ui.theme.inter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

//@Preview(widthDp = 390, heightDp = 844)
@Composable
fun PortfolioDetailPage(
    clickBack: () -> Unit,
    selectedDetailName: MutableState<String>
) {
    val cal = Calendar.getInstance()
    cal.time = Date(1565209665L)

    val tetsHist = DataTransaction(
        type = "Buy",
        name = "ACES",
        dateTransaction = cal,
        quantity = 2,
        price = 1421321414.0
    )

    val data = DataPortfolioDetail(
        ticket = "ACES",
        fullName = "Ace Hardware Indonesia Tbk",
        allocate = 700,
        profValue = 5.0,
        profDrop = 0.72,
        userName = "Nilai",
        allocateValue = 25.32,
        lotValue = 26,
        averagePriceValue = 760.96,
        invValue =  0.72,
        historyTransaction = listOf(tetsHist,tetsHist,tetsHist,tetsHist,tetsHist,tetsHist,tetsHist)
    )

    val stateDialog = remember {
        mutableStateOf(false)
    }
    if (stateDialog.value){
        AddTransactionDialog(clickBack = {stateDialog.value = false}, stateDialog = stateDialog, nameTicket = selectedDetailName.value)
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.95f)
        .background(Color.White)
        .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.93F),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.padding(2.dp).clickable { clickBack() }
                    )
                }
                Text(
                    fontFamily = inter,
                    fontSize = 16.0.sp,
                    text = "Investment Detail"
                )
            }

            Title(
                modifier = Modifier.fillMaxWidth(),
                name = data.ticket,
                fullName = data.fullName,
                AllocateValue = data.allocate,
                ProfValue = data.profValue,
                ProfDrop = data.profDrop
            )
            Detail(
                modifier = Modifier,
                UserName = data.userName,
                AllocateValue = data.allocateValue,
                LotValue = data.lotValue,
                AveragePriceValue = data.averagePriceValue,
                InvValue = data.invValue,
                ProfValue = data.profValue,
                ProfDrop = data.profDrop
            )
            History(data.historyTransaction)
        }
        Button(
            onClick = {
                stateDialog.value = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(top = 5.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppYellow
            )

        ) {
            Text("Sell", fontSize = 18.sp)
        }
    }
}

@Composable
fun Title(
    modifier: Modifier,
    name: String,
    fullName: String,
    AllocateValue: Int,
    ProfValue: Double,
    ProfDrop: Double
) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.Start)
    {
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
            Text(fontFamily= inter,
                fontSize = 14.0.sp,
                text = name)
            Text(fontFamily= inter,
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
                text = AllocateValue.toString()
            )
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = ProfValue.toString() + " (" + ProfDrop.toString()  + " %" +")",
                color = if (ProfValue > 0) Color.Green else Color.Red
            )
        }
    }
}

@Composable
fun Detail(
    modifier: Modifier,
    UserName: String,
    AllocateValue: Double,
    LotValue: Int,
    AveragePriceValue: Double,
    InvValue: Double,
    ProfValue: Double,
    ProfDrop: Double
){
    Column(modifier = modifier
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
        )) {
        Text(modifier = Modifier.padding(PaddingValues(start = 10.dp)),
            fontFamily = inter,
            fontSize = 16.0.sp,
            text = UserName
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
                    text = "$AllocateValue %"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = LotValue.toString()
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = AveragePriceValue.toString()
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = InvValue.toString() + " ₽"
                )
                Text(
                    fontFamily = inter,
                    fontSize = 12.0.sp,
                    text = ProfValue.toString()  + " ₽" + " (" + ProfDrop.toString() + " %" + ")",
                    color = if (ProfValue > 0) Color.Green else Color.Red
                )
            }
        }
    }
}

@Composable
fun History(historyTransaction: List<DataTransaction> ) {
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
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            historyTransaction.forEach { it ->
                item {
                    itemHistory(
                    DateTransaction = it.dateTransaction.time,
                    TypeTransaction = it.type,
                    LocValueTransaction = it.quantity,
                    InvValueTransaction = it.price
                )
                }
            }
        }
    }
}

@Composable
fun itemHistory(DateTransaction: Date, TypeTransaction: String,  LocValueTransaction: Int,   InvValueTransaction: Double){
    Column {
        Text(text = SimpleDateFormat("dd MMMM yyyy").format(DateTransaction).toString())
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = TypeTransaction.toString()
            )
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = "$LocValueTransaction Lot"
            )
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = "$InvValueTransaction ₽"
            )
        }
    }
}

