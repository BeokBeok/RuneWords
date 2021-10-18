package com.beok.runewords.combination

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.beok.runewords.common.BundleKeyConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CombinationActivity : AppCompatActivity() {

    private val viewModel by viewModels<CombinationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (intent.extras?.get(BundleKeyConstants.RUNE_NAME) as? String)
            ?.let(viewModel::fetchRuneWords)
    }
}
