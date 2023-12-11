package com.intinv.intinvapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.util.Log
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.intinv.intinvapp.ui.theme.AppGray
import com.intinv.intinvapp.ui.theme.AppYellow
import java.util.Calendar
import java.util.Locale


@Composable
fun AddTransactionDialog(stateDialog: MutableState<Boolean>){
    val listTypeTransaction = listOf("Buy","Sell","Dividend","Input", "Output")
    val selectedTypeIndex = remember { mutableStateOf(1) }
    val nameTicketValue = remember{ mutableStateOf("")}
    val quantityValue = remember{ mutableStateOf("")}
    val dateTimeValue = remember{ mutableStateOf("")}
    val priceValue = remember{ mutableStateOf("")}
    val calendar = remember { Calendar.getInstance() }
    AlertDialog(modifier = Modifier.fillMaxWidth(),
        onDismissRequest = { /*TODO*/ },
        buttons = { /*TODO*/ },
        title = {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { stateDialog.value = false }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.padding(2.dp)
                    )
                }
                Text(
                    fontFamily = inter,
                    fontSize = 16.0.sp,
                    text = "Add Transaction"
                )
            }
            TabRow(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
                selectedTabIndex = selectedTypeIndex.value,
                backgroundColor = Color.Transparent,
                contentColor = Color.Black ) {
                listTypeTransaction.forEachIndexed{index, text ->
                Tab(selected = selectedTypeIndex.value == index,
                    onClick = { selectedTypeIndex.value = index },
                    selectedContentColor = AppYellow,
                    unselectedContentColor = AppGray,
                    modifier = Modifier.wrapContentWidth()) {
                    Text(text = text,
                        color = Color.Black,
                        fontFamily=inter,
                        fontSize = 13.0.sp)
                }}
            }

            Column(modifier = Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start) {

                Text(text = "Name/Ticket",
                    fontFamily=inter,
                    fontSize = 14.sp)

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    value = nameTicketValue.value,
                    singleLine = true,
                    onValueChange = {nameTicketValue.value = it},
                    colors = TextFieldDefaults.textFieldColors(
                        textColor=Color.Black,
                        backgroundColor = AppGray,),
                    textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                )

                Text(text = "Quantity",
                    fontFamily=inter,
                    fontSize = 14.0.sp,
                    modifier =  Modifier.padding(top = 10.dp))


                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    value = quantityValue.value,
                    singleLine = true,
                    onValueChange = {quantityValue.value = it},
                    colors = TextFieldDefaults.textFieldColors(
                        textColor=Color.Black,
                        backgroundColor = AppGray,),
                    textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                )

                Text(text = "Date Time",
                    fontFamily=inter,
                    fontSize = 14.0.sp,
                    modifier =  Modifier.padding(top = 10.dp))

                DateTimePicker(calendar)

                Text(text = "Price",
                    fontFamily=inter,
                    fontSize = 14.0.sp,
                    modifier =  Modifier.padding(top = 10.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    value = priceValue.value,
                    singleLine = true,
                    onValueChange = {priceValue.value = it},
                    colors = TextFieldDefaults.textFieldColors(
                        textColor=Color.Black,
                        backgroundColor = AppGray,),
                    textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                )

            }
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(vertical = 10.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = AppYellow
                )

            ) {
                Text("ADD", fontSize = 18.sp)
            }
        }
    })
}

@Composable
fun DateTimePicker(calendar: Calendar) {
    val context = LocalContext.current
    val dateFormat = remember { SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()) }
//    val calendar = remember { Calendar.getInstance() }
    val currentDateString = remember { mutableStateOf(dateFormat.format(calendar.time)) }

    // A state to manage when to show the date or time picker
    val (showDatePicker, setShowDatePicker) = remember { mutableStateOf(false) }
    val (showTimePicker, setShowTimePicker) = remember { mutableStateOf(false) }

    // When the state is true, show the DatePickerDialog
    if (showDatePicker) {
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                setShowDatePicker(false) // Hide the DatePickerDialog
                setShowTimePicker(true) // Show the TimePickerDialog
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.setOnDismissListener { setShowDatePicker(false) }
        datePickerDialog.show()
    }

    // When the state is true, show the TimePickerDialog
    if (showTimePicker) {
        val timePickerDialog = TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                currentDateString.value = dateFormat.format(calendar.time)
                setShowTimePicker(false) // Hide the TimePickerDialog
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true // Use 24-hour format
        )
        timePickerDialog.setOnDismissListener { setShowTimePicker(false) }
        timePickerDialog.show()
    }

    // Display the date and time as text and when clicked, show the DatePickerDialog
    Text(
        text = currentDateString.value,
        fontFamily=inter,
        fontSize = 14.0.sp,
        modifier = Modifier.clickable{ setShowDatePicker(true) }
            .fillMaxWidth()
            .background(AppGray)
            .padding(vertical = 5.dp)
    )
}