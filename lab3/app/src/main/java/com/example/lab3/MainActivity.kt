package com.example.lab3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab3.ui.theme.Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab3Theme {
                ShoppingCartApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShoppingCartApp(){
    var data = remember { mutableStateListOf<String>()}
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping Cart")},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.LightGray,
                    titleContentColor = Color.Magenta
                ))
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { data.add("Item Name") },
                containerColor = Color.Magenta) {
                Icon(Icons.Filled.Add,
                    "Add new Items",
                    tint = Color.White)
            }
        }
    ){
        LazyColumn(modifier = Modifier.padding(it)) {
            items(data) {item ->
                CartItem(item)
            }
        }
    }
}

@Composable
private fun CartItem(itemname:String) {
    var amount : Int by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ){
        Text(
            "$itemname",
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        )
        IconButton(onClick = {
            if (amount > 0) {
                amount--
            }
        }) {
            Icon(Icons.Filled.ArrowBack, "Decrease")
        }
        Text(
            "$amount",
        )
        IconButton(onClick = { amount++ }) {
            Icon(Icons.Filled.ArrowForward,
                "Increase")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartPreview() {
    ShoppingCartApp()
}