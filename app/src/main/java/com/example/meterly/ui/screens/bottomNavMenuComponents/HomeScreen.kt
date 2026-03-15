package com.example.meterly.ui.screens.bottomNavMenuComponents

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.meterly.ui.components.homeScreen.bottomSectionComp.BottomSectionHomeScreen
import com.example.meterly.ui.components.homeScreen.middleSectionComp.MiddleSectionHomeScreen
import com.example.meterly.ui.components.homeScreen.TopSectionHomeScreen
import com.example.meterly.ui.theme.SecondaryGradient

@Composable
@Preview
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController? = null){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SecondaryGradient())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TopSectionHomeScreen()

            MiddleSectionHomeScreen()

            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(size = 24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "Статус платежів",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                BottomSectionHomeScreen()
            }
        }
    }
}