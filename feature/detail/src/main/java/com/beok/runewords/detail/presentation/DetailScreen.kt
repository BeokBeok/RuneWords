package com.beok.runewords.detail.presentation

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.beok.runewords.common.ext.EMPTY
import com.beok.runewords.common.view.ContentLoading
import com.beok.runewords.detail.BuildConfig
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
    showReviewWriteForm: () -> Unit,
    onRuneClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        showReviewWriteForm()
    }

    val state by viewModel.detailState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            val runeWords = (state as? DetailState.Content)?.value ?: return@Scaffold
            if (runeWords.isEmpty()) return@Scaffold
            DetailTopBar(runeWordsNameResourceID = runeWords.nameResourceID)
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            DetailBody(state = state, onRuneClick = onRuneClick)
        }
    }
}

@Composable
private fun DetailBody(state: DetailState, onRuneClick: (String) -> Unit) {
    when (state) {
        is DetailState.Content -> {
            if (state.value.isEmpty()) return
            DetailContent(
                info = state.value,
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

@Composable
private fun DetailContent(info: RuneWordsItem, onRuneClick: (String) -> Unit) {
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
        BannerAd(
            modifier = Modifier.constrainAs(admob) {
                top.linkTo(content.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
private fun BannerAd(modifier: Modifier = Modifier) {
    val deviceCurrentWidth = LocalConfiguration.current.screenWidthDp
    val applicationContext = LocalContext.current.applicationContext
    val adView = remember {
        AdView(applicationContext).apply {
            setAdSize(
                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                    applicationContext,
                    deviceCurrentWidth
                )
            )
            adUnitId = applicationContext.getString(
                if (BuildConfig.DEBUG) {
                    com.beok.runewords.common.R.string.test_admob_banner_app_key
                } else {
                    com.beok.runewords.common.R.string.admob_banner_app_key
                }
            )
            @SuppressLint("MissingPermission")
            loadAd(AdRequest.Builder().build())
        }
    }
    AndroidView(
        modifier = modifier,
        factory = { adView },
        onRelease = AdView::destroy
    )
    LifecycleResumeEffect(adView) {
        adView.resume()
        onPauseOrDispose { adView.pause() }
    }
}

@Composable
private fun RuneWordsOption(info: RuneWordsItem) {
    Headline(title = stringResource(id = R.string.title_options))
    if (info.optionResourceID < 1) return
    val html = stringResource(id = info.optionResourceID)
    val option = remember(html) { AnnotatedString.fromHtml(html).withoutDefaultTextColor() }
    Text(
        text = option,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary
    )
}

/**
 * 옵션 HTML은 기본 문구에 검정색(#000000)을 지정한다.
 * 검정색 지정을 지워서 테마의 글자 색상을 따르게 한다. 강조 색상은 유지한다.
 */
private fun AnnotatedString.withoutDefaultTextColor(): AnnotatedString = AnnotatedString(
    text = text,
    spanStyles = spanStyles.map { range ->
        if (range.item.color == Color.Black) {
            range.copy(item = range.item.copy(color = Color.Unspecified))
        } else {
            range
        }
    },
    paragraphStyles = paragraphStyles
)

@Composable
private fun RuneWordsCombination(info: RuneWordsItem, onRuneClick: (String) -> Unit) {
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
                    modifier = Modifier.padding(top = 4.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun RuneWordsType(info: RuneWordsItem) {
    Headline(title = stringResource(id = R.string.title_type))
    Text(
        text = info.typeResourceIDs
            .map { stringResource(id = it) }
            .joinToString(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopBar(@StringRes runeWordsNameResourceID: Int) {
    TopAppBar(
        title = {
            Text(
                text = if (runeWordsNameResourceID > 0) {
                    stringResource(id = runeWordsNameResourceID)
                } else {
                    String.EMPTY
                },
                modifier = Modifier.padding(start = 12.dp),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
private fun Headline(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 4.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
