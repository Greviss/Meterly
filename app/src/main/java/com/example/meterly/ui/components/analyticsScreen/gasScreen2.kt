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
import com.example.meterly.ui.components.analyticsScreen.gasScreen2.BottomSectionGasScreen2
import com.example.meterly.ui.components.analyticsScreen.gasScreen2.MiddleSectionGasScreen2
import com.example.meterly.ui.components.analyticsScreen.gasScreen2.TopSectionGasScreen2
import com.example.meterly.ui.theme.secondaryGradient
import com.patrykandpatrick.vico.compose.cartesian.axis.Axis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer

@Composable
@Preview
fun GasScreen2(onLeftArrowGas2: () -> Unit = {},
               onRightArrowGas2: () -> Unit = {},
               startAxisGas: Axis<Axis.Position.Vertical.Start>? = null,
               bottomAxisGas: Axis<Axis.Position.Horizontal.Bottom>? = null,
               modelProducerGas: CartesianChartModelProducer = CartesianChartModelProducer()
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
            TopSectionGasScreen2(
                onLeftArrowGas2 = onLeftArrowGas2,
                onRightArrowGas2 = onRightArrowGas2
            )

            Spacer(modifier = Modifier.height(16.dp))

            MiddleSectionGasScreen2(
                startAxisGas = { startAxisGas },
                bottomAxisGas = { bottomAxisGas },
                modelProducerGas = modelProducerGas,
            )

            Spacer(modifier = Modifier.height(24.dp))

            BottomSectionGasScreen2()
        }
    }
}