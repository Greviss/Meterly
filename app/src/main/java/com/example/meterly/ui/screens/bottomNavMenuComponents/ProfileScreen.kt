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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.meterly.model.Address
import com.example.meterly.ui.components.profileScreen.BottomSectionProfileScreen
import com.example.meterly.ui.components.profileScreen.MiddleSectionProfileScreen
import com.example.meterly.ui.components.profileScreen.TopSectionProfileScreen
import com.example.meterly.ui.components.profileScreen.middleSectionComponents.AddAddressDialog
import com.example.meterly.ui.components.profileScreen.middleSectionComponents.EditAddressDialog
import com.example.meterly.ui.navigation.Screen
import com.example.meterly.ui.theme.secondaryGradient
import com.example.meterly.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    onSignOutClick: () -> Unit = {},
    onClickPrivacy: () -> Unit
) {

    val profileViewModel: ProfileViewModel = viewModel()

    var showAddDialog by remember {
        mutableStateOf(false)
    }

    var showEditDialog by remember {
        mutableStateOf(false)
    }

    var selectedAddress by remember {
        mutableStateOf<Address?>(null)
    }

    val user by profileViewModel.user.collectAsState()
    val currentAddress by profileViewModel.currentAddress.collectAsState()
    val addresses by profileViewModel.addresses.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(secondaryGradient())
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            TopSectionProfileScreen(
                user = user
            )

            Spacer(modifier = Modifier.height(24.dp))

            MiddleSectionProfileScreen(
                user = user,
                currentAddress = currentAddress,
                addresses = addresses,

                onAddAddress = {
                    showAddDialog = true
                },

                onAddressClick = {
                    profileViewModel.setCurrentAddress(it.id)
                },

                onDeleteAddress = {
                    profileViewModel.deleteAddress(it.id)
                },

                onEditAddress = { address ->
                    selectedAddress = address
                    showEditDialog = true
                },
                onClickPrivacy = onClickPrivacy,
                onClickProfileControl = { navController.navigate(Screen.ProfileControl.route) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomSectionProfileScreen(
                onSingOutClick = onSignOutClick
            )

            Spacer(modifier = Modifier.height(35.dp))
        }

        if (showAddDialog) {
            AddAddressDialog(
                onDismiss = { showAddDialog = false},
                onSave = { addresses ->
                    profileViewModel.addAddress(addresses)
                    showAddDialog = false
                }
            )
        }

        if (showEditDialog && selectedAddress != null) {
            EditAddressDialog(
                address = selectedAddress!!,
                onDismiss = {
                    showEditDialog = false
                },
                onSave = { newAddress ->
                    profileViewModel.updateAddress(
                        selectedAddress!!.id,
                        newAddress
                    )

                    showEditDialog = false
                }
            )
        }
    }
}