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
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.lightScreenComp.BottomSectionLightScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.lightScreenComp.MiddleSectionLightScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.lightScreenComp.TopSectionLightScreen
import com.example.meterly.ui.theme.secondaryGradient

@Composable
@Preview
fun LightScreen(onLeftArrowLight: () -> Unit = {},
                onRightArrowLight: () -> Unit = {}) {
    var monthBegin3 by remember { mutableStateOf("") }
    var monthEnd3 by remember { mutableStateOf("") }

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
            TopSectionLightScreen(
                onLeftArrowLight = onLeftArrowLight,
                onRightArrowLight = onRightArrowLight
            )

            Spacer(modifier = Modifier.height(24.dp))

            MiddleSectionLightScreen(
                monthBegin3 = monthBegin3,
                onMonthBeginChange3 = { monthBegin3 = it },
                monthEnd3 = monthEnd3,
                onMonthEndChange3 = { monthEnd3 = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomSectionLightScreen()
        }
    }
}