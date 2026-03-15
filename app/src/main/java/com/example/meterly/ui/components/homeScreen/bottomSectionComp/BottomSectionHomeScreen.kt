package com.example.meterly.ui.components.homeScreen.bottomSectionComp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomSectionHomeScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentSize()
    ) {
        GasCard()

        Spacer(modifier = Modifier.height(4.dp))

        WaterCard()

        Spacer(modifier = Modifier.height(4.dp))

        LightCard()

        Spacer(modifier = Modifier.height(4.dp))

        SewerageCard()
    }
}