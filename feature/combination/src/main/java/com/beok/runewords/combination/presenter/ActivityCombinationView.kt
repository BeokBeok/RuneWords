package com.beok.runewords.combination.presenter

import android.content.Context
import android.content.Intent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import com.beok.runewords.combination.R
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.ext.resourceIDByName
import com.beok.runewords.common.ext.startActivity
import com.beok.runewords.common.model.Rune
import com.beok.runewords.common.view.ContentLoading

internal object ActivityCombinationView {

    private const val CLASSNAME_DETAIL = "com.beok.runewords.detail.presenter.DetailActivity"

    @Composable
    fun Layout(
        rune: Rune,
        context: Context,
        isLoading: Boolean,
        runeWords: List<RuneWords>,
        runeWordClickTracking: (String) -> Unit
    ) {
        MaterialTheme {
            CombinationScaffold(
                rune = rune,
                context = context,
                isLoading = isLoading,
                runeWords = runeWords,
                runeWordClickTracking = runeWordClickTracking
            )
        }
    }

    @Composable
    private fun CombinationScaffold(
        rune: Rune,
        context: Context,
        isLoading: Boolean,
        runeWords: List<RuneWords>,
        runeWordClickTracking: (String) -> Unit
    ) {
        Scaffold(
            topBar = {
                CombinationTopBar(rune = rune)
            },
            content = {
                ContentLoading(isLoading = isLoading)
                CombinationContent(
                    context = context,
                    runeWords = runeWords,
                    runeWordClickTracking = runeWordClickTracking
                )
            }
        )
    }

    @Composable
    private fun CombinationContent(
        context: Context,
        runeWords: List<RuneWords>,
        runeWordClickTracking: (String) -> Unit
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(runeWords) { item ->
                if (context.resourceIDByName(item.name) > 0) {
                    val isLadder = stringArrayResource(id = R.array.ladder_rune_words)
                        .contains(item.name)
                    var runeWordsName =
                        stringResource(id = context.resourceIDByName(item.name))
                    if (isLadder) {
                        runeWordsName = stringResource(id = R.string.ladder_only, runeWordsName)
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                context.startActivity(
                                    className = CLASSNAME_DETAIL,
                                    bundle = bundleOf(
                                        BundleKeyConstants.RUNE_WORDS_NAME to item.name
                                    ),
                                    intentFlags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                )
                                runeWordClickTracking(item.name)
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
                    modifier = Modifier.padding(start = 12.dp),
                    fontSize = 20.sp
                )
            }
        )
    }
}
