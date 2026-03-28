package com.example.meterly.ui.components.paymentsScreen.paymentsScreens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.waterScreenComp.BottomSectionWaterScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.waterScreenComp.MiddleSectionWaterScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.waterScreenComp.TopSectionWaterScreen
import com.example.meterly.ui.theme.secondaryGradient

@Composable
@Preview
fun WaterScreen(onLeftArrowWater: () -> Unit = {},
                onRightArrowWater: () -> Unit = {}) {
    var monthBegin2 by remember { mutableStateOf("") }
    var monthEnd2 by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(secondaryGradient())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSectionWaterScreen(onLeftArrowWater = onLeftArrowWater,
                onRightArrowWater = onRightArrowWater)

            Spacer(modifier = Modifier.height(24.dp))

            MiddleSectionWaterScreen(
                monthBegin2 = monthBegin2,
                onMonthBeginChange2 = { monthBegin2 = it },
                monthEnd2 = monthEnd2,
                onMonthEndChange2 = { monthEnd2 = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomSectionWaterScreen()
        }
    }
}