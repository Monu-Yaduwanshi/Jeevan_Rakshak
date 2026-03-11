package com.example.jeevan_rakshak.ui.screens.bottomNavBar

//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.clickable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.composed
//
//fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
//    clickable(
//        interactionSource = remember { MutableInteractionSource() },
//        indication = null
//    ) {
//        onClick()
//    }
//}

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

fun Modifier.safeClickable(onClick: () -> Unit): Modifier = this.then(
    Modifier.clickable(
        interactionSource = MutableInteractionSource(),
        indication = null
    ) {
        onClick()
    }
)