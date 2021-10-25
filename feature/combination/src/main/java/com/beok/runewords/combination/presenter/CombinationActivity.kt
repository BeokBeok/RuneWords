package com.beok.runewords.combination.presenter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.model.Rune
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class CombinationActivity : AppCompatActivity() {

    private val viewModel by viewModels<CombinationViewModel>()

    @Inject
    lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()
        showContent()
    }

    private fun setupUI() {
        setContent {
            ActivityCombinationView.Layout(
                rune = intent.extras?.get(BundleKeyConstants.RUNE_NAME) as? Rune
                    ?: return@setContent,
                context = this,
                viewModel = viewModel,
                analytics = analytics
            )
        }
    }

    private fun showContent() {
        (intent.extras?.get(BundleKeyConstants.RUNE_NAME) as? Rune)
            ?.let(viewModel::fetchRuneWords)
    }
}
