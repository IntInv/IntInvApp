package com.intinv.intinvapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun menuBar(){

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