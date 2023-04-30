package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import  com.example.littlelemon.ui.theme.LittleLemonColor
import  com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Profile(context: Context, navController : NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    val sharedPreferences = context.getSharedPreferences("LemonPrefs", Context.MODE_PRIVATE)
    firstName = sharedPreferences.getString("firstName", firstName).toString()
    email = sharedPreferences.getString("email", email).toString()
    lastName = sharedPreferences.getString("lastName", lastName).toString()
    LittleLemonTheme {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
                    contentDescription = "Little Lemon Logo",
                    modifier = Modifier
                        .fillMaxWidth(0.5F)
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.personal_information),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h2,
                    color = LittleLemonColor.green,
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 35.dp)
                        .fillMaxWidth(0.6f)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.first_name),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                            .fillMaxWidth(0.6f)
                    )



                    OutlinedTextField(
                        value = firstName, onValueChange = { firstName = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.last_name),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                            .fillMaxWidth(0.6f)
                    )

                    OutlinedTextField(
                        value = lastName, onValueChange = { lastName = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.email),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                            .fillMaxWidth(0.6f)
                    )

                    OutlinedTextField(
                        value = email, onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp),
                    onClick = {
                        sharedPreferences.edit().clear().apply()
                        navController.navigate(Onboarding.route)
                    }
                ) {
                    Text(
                        "Log out",
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    LittleLemonTheme {
       // Profile(LocalContext.current)
    }
}