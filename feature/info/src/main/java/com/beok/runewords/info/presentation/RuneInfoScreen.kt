package com.beok.runewords.info.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beok.runewords.common.model.Rune
import com.beok.runewords.info.R

@Composable
internal fun RuneInfoScreen(rune: Rune?) {
    if (rune == null) return

    Column(modifier = Modifier.fillMaxSize()) {
        RuneInfoTopBar(rune)
        RuneInfoContent(
            modifier = Modifier.fillMaxSize(),
            rune = rune
        )
    }
}

@Composable
private fun RuneInfoContent(
    modifier: Modifier,
    rune: Rune
) {
    Column(
        modifier = modifier.padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RuneInfoItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            imageResourceID = com.beok.runewords.common.R.drawable.ic_weapon,
            messageResourceID = rune.weaponResourceID
        )
        RuneInfoItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            imageResourceID = com.beok.runewords.common.R.drawable.ic_helm,
            messageResourceID = rune.helmResourceID
        )
        RuneInfoItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            imageResourceID = com.beok.runewords.common.R.drawable.ic_armor,
            messageResourceID = rune.armorResourceID
        )
        RuneInfoItem(
            modifier = Modifier.fillMaxWidth(),
            imageResourceID = com.beok.runewords.common.R.drawable.ic_shield,
            messageResourceID = rune.shieldResourceID
        )
    }
}

@Composable
private fun RuneInfoItem(
    modifier: Modifier,
    @DrawableRes imageResourceID: Int,
    @StringRes messageResourceID: Int
) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResourceID),
            contentDescription = null,
            modifier = Modifier.size(96.dp)
        )
        Spacer(modifier = Modifier.width(36.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = messageResourceID),
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun RuneInfoTopBar(rune: Rune) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = rune.iconResourceID),
                contentDescription = null,
                modifier = Modifier.size(width = 40.dp, height = 40.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Rune.previous(rune)
                    ?.let {
                        RuneCompound(rune = it)
                    }
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun RuneCompound(rune: Rune) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null,
        tint = MaterialTheme.colors.primary
    )
    repeat(rune.compoundCount) {
        Image(
            painter = painterResource(id = rune.iconResourceID),
            contentDescription = null,
            modifier = Modifier.size(width = 40.dp, height = 40.dp)
        )
    }
    if (rune.gemResourceID == null) return
    Icon(
        imageVector = Icons.Default.Add,
        contentDescription = null,
        tint = MaterialTheme.colors.primary
    )
    Image(
        painter = painterResource(id = rune.gemResourceID!!),
        contentDescription = null,
        modifier = Modifier.size(width = 40.dp, height = 40.dp)
    )
}
