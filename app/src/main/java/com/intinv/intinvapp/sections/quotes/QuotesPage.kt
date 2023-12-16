package com.intinv.intinvapp.sections.quotes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.intinv.intinvapp.sections.transaction.AddTransactionDialog
import com.intinv.intinvapp.Logo
import com.intinv.intinvapp.R
import com.intinv.intinvapp.ui.theme.inter

@Composable
fun QuotesPage(
    navController: NavHostController,
    // viewModel: QuotesViewModel TODO - implement
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Logo()
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            // TODO - screenState.QuotesData.foreach
            item{ QuoteItem("17:13:00","GOTO", "GoTo Gojek Tokopedia Tbk", 16778.0, -25.0,) }
            item{ QuoteItem("17:13:00","PTBA", "Bukit Asam Tbk", 2358.0, 15.2) }
            item{ QuoteItem("17:13:00","ACES", "Ace Hardware Indonesia Tbk", 14954.0, 73.7) }
            item{ QuoteItem("17:13:00","ACES", "Ace Hardware Indonesia Tbk", 14954.0, 73.7) }
            item{ QuoteItem("17:13:00","ACES", "Ace Hardware Indonesia Tbk", 14954.0, 73.7) }
            item{ QuoteItem("17:13:00","ACES", "Ace Hardware Indonesia Tbk", 14954.0, 73.7) }
            item{ QuoteItem("17:13:00","ACES", "Ace Hardware Indonesia Tbk", 14954.0, 73.7) }
            item{ QuoteItem("17:13:00","ACES", "Ace Hardware Indonesia Tbk", 14954.0, 73.7) }
            item{ QuoteItem("17:13:00","ACES", "Ace Hardware Indonesia Tbk", 14954.0, 73.7) }
            item{ QuoteItem("17:13:00","ACES", "Ace Hardware Indonesia Tbk", 14954.0, 73.7) }
        }
    }
}

@Composable
fun QuoteItem(
    curentTime: String,
    name: String,
    fullName: String,
    priceValue: Double,
    changeValue: Double
) {
    val stateDialog = remember {
        mutableStateOf(false)
    }
    if(stateDialog.value){
        AddTransactionDialog(
            clickBack = { stateDialog.value = false },
            stateDialog = stateDialog,
            nameTicket = name)
    }
    Column(modifier = Modifier
        .heightIn(min = 60.dp, max = 100.dp)
        .padding(5.dp)
        .clickable(onClick = {stateDialog.value = true})
        ){
        Row {
            Image(painter = painterResource(id = R.drawable.home_icon1),  contentDescription = "Icon",
                modifier = Modifier.size(45.dp))
            Column(modifier = Modifier
                .heightIn(max = 45.dp)
                .fillMaxHeight()
                .padding(5.dp),
                verticalArrangement = Arrangement.Center){
                Text(fontFamily= inter,
                    fontSize = 14.0.sp,
                    text = name)
                Text(fontFamily= inter,
                    fontSize = 12.0.sp,
                    text = fullName,
                    color = Color.Gray
                )
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Text(fontFamily= inter,
                    fontSize = 12.0.sp,
                    text = "Time")
                Text(fontFamily= inter,
                    fontSize = 12.0.sp,
                    text = "Last price")
                Text(fontFamily= inter,
                    fontSize = 12.0.sp,
                    text = "Change per day")
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(fontFamily= inter,
                fontSize = 12.0.sp,
                text = curentTime)
            Text(fontFamily= inter,
                fontSize = 12.0.sp,
                text = "$priceValue ₽")
            Text(fontFamily= inter,
                fontSize = 12.0.sp,
                text = (if(changeValue > 0) "+" else "") + "$changeValue %",
                color = if (changeValue > 0) Color.Green else Color.Red)
        }
    }
}



