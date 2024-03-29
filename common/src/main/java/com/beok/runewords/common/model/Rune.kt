package com.beok.runewords.common.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.beok.runewords.common.R

enum class Rune(
    @StringRes val nameResourceID: Int,
    @DrawableRes val iconResourceID: Int,
    @StringRes val weaponResourceID: Int,
    @StringRes val helmResourceID: Int,
    @StringRes val armorResourceID: Int,
    @StringRes val shieldResourceID: Int,
    @DrawableRes val gemResourceID: Int? = null,
    val compoundCount: Int
) {
    EL(
        nameResourceID = R.string.title_rune_el,
        iconResourceID = R.drawable.ic_rune_el,
        weaponResourceID = R.string.option_el_one,
        helmResourceID = R.string.option_el_two,
        armorResourceID = R.string.option_el_two,
        shieldResourceID = R.string.option_el_two,
        compoundCount = 3
    ),
    ELD(
        nameResourceID = R.string.title_rune_eld,
        iconResourceID = R.drawable.ic_rune_eld,
        weaponResourceID = R.string.option_eld_one,
        helmResourceID = R.string.option_eld_two,
        armorResourceID = R.string.option_eld_two,
        shieldResourceID = R.string.option_eld_three,
        compoundCount = 3
    ),
    TIR(
        nameResourceID = R.string.title_rune_tir,
        iconResourceID = R.drawable.ic_rune_tir,
        weaponResourceID = R.string.option_tir_one,
        helmResourceID = R.string.option_tir_one,
        armorResourceID = R.string.option_tir_one,
        shieldResourceID = R.string.option_tir_one,
        compoundCount = 3
    ),
    NEF(
        nameResourceID = R.string.title_rune_nef,
        iconResourceID = R.drawable.ic_rune_nef,
        weaponResourceID = R.string.option_nef_one,
        helmResourceID = R.string.option_nef_two,
        armorResourceID = R.string.option_nef_two,
        shieldResourceID = R.string.option_nef_two,
        compoundCount = 3
    ),
    ETH(
        nameResourceID = R.string.title_rune_eth,
        iconResourceID = R.drawable.ic_rune_eth,
        weaponResourceID = R.string.option_eth_one,
        helmResourceID = R.string.option_eth_two,
        armorResourceID = R.string.option_eth_two,
        shieldResourceID = R.string.option_eth_two,
        compoundCount = 3
    ),
    ITH(
        nameResourceID = R.string.title_rune_ith,
        iconResourceID = R.drawable.ic_rune_ith,
        weaponResourceID = R.string.option_ith_one,
        helmResourceID = R.string.option_ith_two,
        armorResourceID = R.string.option_ith_two,
        shieldResourceID = R.string.option_ith_two,
        compoundCount = 3
    ),
    TAL(
        nameResourceID = R.string.title_rune_tal,
        iconResourceID = R.drawable.ic_rune_tal,
        weaponResourceID = R.string.option_tal_one,
        helmResourceID = R.string.option_tal_two,
        armorResourceID = R.string.option_tal_two,
        shieldResourceID = R.string.option_tal_three,
        compoundCount = 3
    ),
    RAL(
        nameResourceID = R.string.title_rune_ral,
        iconResourceID = R.drawable.ic_rune_ral,
        weaponResourceID = R.string.option_ral_one,
        helmResourceID = R.string.option_ral_two,
        armorResourceID = R.string.option_ral_two,
        shieldResourceID = R.string.option_ral_three,
        compoundCount = 3
    ),
    ORT(
        nameResourceID = R.string.title_rune_ort,
        iconResourceID = R.drawable.ic_rune_ort,
        weaponResourceID = R.string.option_ort_one,
        helmResourceID = R.string.option_ort_two,
        armorResourceID = R.string.option_ort_two,
        shieldResourceID = R.string.option_ort_three,
        compoundCount = 3
    ),
    THUL(
        nameResourceID = R.string.title_rune_thul,
        iconResourceID = R.drawable.ic_rune_thul,
        weaponResourceID = R.string.option_thul_one,
        helmResourceID = R.string.option_thul_two,
        armorResourceID = R.string.option_thul_two,
        shieldResourceID = R.string.option_thul_three,
        gemResourceID = R.drawable.ic_chip_topaz,
        compoundCount = 3
    ),
    AMN(
        nameResourceID = R.string.title_rune_amn,
        iconResourceID = R.drawable.ic_rune_amn,
        weaponResourceID = R.string.option_amn_one,
        helmResourceID = R.string.option_amn_two,
        armorResourceID = R.string.option_amn_two,
        shieldResourceID = R.string.option_amn_two,
        gemResourceID = R.drawable.ic_chip_amethyst,
        compoundCount = 3
    ),
    SOL(
        nameResourceID = R.string.title_rune_sol,
        iconResourceID = R.drawable.ic_rune_sol,
        weaponResourceID = R.string.option_sol_one,
        helmResourceID = R.string.option_sol_two,
        armorResourceID = R.string.option_sol_two,
        shieldResourceID = R.string.option_sol_two,
        gemResourceID = R.drawable.ic_chip_sapphire,
        compoundCount = 3
    ),
    SHAEL(
        nameResourceID = R.string.title_rune_shael,
        iconResourceID = R.drawable.ic_rune_shael,
        weaponResourceID = R.string.option_shael_one,
        helmResourceID = R.string.option_shael_two,
        armorResourceID = R.string.option_shael_two,
        shieldResourceID = R.string.option_shael_three,
        gemResourceID = R.drawable.ic_chip_ruby,
        compoundCount = 3
    ),
    DOL(
        nameResourceID = R.string.title_rune_dol,
        iconResourceID = R.drawable.ic_rune_dol,
        weaponResourceID = R.string.option_dol_one,
        helmResourceID = R.string.option_dol_two,
        armorResourceID = R.string.option_dol_two,
        shieldResourceID = R.string.option_dol_two,
        gemResourceID = R.drawable.ic_chip_emerald,
        compoundCount = 3
    ),
    HEL(
        nameResourceID = R.string.title_rune_hel,
        iconResourceID = R.drawable.ic_rune_hel,
        weaponResourceID = R.string.option_hel_one,
        helmResourceID = R.string.option_hel_two,
        armorResourceID = R.string.option_hel_two,
        shieldResourceID = R.string.option_hel_two,
        gemResourceID = R.drawable.ic_chip_diamond,
        compoundCount = 3
    ),
    IO(
        nameResourceID = R.string.title_rune_io,
        iconResourceID = R.drawable.ic_rune_io,
        weaponResourceID = R.string.option_io_one,
        helmResourceID = R.string.option_io_one,
        armorResourceID = R.string.option_io_one,
        shieldResourceID = R.string.option_io_one,
        gemResourceID = R.drawable.ic_low_topaz,
        compoundCount = 3
    ),
    LUM(
        nameResourceID = R.string.title_rune_lum,
        iconResourceID = R.drawable.ic_rune_lum,
        weaponResourceID = R.string.option_lum_one,
        helmResourceID = R.string.option_lum_one,
        armorResourceID = R.string.option_lum_one,
        shieldResourceID = R.string.option_lum_one,
        gemResourceID = R.drawable.ic_low_amethyst,
        compoundCount = 3
    ),
    KO(
        nameResourceID = R.string.title_rune_ko,
        iconResourceID = R.drawable.ic_rune_ko,
        weaponResourceID = R.string.option_ko_one,
        helmResourceID = R.string.option_ko_one,
        armorResourceID = R.string.option_ko_one,
        shieldResourceID = R.string.option_ko_one,
        gemResourceID = R.drawable.ic_low_sapphire,
        compoundCount = 3
    ),
    FAL(
        nameResourceID = R.string.title_rune_fal,
        iconResourceID = R.drawable.ic_rune_fal,
        weaponResourceID = R.string.option_fal_one,
        helmResourceID = R.string.option_fal_one,
        armorResourceID = R.string.option_fal_one,
        shieldResourceID = R.string.option_fal_one,
        gemResourceID = R.drawable.ic_low_ruby,
        compoundCount = 3
    ),
    LEM(
        nameResourceID = R.string.title_rune_lem,
        iconResourceID = R.drawable.ic_rune_lem,
        weaponResourceID = R.string.option_lem_one,
        helmResourceID = R.string.option_lem_two,
        armorResourceID = R.string.option_lem_two,
        shieldResourceID = R.string.option_lem_two,
        gemResourceID = R.drawable.ic_low_emerald,
        compoundCount = 3
    ),
    PUL(
        nameResourceID = R.string.title_rune_pul,
        iconResourceID = R.drawable.ic_rune_pul,
        weaponResourceID = R.string.option_pul_one,
        helmResourceID = R.string.option_pul_two,
        armorResourceID = R.string.option_pul_two,
        shieldResourceID = R.string.option_pul_two,
        gemResourceID = R.drawable.ic_low_diamond,
        compoundCount = 2
    ),
    UM(
        nameResourceID = R.string.title_rune_um,
        iconResourceID = R.drawable.ic_rune_um,
        weaponResourceID = R.string.option_um_one,
        helmResourceID = R.string.option_um_two,
        armorResourceID = R.string.option_um_two,
        shieldResourceID = R.string.option_um_three,
        gemResourceID = R.drawable.ic_topaz,
        compoundCount = 2
    ),
    MAL(
        nameResourceID = R.string.title_rune_mal,
        iconResourceID = R.drawable.ic_rune_mal,
        weaponResourceID = R.string.option_mal_one,
        helmResourceID = R.string.option_mal_two,
        armorResourceID = R.string.option_mal_two,
        shieldResourceID = R.string.option_mal_two,
        gemResourceID = R.drawable.ic_amethyst,
        compoundCount = 2
    ),
    IST(
        nameResourceID = R.string.title_rune_ist,
        iconResourceID = R.drawable.ic_rune_ist,
        weaponResourceID = R.string.option_ist_one,
        helmResourceID = R.string.option_ist_two,
        armorResourceID = R.string.option_ist_two,
        shieldResourceID = R.string.option_ist_two,
        gemResourceID = R.drawable.ic_sapphire,
        compoundCount = 2
    ),
    GUL(
        nameResourceID = R.string.title_rune_gul,
        iconResourceID = R.drawable.ic_rune_gul,
        weaponResourceID = R.string.option_gul_one,
        helmResourceID = R.string.option_gul_two,
        armorResourceID = R.string.option_gul_two,
        shieldResourceID = R.string.option_gul_two,
        gemResourceID = R.drawable.ic_ruby,
        compoundCount = 2
    ),
    VEX(
        nameResourceID = R.string.title_rune_vex,
        iconResourceID = R.drawable.ic_rune_vex,
        weaponResourceID = R.string.option_vex_one,
        helmResourceID = R.string.option_vex_two,
        armorResourceID = R.string.option_vex_two,
        shieldResourceID = R.string.option_vex_two,
        gemResourceID = R.drawable.ic_emerald,
        compoundCount = 2
    ),
    OHM(
        nameResourceID = R.string.title_rune_ohm,
        iconResourceID = R.drawable.ic_rune_ohm,
        weaponResourceID = R.string.option_ohm_one,
        helmResourceID = R.string.option_ohm_two,
        armorResourceID = R.string.option_ohm_two,
        shieldResourceID = R.string.option_ohm_two,
        gemResourceID = R.drawable.ic_diamond,
        compoundCount = 2
    ),
    LO(
        nameResourceID = R.string.title_rune_lo,
        iconResourceID = R.drawable.ic_rune_lo,
        weaponResourceID = R.string.option_lo_one,
        helmResourceID = R.string.option_lo_two,
        armorResourceID = R.string.option_lo_two,
        shieldResourceID = R.string.option_lo_two,
        gemResourceID = R.drawable.ic_high_topaz,
        compoundCount = 2
    ),
    SUR(
        nameResourceID = R.string.title_rune_sur,
        iconResourceID = R.drawable.ic_rune_sur,
        weaponResourceID = R.string.option_sur_one,
        helmResourceID = R.string.option_sur_two,
        armorResourceID = R.string.option_sur_two,
        shieldResourceID = R.string.option_sur_three,
        gemResourceID = R.drawable.ic_high_amethyst,
        compoundCount = 2
    ),
    BER(
        nameResourceID = R.string.title_rune_ber,
        iconResourceID = R.drawable.ic_rune_ber,
        weaponResourceID = R.string.option_ber_one,
        helmResourceID = R.string.option_ber_two,
        armorResourceID = R.string.option_ber_two,
        shieldResourceID = R.string.option_ber_two,
        gemResourceID = R.drawable.ic_high_sapphire,
        compoundCount = 2
    ),
    JAH(
        nameResourceID = R.string.title_rune_jah,
        iconResourceID = R.drawable.ic_rune_jah,
        weaponResourceID = R.string.option_jah_one,
        helmResourceID = R.string.option_jah_two,
        armorResourceID = R.string.option_jah_two,
        shieldResourceID = R.string.option_jah_three,
        gemResourceID = R.drawable.ic_high_ruby,
        compoundCount = 2
    ),
    CHAM(
        nameResourceID = R.string.title_rune_cham,
        iconResourceID = R.drawable.ic_rune_cham,
        weaponResourceID = R.string.option_cham_one,
        helmResourceID = R.string.option_cham_two,
        armorResourceID = R.string.option_cham_two,
        shieldResourceID = R.string.option_cham_two,
        gemResourceID = R.drawable.ic_high_emerald,
        compoundCount = 2
    ),
    ZOD(
        nameResourceID = R.string.title_rune_zod,
        iconResourceID = R.drawable.ic_rune_zod,
        weaponResourceID = R.string.option_zod_one,
        helmResourceID = R.string.option_zod_one,
        armorResourceID = R.string.option_zod_one,
        shieldResourceID = R.string.option_zod_one,
        compoundCount = 0
    );

    companion object {

        fun all(): List<Rune> = entries

        fun findByName(name: String): Rune? = entries
            .firstOrNull {
                it.name.lowercase() == name.lowercase()
            }

        fun previous(target: Rune): Rune? {
            if (target == EL) return null
            return entries.first {
                it.ordinal == target.ordinal - 1
            }
        }
    }
}
