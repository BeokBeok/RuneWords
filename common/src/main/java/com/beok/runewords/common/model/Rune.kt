package com.beok.runewords.common.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.beok.runewords.common.R

enum class Rune(
    @StringRes val nameResourceID: Int,
    @DrawableRes val iconResourceID: Int
) {
    EL(
        nameResourceID = R.string.title_rune_el,
        iconResourceID = R.drawable.ic_rune_el
    ),
    ELD(
        nameResourceID = R.string.title_rune_eld,
        iconResourceID = R.drawable.ic_rune_eld
    ),
    TIR(
        nameResourceID = R.string.title_rune_tir,
        iconResourceID = R.drawable.ic_rune_tir
    ),
    NEF(
        nameResourceID = R.string.title_rune_nef,
        iconResourceID = R.drawable.ic_rune_nef
    ),
    ETH(
        nameResourceID = R.string.title_rune_eth,
        iconResourceID = R.drawable.ic_rune_eth
    ),
    ITH(
        nameResourceID = R.string.title_rune_ith,
        iconResourceID = R.drawable.ic_rune_ith
    ),
    TAL(
        nameResourceID = R.string.title_rune_tal,
        iconResourceID = R.drawable.ic_rune_tal
    ),
    RAL(
        nameResourceID = R.string.title_rune_ral,
        iconResourceID = R.drawable.ic_rune_ral
    ),
    ORT(
        nameResourceID = R.string.title_rune_ort,
        iconResourceID = R.drawable.ic_rune_ort
    ),
    THUL(
        nameResourceID = R.string.title_rune_thul,
        iconResourceID = R.drawable.ic_rune_thul
    ),
    AMN(
        nameResourceID = R.string.title_rune_amn,
        iconResourceID = R.drawable.ic_rune_amn
    ),
    SOL(
        nameResourceID = R.string.title_rune_sol,
        iconResourceID = R.drawable.ic_rune_sol
    ),
    SHAEL(
        nameResourceID = R.string.title_rune_shael,
        iconResourceID = R.drawable.ic_rune_shael
    ),
    DOL(
        nameResourceID = R.string.title_rune_dol,
        iconResourceID = R.drawable.ic_rune_dol
    ),
    HEL(
        nameResourceID = R.string.title_rune_hel,
        iconResourceID = R.drawable.ic_rune_hel
    ),
    IO(
        nameResourceID = R.string.title_rune_io,
        iconResourceID = R.drawable.ic_rune_io
    ),
    LUM(
        nameResourceID = R.string.title_rune_lum,
        iconResourceID = R.drawable.ic_rune_lum
    ),
    KO(
        nameResourceID = R.string.title_rune_ko,
        iconResourceID = R.drawable.ic_rune_ko
    ),
    FAL(
        nameResourceID = R.string.title_rune_fal,
        iconResourceID = R.drawable.ic_rune_fal
    ),
    LEM(
        nameResourceID = R.string.title_rune_lem,
        iconResourceID = R.drawable.ic_rune_lem
    ),
    PUL(
        nameResourceID = R.string.title_rune_pul,
        iconResourceID = R.drawable.ic_rune_pul
    ),
    UM(
        nameResourceID = R.string.title_rune_um,
        iconResourceID = R.drawable.ic_rune_um
    ),
    MAL(
        nameResourceID = R.string.title_rune_mal,
        iconResourceID = R.drawable.ic_rune_mal
    ),
    IST(
        nameResourceID = R.string.title_rune_ist,
        iconResourceID = R.drawable.ic_rune_ist
    ),
    GUL(
        nameResourceID = R.string.title_rune_gul,
        iconResourceID = R.drawable.ic_rune_gul
    ),
    VEX(
        nameResourceID = R.string.title_rune_vex,
        iconResourceID = R.drawable.ic_rune_vex
    ),
    OHM(
        nameResourceID = R.string.title_rune_ohm,
        iconResourceID = R.drawable.ic_rune_ohm
    ),
    LO(
        nameResourceID = R.string.title_rune_lo,
        iconResourceID = R.drawable.ic_rune_lo
    ),
    SUR(
        nameResourceID = R.string.title_rune_sur,
        iconResourceID = R.drawable.ic_rune_sur
    ),
    BER(
        nameResourceID = R.string.title_rune_ber,
        iconResourceID = R.drawable.ic_rune_ber
    ),
    JAH(
        nameResourceID = R.string.title_rune_jah,
        iconResourceID = R.drawable.ic_rune_jah
    ),
    CHAM(
        nameResourceID = R.string.title_rune_cham,
        iconResourceID = R.drawable.ic_rune_cham
    ),
    ZOD(
        nameResourceID = R.string.title_rune_zod,
        iconResourceID = R.drawable.ic_rune_zod
    );

    companion object {

        fun all(): List<Rune> = values().toList()

        fun findByName(name: String) = values()
            .firstOrNull {
                it.name == name.uppercase()
            }
    }
}
