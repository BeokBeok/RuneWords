package com.beok.runewords.detail.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.beok.runewords.common.BundleKeyConstants
import com.google.android.play.core.review.ReviewManagerFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()
        showContent()
        requestInAppReview()
    }

    private fun showContent() {
        (intent.extras?.get(BundleKeyConstants.RUNE_WORDS_NAME) as? String)
            ?.let(viewModel::fetchDetail)
    }

    private fun setupUI() {
        setContent {
            ActivityDetailView.Layout(
                runeWordsName = intent.extras?.get(BundleKeyConstants.RUNE_WORDS_NAME) as? String
                    ?: return@setContent,
                context = this,
                state = viewModel.state
            )
        }
    }

    private fun requestInAppReview() {
        val reviewManager = ReviewManagerFactory.create(this)
        reviewManager
            .requestReviewFlow()
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                reviewManager
                    .launchReviewFlow(this, it.result)
                    .addOnCompleteListener {  }
            }
    }
}
