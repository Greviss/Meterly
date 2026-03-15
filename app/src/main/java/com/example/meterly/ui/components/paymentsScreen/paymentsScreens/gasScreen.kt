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
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.gasScreenComp.BottomSectionGasScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.gasScreenComp.MiddleSectionGasScreen
import com.example.meterly.ui.components.paymentsScreen.paymentsScreens.gasScreenComp.TopSectionGasScreen
import com.example.meterly.ui.theme.SecondaryGradient

@Composable
fun GasScreen(navController: NavHostController? = null,
                   onMonthBeginChange: (String) -> Unit = { _ -> },
                   onMonthEndChange: (String) -> Unit = { _ -> }) {
    var monthBegin by remember { mutableStateOf("") }
    var monthEnd by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SecondaryGradient())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSectionGasScreen()

            Spacer(modifier = Modifier.height(24.dp))

            MiddleSectionGasScreen(
                monthBegin = monthBegin,
                onMonthBeginChange = onMonthBeginChange,
                monthEnd = monthEnd,
                onMonthEndChange = onMonthEndChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomSectionGasScreen()
        }
    }
}