package ru.z3rg.footballtoday.ui.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun BottomAppBarComponent(
    bottomMenuItemList: ArrayList<BottomMenuItem>,
    item: String,
    mainHostController: NavHostController
) {

    var selectedItem = item

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter)
                .height(60.dp)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                bottomMenuItemList.forEach {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                selectedItem = it.label
                                when (selectedItem) {
                                    "Home" -> {
                                        mainHostController.navigate("main") {
                                            popUpTo("main") {
                                                inclusive = true
                                            }
                                        }
                                    }

                                    "Web View" -> {
                                        mainHostController.navigate("web_view") {
                                            popUpTo("web_view") {
                                                inclusive = true
                                            }
                                        }
                                    }
                                }
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (selectedItem == it.label) {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.label,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = it.label,
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        } else {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.label,
                            )
                            Text(
                                text = it.label,
                                style = TextStyle(
                                    textAlign = TextAlign.Center
                                )
                            )
                        }

                    }
                }
            }
        }
    }

}

