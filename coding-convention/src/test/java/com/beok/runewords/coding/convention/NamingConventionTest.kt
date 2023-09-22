package com.beok.runewords.coding.convention

import androidx.lifecycle.ViewModel
import androidx.room.Entity
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAllAnnotationsOf
import com.lemonappdev.konsist.api.ext.list.withAllParentsOf
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assert
import org.junit.jupiter.api.Test

internal class NamingConventionTest {
    @Test
    fun `Entity 어노테이션이 붙은 Table은_data의 model 패키지에 있어야 한다`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAllAnnotationsOf(Entity::class)
            .withNameEndingWith(suffix = "Table")
            .assert { it.resideInPackage(name = "..data.model") }
    }

    @Test
    fun `Response는_data의 model 패키지에 있어야 한다`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith(suffix = "Response")
            .assert { it.resideInPackage(name = "..data.model") }
    }

    @Test
    fun `Database는 뒤에 Database를 붙이며_data의 local 패키지에 있어야 한다`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith(suffix = "Database")
            .assert { it.resideInPackage(name = "..data.local") }
    }

    @Test
    fun `DAO는 뒤에 DAO를 붙이며_data의 local 패키지에 있어야 한다`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withNameEndingWith(suffix = "DAO")
            .assert { it.resideInPackage(name = "..data.local") }
    }

    @Test
    fun `RemoteDataSource는 뒤에 RemoteDataSource를 붙이며_data의 remote 패키지에 있어야 한다`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withNameEndingWith(suffix = "RemoteDataSource")
            .assert { it.resideInPackage(name = "..data.remote") }
    }

    @Test
    fun `RemoteDataSource의 구현부는 뒤에 RemoteDataSourceImpl를 붙이며_data의 remote 패키지에 있어야 한다`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith(suffix = "RemoteDataSourceImpl")
            .assert { it.resideInPackage(name = "..data.remote") }
    }

    @Test
    fun `Repository 인터페이스는_domain 패키지에 있어야 한다`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withNameEndingWith(suffix = "Repository")
            .assert { it.resideInPackage(name = "..domain") }
    }

    @Test
    fun `Repository의 구현체는_data 패키지에 있어야 한다`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith(suffix = "RepositoryImpl")
            .assert { it.resideInPackage(name = "..data") }
    }

    @Test
    fun `ViewModel은_presentation 패키지에 있어야 한다`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAllParentsOf(ViewModel::class)
            .assert {
                it.name.endsWith("ViewModel")
                it.resideInPackage(name = "..presentation")
            }
    }
}
