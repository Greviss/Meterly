package com.example.meterly.ui.components.profileScreen.middleSectionComponents

import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meterly.model.Address

@Composable
fun MyObjects(
    addresses: List<Address>,
    currentAddress: Address?,
    onAddClick: () -> Unit = {},
    onAddressClick: (Address) -> Unit = {},
    onDeleteAddress: (Address) -> Unit = {},
    onEditAddress: (Address) -> Unit = {}
) {
    Column {
        AddObjects(
            onAddClick = onAddClick
        )
        Spacer(modifier = Modifier.height(12.dp))

        if (addresses.isEmpty()) {
            Text(
                text = "У вас ще немає доданих адрес.",
                color = Color.Gray,
                fontSize = 14.sp
            )
        } else {
            addresses.forEach { address ->
                AddressCard(
                    address = address,
                    isCurrent = address.id == currentAddress?.id,
                    onClick = {
                        onAddressClick(address)
                    },
                    onDelete = {
                        onDeleteAddress(address)
                    },
                    onEdit = {
                        onEditAddress(address)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun AddressCard(
    address: Address,
    isCurrent: Boolean,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor =
                if (isCurrent)
                    Color(0xFFE8F5E9)
                else
                    Color(0xFFF7F7F7)
        )
    ) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    tint =
                        if (isCurrent)
                            Color(0xFF2E7D32)
                        else
                            Color.Gray
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = address.address,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    if (isCurrent) {
                        Text(
                            text = "Поточна адреса",
                            color = Color(0xFF2E7D32),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                modifier = Modifier.clickable {
                    expanded = true
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                if (!isCurrent) {
                    DropdownMenuItem(
                        text = {
                            Text("Зробити поточною")
                        },
                        onClick = {
                            expanded = false
                            onClick()
                        }
                    )
                }

                DropdownMenuItem(
                    text = { Text("Редагувати") },
                    onClick = {
                        expanded = false
                        onEdit()
                    }
                )

                DropdownMenuItem(
                    text = { Text("Видалити") },
                    onClick = {
                        expanded = false
                        onDelete()
                    }
                )
            }
        }
    }
}

@Composable
private fun AddObjects(
    onAddClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Список адрес",
            fontSize = 16.sp,
            color = Color.Gray
        )

        FloatingActionButton(
            modifier = Modifier.size(44.dp),
            onClick = onAddClick,
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }
}