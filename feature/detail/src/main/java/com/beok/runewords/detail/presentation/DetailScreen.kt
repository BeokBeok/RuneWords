package com.beok.runewords.detail.presentation

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import com.beok.runewords.common.ext.EMPTY
import com.beok.runewords.common.ext.resourceIDByName
import com.beok.runewords.common.util.HtmlConverter
import com.beok.runewords.common.view.ContentLoading
import com.beok.runewords.detail.R
import com.beok.runewords.detail.presentation.model.DetailState
import com.beok.runewords.detail.presentation.model.RuneWordsItem
import com.beok.runewords.tracking.LocalTracker
import com.beok.runewords.tracking.TrackingConstants
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
internal fun DetailRoute(
    viewModel: DetailViewModel = hiltViewModel(),
    showReviewWriteForm: () -> Unit,
    onRuneClick: (String) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        showReviewWriteForm()
    }

    val state by viewModel.detailState.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is DetailState.Content -> {
                val runeWords = (state as DetailState.Content).value

                if (runeWords.isEmpty()) return@Column
                DetailTopBar(runeWordsName = runeWords.name)
                DetailContent(
                    info = runeWords,
                    onRuneClick = onRuneClick
                )
            }
            DetailState.Loading -> {
                ContentLoading(isLoading = true)
            }
            DetailState.Failed,
            DetailState.None -> Unit
        }
    }
}

@Composable
private fun DetailContent(
    info: RuneWordsItem,
    onRuneClick: (String) -> Unit
) {
    val deviceCurrentWidth = LocalConfiguration.current.screenWidthDp
    val applicationContext = LocalContext.current.applicationContext
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (content, admob) = createRefs()
        Column(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(admob.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .verticalScroll(rememberScrollState())
        ) {
            RuneWordsType(info = info)
            RuneWordsCombination(info = info, onRuneClick = onRuneClick)
            RuneWordsOption(info = info)
        }
        AndroidView(
            modifier = Modifier.constrainAs(admob) {
                top.linkTo(content.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            factory = { context ->
                AdView(applicationContext).apply {
                    setAdSize(
                        AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                            applicationContext,
                            deviceCurrentWidth
                        )
                    )
                    adUnitId = context.getString(R.string.admob_banner_app_key)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}

@Composable
private fun RuneWordsOption(info: RuneWordsItem) {
    val isDarkTheme = isSystemInDarkTheme()
    Headline(title = stringResource(id = R.string.title_options))
    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        factory = { context ->
            TextView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                text = if (context.resourceIDByName(name = info.option) > 0) {
                    HtmlConverter.fromTheme(
                        source = context.getString(context.resourceIDByName(name = info.option)),
                        isDarkTheme = isDarkTheme
                    )
                } else {
                    String.EMPTY
                }
            }
        }
    )
}

@Composable
private fun RuneWordsCombination(
    info: RuneWordsItem,
    onRuneClick: (String) -> Unit
) {
    val tracking = LocalTracker.current
    Headline(
        title = stringResource(
            id = R.string.title_rune_words,
            formatArgs = arrayOf(info.levelLimit)
        )
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center
    ) {
        info.runeCombination.forEach {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(shape = RoundedCornerShape(size = 20.dp))
                    .clickable {
                        onRuneClick(it.name)
                        tracking.logEvent(
                            name = TrackingConstants.Rune.CLICK,
                            bundle = bundleOf(TrackingConstants.Params.RUNE_NAME to it.name)
                        )
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
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
                    modifier = Modifier.padding(top = 4.dp),
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Composable
private fun RuneWordsType(info: RuneWordsItem) {
    val context: Context = LocalContext.current
    Headline(title = stringResource(id = R.string.title_type))
    Text(
        text = info.type
            .filter { context.resourceIDByName(name = it) > 0 }
            .map { stringResource(id = context.resourceIDByName(it)) }
            .joinToString(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
    )
}

@Composable
private fun DetailTopBar(runeWordsName: String) {
    val context: Context = LocalContext.current
    TopAppBar(
        title = {
            Text(
                text = if (context.resourceIDByName(name = runeWordsName) > 0) {
                    stringResource(id = context.resourceIDByName(name = runeWordsName))
                } else {
                    String.EMPTY
                },
                modifier = Modifier.padding(start = 12.dp),
                fontSize = 20.sp,
                color = MaterialTheme.colors.primary
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun Headline(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(vertical = 4.dp),
        textAlign = TextAlign.Center,
        color = Color.Black
    )
}
