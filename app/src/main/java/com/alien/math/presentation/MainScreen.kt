package com.alien.math.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alien.math.MainViewModel
import com.alien.math.model.PostModel
import com.alien.math.model.ResponseModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier, mainViewModel: MainViewModel, context: Context) {


    Column(
        modifier = modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        var text by remember { mutableStateOf(TextFieldValue("")) }
        val resposeData by mainViewModel.responseData.observeAsState()


        OutlinedTextField(
            value = text,
            label = { Text(text = "Enter Your Expression") },
            onValueChange = {
                text = it
                Log.d("alien", "text ${it.text}")
            },
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(6.dp)
        )

        Button(
            onClick = {
                val expressionList = text.text.split("\r?\n|\r".toRegex())
                val postModel = PostModel(expressionList, 0)
                mainViewModel.getEvalute(postModel, context)
            },
            modifier = modifier
                .padding(10.dp)
        ) {
            Text(text = "Evaluate")
        }

        Card(
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(190.dp)
        ) {
            val answers = resposeData?.result
            if (answers != null) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    items(answers) {
                        Text(
                            text = it,
                            modifier = modifier
                                .align(Alignment.CenterHorizontally),
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else {
                Text(
                    text = "Answer $$$$",
                    modifier = modifier
                        .align(Alignment.CenterHorizontally),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    Toast.makeText(context, "Work in progress", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(imageVector = Icons.Default.List, contentDescription = "History")

                }
            },
            modifier = modifier
                .padding(10.dp)
        ) { padding ->
            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

            }
        }


    }
}


