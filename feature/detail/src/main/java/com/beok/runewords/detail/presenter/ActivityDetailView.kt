package com.beok.runewords.detail.presenter

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beok.runewords.common.ext.resourceIDByName
import com.beok.runewords.detail.R
import com.beok.runewords.detail.presenter.vo.RuneWordsVO

internal object ActivityDetailView {

    @Composable
    fun Layout(runeWordsName: String, context: Context, viewModel: DetailViewModel) {
        MaterialTheme {
            DetailScaffold(runeWordsName = runeWordsName, context = context, viewModel = viewModel)
        }
    }

    @Composable
    private fun DetailScaffold(
        runeWordsName: String,
        context: Context,
        viewModel: DetailViewModel
    ) {
        Scaffold(
            topBar = { DetailTopBar(context, runeWordsName) },
            content = {
                val info = viewModel.detailInfo.observeAsState(initial = RuneWordsVO())
                if (info.value.isEmpty()) return@Scaffold
                DetailContent(context, info)
            }
        )
    }

    @Composable
    private fun DetailContent(
        context: Context,
        info: State<RuneWordsVO>
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Headline(resourceID = R.string.title_type)
            Text(
                text = info.value.type
                    .map { typeName ->
                        context.resourceIDByName(name = typeName)?.let { id ->
                            stringResource(id = id)
                        }
                    }
                    .joinToString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Headline(resourceID = R.string.title_rune_words)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                info.value.runeCombination.forEach {
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = it.iconResourceID),
                            contentDescription = null,
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp)
                        )
                        Text(
                            text = stringResource(id = it.nameResourceID),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun DetailTopBar(context: Context, runeWordsName: String) {
        TopAppBar(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(
                    id = context.resourceIDByName(name = runeWordsName) ?: return@TopAppBar
                ),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }

    @Composable
    private fun Headline(resourceID: Int) {
        Text(
            text = stringResource(id = resourceID),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}
