package com.example.meterly.ui.screens

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.meterly.ui.components.profileScreen.middleSectionComponents.DeleteAccountDialog
import com.example.meterly.ui.components.profileScreen.middleSectionComponents.EditNameDialog
import com.example.meterly.ui.components.profileScreen.middleSectionComponents.ProfileControl
import com.example.meterly.ui.navigation.Screen
import com.example.meterly.viewModel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileControlScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val viewModel: ProfileViewModel = viewModel()
    val user by viewModel.user.collectAsState()

    var showEditDialog by remember {
        mutableStateOf(false)
    }

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    ProfileControl(
        onEditName = {
            showEditDialog = true
        },

        onSignOut = {
            FirebaseAuth.getInstance().signOut()
            navController.navigate(Screen.Enter.route) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }

                launchSingleTop = true
            }
        },

        onShare = {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                Intent.EXTRA_TEXT
                """
Спробуй Meterly — додаток для обліку комунальних платежів.

Незабаром у Google Play 🚀
""".trimIndent()
            }

            context.startActivity(
                Intent.createChooser(intent, "Поділитися")
            )
        },

        onDeleteAccount = {
            showDeleteDialog = true
        },

        onBackClick = {
            navController.popBackStack()
        }
    )

    if (showEditDialog) {
        EditNameDialog(
            currentName = user?.fullName ?: "",
            onDismiss = {
                showEditDialog = false
            },
            onSave = {newName ->
                if (newName.isNotBlank()) {
                    viewModel.updateFullName(newName.trim())
                }
                showEditDialog = false
            }
        )
    }

    if (showDeleteDialog) {
        DeleteAccountDialog(
            onDismiss = {
                showDeleteDialog = false
            },

            onDelete = {
                viewModel.deleteAccount {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate(Screen.Enter.route) {
                        popUpTo(Screen.Enter.route) {
                            inclusive = true
                        }
                    }
                }
                showDeleteDialog = false
            }
        )
    }
}