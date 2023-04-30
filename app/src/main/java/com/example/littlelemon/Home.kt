package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Home(databaseMenuItems: List<MenuItemRoom>, navController : NavHostController) {
    var searchPhrase by remember { mutableStateOf("") }
    LittleLemonTheme {
        Column(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(modifier = Modifier.size(24.dp), onClick = {
                }) {

                }
                Image(
                    painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
                    contentDescription = "Little Lemon Logo",
                    modifier = Modifier
                        .fillMaxWidth(0.5F)
                        .padding(horizontal = 20.dp)
                )

                IconButton(onClick = { navController.navigate(Profile.route) }) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_icon),
                        contentDescription = "profile icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .background(LittleLemonColor.green)
                    .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = LittleLemonColor.yellow
                )
                Text(
                    text = stringResource(id = R.string.location),
                    fontSize = 24.sp,
                    color = LittleLemonColor.cloud
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.description),
                        style = MaterialTheme.typography.body1,
                        color = LittleLemonColor.cloud,
                        modifier = Modifier
                            .padding(bottom = 28.dp, end = 20.dp)
                            .fillMaxWidth(0.6f)

                    )
                    Image(
                        painter = painterResource(id = R.drawable.upperpanelimage),
                        contentDescription = "Upper Panel Image",
                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    )
                }

                OutlinedTextField(
                    value = searchPhrase,
                    onValueChange = { searchPhrase = it },
                    placeholder = { Text(text = "Enter search phrase") },
                    leadingIcon = {  Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "search icon")  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .background(color = LittleLemonColor.cloud)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
            ) {
                OrderText()
                LazyRow {
                    items(databaseMenuItems) { category ->
                        MenuCategory(category.category)
                    }
                }
                Divider(
                    modifier = Modifier.padding(8.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
                if (searchPhrase.isEmpty()) {
                    MenuDish(databaseMenuItems)
                } else {
                    MenuDish(databaseMenuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) })
                }

            }
        }
    }
}

@Composable
fun OrderText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.order_text).uppercase(),
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun MenuCategory(category: String){
    Button(onClick = {},
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
        shape = RoundedCornerShape(40),
        modifier = Modifier.padding(5.dp)
    ){
        Text(
            text = category
        )
    }
}



@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun MenuDish(menuItems: List<MenuItemRoom>) {
    LazyColumn {
        items(menuItems) { dish ->
            Card(onClick = {
            }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column() {
                        Text(text = dish.title, style = MaterialTheme.typography.h2)
                        Text(
                            text = dish.description,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .padding(top = 5.dp, bottom = 5.dp)
                        )
                        Text(text = "$${dish.price}", style = MaterialTheme.typography.body2)

                    }
                    GlideImage(
                        model = dish.image,
                        contentDescription = dish.title,
                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    )
                }

            }
            Divider(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                thickness = 0.6.dp,
                color = LittleLemonColor.charcoal
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LittleLemonTheme {
       //Home()
    }
}