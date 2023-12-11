package com.intinv.intinvapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intinv.intinvapp.ui.theme.IntInvAppTheme
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.intinv.intinvapp.ui.theme.AppGray
import com.intinv.intinvapp.ui.theme.AppYellow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

@Preview(widthDp = 390, heightDp = 844)
@Composable
fun PortfolioDetailPage(){
    val stateDialog = remember {
        mutableStateOf(false)
    }
    if (stateDialog.value){
        AddTransactionDialog(stateDialog = stateDialog)
    }
    Column(modifier = Modifier
        .fillMaxSize()
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
                        modifier = Modifier.padding(2.dp)
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
                name = "ACES",
                fullName = "Ace Hardware Indonesia Tbk",
                AlocateValue = 700,
                ProfValue = 5.0,
                ProfDrop = 0.72
            )
            Detail(
                modifier = Modifier,
                UserName = "Nilai",
                AlocateValue = 25.32,
                LotValue = 26,
                AveragePriceValue = 760.96,
                InvValue = 1978496.0,
                ProfValue = 158496.0,
                ProfDrop = 0.72
            )
            History()
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
fun Title(modifier: Modifier, name: String, fullName: String, AlocateValue: Int, ProfValue: Double, ProfDrop: Double) {
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
            Text(fontFamily=inter,
                fontSize = 14.0.sp,
                text = name)
            Text(fontFamily=inter,
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
                text = AlocateValue.toString()
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
fun Detail(modifier: Modifier, UserName: String, AlocateValue: Double, LotValue: Int, AveragePriceValue: Double, InvValue: Double, ProfValue: Double, ProfDrop: Double){
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
                    text = "$AlocateValue %"
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
fun History() {
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
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong()),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
                )
            }
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}
            item {itemHistory(
                DateTransaction = Date(1565209665.toLong() ),
                TypeTransaction = "Buy",
                LocValueTransaction = 2,
                InvValueTransaction = 141000.0
            )}

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
                text = LocValueTransaction.toString() + " Lot"
            )
            Text(
                fontFamily = inter,
                fontSize = 12.0.sp,
                text = InvValueTransaction.toString()  + " ₽"
            )

        }
    }

}

