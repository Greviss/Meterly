package com.example.meterly.ui.components.analyticsScreen

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
import com.example.meterly.ui.components.analyticsScreen.lightScreen2.BottomSectionLightScreen2
import com.example.meterly.ui.components.analyticsScreen.lightScreen2.MiddleSectionLightScreen2
import com.example.meterly.ui.components.analyticsScreen.lightScreen2.TopSectionLightScreen2
import com.example.meterly.ui.theme.secondaryGradient
import com.patrykandpatrick.vico.compose.cartesian.axis.Axis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer

@Composable
@Preview
fun LightScreen2(onLeftArrowLight2: () -> Unit = {},
                 onRightArrowLight2: () -> Unit = {},
                 startAxisLight: Axis<Axis.Position.Vertical.Start>? = null,
                 bottomAxisLight: Axis<Axis.Position.Horizontal.Bottom>? = null,
                 modelProducerLight: CartesianChartModelProducer = CartesianChartModelProducer()
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(secondaryGradient())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TopSectionLightScreen2(onLeftArrowLight2 = onLeftArrowLight2,
                onRightArrowLight2 = onRightArrowLight2)

            Spacer(modifier = Modifier.height(16.dp))

            MiddleSectionLightScreen2(
                startAxisLight = { startAxisLight },
                bottomAxisLight = { bottomAxisLight },
                modelProducerLight = modelProducerLight
            )

            Spacer(modifier = Modifier.height(24.dp))

            BottomSectionLightScreen2()
        }
    }
}