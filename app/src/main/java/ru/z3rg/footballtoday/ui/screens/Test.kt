package ru.z3rg.footballtoday.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true)
@Composable
fun Test() {

    data class BottomMenuItem(val label: String, val icon: ImageVector)

    fun prepareBottomMenu(): List<BottomMenuItem> {
        val bottomMenuItemsList = arrayListOf<BottomMenuItem>()

        // add menu items
        bottomMenuItemsList.add(BottomMenuItem(label = "Home", icon = Icons.Filled.Home))
        bottomMenuItemsList.add(BottomMenuItem(label = "Profile", icon = Icons.Filled.Person))
        bottomMenuItemsList.add(BottomMenuItem(label = "Cart", icon = Icons.Filled.ShoppingCart))
        bottomMenuItemsList.add(BottomMenuItem(label = "Settings", icon = Icons.Filled.Settings))

        return bottomMenuItemsList
    }

    var selectedItem by remember {
        mutableStateOf("Home")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BottomAppBar(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
        ) {
            prepareBottomMenu().forEach {
                this.NavigationBarItem(
                    selected = (selectedItem == it.label),
                    onClick = {
                        selectedItem = it.label
                    },
                    icon = {
                        Icon(imageVector = it.icon, contentDescription = it.label)
                    },
                    label = {
                        Text(text = it.label)
                    })
            }

        }
    }

}