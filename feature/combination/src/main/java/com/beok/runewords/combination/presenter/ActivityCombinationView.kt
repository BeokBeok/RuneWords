package com.beok.runewords.combination.presenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beok.runewords.common.model.Rune

object ActivityCombinationView {

    @Composable
    fun Layout(rune: Rune) {
        MaterialTheme {
            Content(rune = rune)
        }
    }

    @Composable
    private fun Content(rune: Rune) {
        Scaffold(
            topBar = {
                TopAppBar(rune = rune)
            },
            content = {

            }
        )
    }

    @Composable
    private fun TopAppBar(rune: Rune) {
        TopAppBar(
            title = {
                Image(
                    painter = painterResource(id = rune.iconResourceID),
                    contentDescription = null,
                    modifier = Modifier.size(width = 40.dp, height = 40.dp)
                )
                Text(
                    text = stringResource(id = rune.nameResourceID),
                    modifier = Modifier.padding(start = 12.dp)
                )
            },
        )
    }
}
