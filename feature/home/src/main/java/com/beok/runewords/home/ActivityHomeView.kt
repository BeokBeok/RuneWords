package com.beok.runewords.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.constants.TrackingConstants
import com.beok.runewords.common.ext.startActivity
import com.beok.runewords.common.model.Rune
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.firebase.analytics.FirebaseAnalytics

internal object ActivityHomeView {

    private const val CLASSNAME_COMBINATION =
        "com.beok.runewords.combination.presenter.CombinationActivity"

    @Composable
    fun Layout(context: Context, runeClickTracking: (String) -> Unit) {
        MaterialTheme {
            HomeScaffold(context = context, runeClickTracking = runeClickTracking)
        }
    }

    @Composable
    private fun HomeScaffold(context: Context, runeClickTracking: (String) -> Unit) {
        Scaffold(
            topBar = { HomeTopBar() },
            content = { HomeContent(context = context, runeClickTracking = runeClickTracking) }
        )
    }

    @Composable
    private fun HomeTopBar() {
        TopAppBar(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.title_rune_list),
                modifier = Modifier.padding(start = 12.dp),
                fontSize = 20.sp
            )
        }
    }

    @Composable
    private fun HomeContent(context: Context, runeClickTracking: (String) -> Unit) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (content, admob) = createRefs()
            LazyVerticalGrid(
                modifier = Modifier.constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(admob.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
                cells = GridCells.Adaptive(minSize = 128.dp)
            ) {
                items(Rune.all()) { item ->
                    RuneItem(
                        item = item,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                            .clickable {
                                context.startActivity(
                                    className = CLASSNAME_COMBINATION,
                                    bundle = bundleOf(
                                        BundleKeyConstants.RUNE_NAME to Rune.findByName(item.name)
                                    )
                                )
                                runeClickTracking(item.name)
                            }
                    )
                }
            }
            AndroidView(
                modifier = Modifier
                    .constrainAs(admob) {
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
    private fun RuneItem(item: Rune, modifier: Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = item.iconResourceID),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = stringResource(id = item.nameResourceID))
        }
    }
}
