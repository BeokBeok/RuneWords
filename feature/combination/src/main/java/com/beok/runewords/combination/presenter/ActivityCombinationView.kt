package com.beok.runewords.combination.presenter

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.ext.resourceIDByName
import com.beok.runewords.common.ext.startActivity
import com.beok.runewords.common.model.Rune

internal object ActivityCombinationView {

    private const val CLASSNAME_DETAIL = "com.beok.runewords.detail.presenter.DetailActivity"

    @Composable
    fun Layout(rune: Rune, context: Context, viewModel: CombinationViewModel) {
        MaterialTheme {
            CombinationScaffold(rune = rune, context = context, viewModel = viewModel)
        }
    }

    @Composable
    private fun CombinationScaffold(rune: Rune, context: Context, viewModel: CombinationViewModel) {
        Scaffold(
            topBar = {
                CombinationTopBar(rune = rune)
            },
            content = {
                CombinationContent(context = context, viewModel = viewModel)
            }
        )
    }

    @Composable
    private fun CombinationContent(
        context: Context,
        viewModel: CombinationViewModel
    ) {
        val runeWords = viewModel.runeWordsGroup.observeAsState(initial = listOf())

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(runeWords.value) { item ->
                val runeWordsName =
                    stringResource(id = context.resourceIDByName(item.name) ?: return@items)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            context.startActivity(
                                className = CLASSNAME_DETAIL,
                                bundle = bundleOf(
                                    BundleKeyConstants.RUNE_WORDS_NAME to item.name
                                )
                            )
                        }
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = runeWordsName)
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }
            }
        }
    }

    @Composable
    private fun CombinationTopBar(rune: Rune) {
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
            }
        )
    }
}
