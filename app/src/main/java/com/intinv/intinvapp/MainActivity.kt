package com.intinv.intinvapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.intinv.intinvapp.ui.theme.IntInvAppTheme

const val LOG_TAG = "MainActivityLog"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntInvAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
//        testJedis()
    }

    private fun testJedis() {
        // TODO - make request to redis with proxying by ktor REST service
        // TODO - enter token with UI, not hardcode it
        /*val pool = JedisPool("10.0.2.2", 6379)
        pool.resource.use {
            it.set("clientName", "androidJedis")

            val jedisPubSub = object : JedisPubSub() {
                override fun onMessage(channel: String?, message: String?) {
                    super.onMessage(channel, message)
                    Log.d(LOG_TAG, "receiving message -$message- from channel $channel")
                }
            }

            it.subscribe(jedisPubSub, "mobiledev")
            it.set("foo", "bar")
            Log.d(LOG_TAG, it.get("foo"))
        }*/
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {menuBar(navController = navController)}
    ) {
        navigationMain(navHostController = navController)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntInvAppTheme {
        Greeting("Android")
    }
}
