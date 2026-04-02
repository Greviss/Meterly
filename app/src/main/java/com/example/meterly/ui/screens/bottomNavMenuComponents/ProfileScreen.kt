package com.example.meterly.ui.screens.bottomNavMenuComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.meterly.ui.components.profileScreen.BottomSectionProfileScreen
import com.example.meterly.ui.components.profileScreen.MiddleSectionProfileScreen
import com.example.meterly.ui.components.profileScreen.TopSectionProfileScreen
import com.example.meterly.ui.theme.secondaryGradient

@Composable
@Preview
fun ProfileScreen(navController: NavHostController ?= null) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(secondaryGradient())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TopSectionProfileScreen(
                imageUrl = null
            )

            Spacer(modifier = Modifier.height(24.dp))

            MiddleSectionProfileScreen()

            Spacer(modifier = Modifier.height(24.dp))

            BottomSectionProfileScreen()
        }
    }
}