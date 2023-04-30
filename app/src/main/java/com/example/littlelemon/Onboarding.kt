package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import  com.example.littlelemon.ui.theme.LittleLemonColor
import  com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Onboarding(context: Context, navController : NavHostController){
    var firstName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    LittleLemonTheme {
        Column(
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
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LittleLemonColor.green)
            ) {
                Text(
                    text = stringResource(id = R.string.prompt_user),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    color = LittleLemonColor.cloud,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                        .fillMaxWidth(0.6f)
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
                Button(modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp),
                    onClick = {
                        if (firstName == "" || lastName == "" || email == "") {
                            Toast.makeText(
                                context,
                                "Registration unsuccessful. Please enter all data",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val sharedPreferences =
                                context.getSharedPreferences("LemonPrefs", Context.MODE_PRIVATE)
                            sharedPreferences.edit().putString("firstName", firstName)
                                .putString("lastName", lastName)
                                .putString("email", email).commit()
                            Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT)
                                .show()
                            navController.navigate(Home.route)
                        }
                    }
                ) {
                    Text(
                        "Register",
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    LittleLemonTheme{
      //  Onboarding(LocalContext.current, null)
    }
}