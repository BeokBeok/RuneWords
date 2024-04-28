package com.beok.runewords.coding.convention

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

internal class ViewModelConventionTest {
    @Test
    fun `ViewModel은 Test 코드가 있어야 한다`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("ViewModel")
            .assertTrue(function = KoClassDeclaration::hasTestClasses)
    }

    @Test
    fun `ViewModel은 presentation 패키지에 있어야 한다`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("ViewModel")
            .assertTrue {
                it.resideInPackage("..presentation..")
            }
    }
}
