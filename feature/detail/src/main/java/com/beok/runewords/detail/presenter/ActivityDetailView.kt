package com.beok.runewords.detail.presenter

import android.content.Context
import android.text.Html
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.beok.runewords.common.ext.resourceIDByName
import com.beok.runewords.common.view.ContentLoading
import com.beok.runewords.detail.R
import com.beok.runewords.detail.presenter.vo.RuneWordsVO
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

internal object ActivityDetailView {

    @Composable
    fun Layout(
        runeWordsName: String,
        context: Context,
        isLoading: Boolean,
        info: RuneWordsVO
    ) {
        MaterialTheme {
            DetailScaffold(
                runeWordsName = runeWordsName,
                context = context,
                isLoading = isLoading,
                info = info,
            )
        }
    }

    @Composable
    private fun DetailScaffold(
        runeWordsName: String,
        context: Context,
        isLoading: Boolean,
        info: RuneWordsVO
    ) {
        Scaffold(
            topBar = { DetailTopBar(context, runeWordsName) },
            content = {
                ContentLoading(isLoading = isLoading)
                if (info.isEmpty()) return@Scaffold
                DetailContent(context, info)
            }
        )
    }

    @Composable
    private fun DetailContent(context: Context, info: RuneWordsVO) {
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
                RuneWordsType(info = info, context = context)
                RuneWordsCombination(info = info)
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
                    AdView(context).apply {
                        adSize = AdSize.BANNER
                        adUnitId = context.getString(R.string.admob_banner_app_key)
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }
    }

    @Composable
    private fun RuneWordsOption(info: RuneWordsVO) {
        Headline(resourceID = R.string.title_options)
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            factory = { context ->
                TextView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                    text = Html.fromHtml(
                        context.getString(
                            context.resourceIDByName(name = info.option) ?: return@apply
                        ),
                        Html.FROM_HTML_MODE_COMPACT
                    )
                }
            }
        )
    }

    @Composable
    private fun RuneWordsCombination(info: RuneWordsVO) {
        Headline(resourceID = R.string.title_rune_words)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Center
        ) {
            info.runeCombination.forEach {
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

    @Composable
    private fun RuneWordsType(info: RuneWordsVO, context: Context) {
        Headline(resourceID = R.string.title_type)
        Text(
            text = info.type
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
    }

    @Composable
    private fun DetailTopBar(context: Context, runeWordsName: String) {
        TopAppBar(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(
                    id = context.resourceIDByName(name = runeWordsName) ?: return@TopAppBar
                ),
                modifier = Modifier.padding(start = 12.dp),
                fontSize = 20.sp
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
