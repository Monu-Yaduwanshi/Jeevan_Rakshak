package com.example.jeevan_rakshak.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.jeevan_rakshak.ui.theme.*

@Composable
fun RoleCheck(
    type: String,
    selected: String,
    onSelect: (String) -> Unit
) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Checkbox(
            checked = selected == type,
            onCheckedChange = { onSelect(type) },
            colors = CheckboxDefaults.colors(checkedColor = SkyAccent)
        )

        Text(
            text = type.replaceFirstChar { it.uppercase() },
            color = BlueText
        )
    }
}