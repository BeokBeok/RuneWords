package com.beok.runewords.detail.presenter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.beok.runewords.common.BundleKeyConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()
        showContent()
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
                isLoading = viewModel.isLoading,
                info = viewModel.detailInfo
            )
        }
    }
}
