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
import com.example.meterly.ui.components.analyticsScreen.sewerageScreen2.BottomSectionSewerageScreen2
import com.example.meterly.ui.components.analyticsScreen.sewerageScreen2.MiddleSectionSewerageScreen2
import com.example.meterly.ui.components.analyticsScreen.sewerageScreen2.TopSectionSewerageScreen2
import com.example.meterly.ui.theme.secondaryGradient
import com.patrykandpatrick.vico.compose.cartesian.axis.Axis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer

@Composable
@Preview
fun SewerageScreen2(onLeftArrowSewerage2: () -> Unit = {},
                 onRightArrowSewerage2: () -> Unit = {},
                 startAxisSewerage: Axis<Axis.Position.Vertical.Start>? = null,
                 bottomAxisSewerage: Axis<Axis.Position.Horizontal.Bottom>? = null,
                 modelProducerSewerage: CartesianChartModelProducer = CartesianChartModelProducer()
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
            TopSectionSewerageScreen2(onLeftArrowSewerage2 = onLeftArrowSewerage2,
                onRightArrowSewerage2 = onRightArrowSewerage2)

            Spacer(modifier = Modifier.height(16.dp))

            MiddleSectionSewerageScreen2(
                startAxisSewerage = { startAxisSewerage },
                bottomAxisSewerage = { bottomAxisSewerage },
                modelProducerSewerage = modelProducerSewerage
            )

            Spacer(modifier = Modifier.height(24.dp))

            BottomSectionSewerageScreen2()
        }
    }
}