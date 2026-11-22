package com.beok.runewords.common.resource

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.beok.runewords.common.model.Rune

private class RuneResource(
    @StringRes val name: Int,
    @DrawableRes val icon: Int,
    @StringRes val weapon: Int,
    @StringRes val helm: Int,
    @StringRes val armor: Int,
    @StringRes val shield: Int,
    @DrawableRes val gem: Int?
)

val Rune.nameResourceID: Int
    @StringRes get() = resource().name

val Rune.iconResourceID: Int
    @DrawableRes get() = resource().icon

val Rune.weaponResourceID: Int
    @StringRes get() = resource().weapon

val Rune.helmResourceID: Int
    @StringRes get() = resource().helm

val Rune.armorResourceID: Int
    @StringRes get() = resource().armor

val Rune.shieldResourceID: Int
    @StringRes get() = resource().shield

val Rune.gemResourceID: Int?
    @DrawableRes get() = resource().gem

@Suppress("LongMethod", "CyclomaticComplexMethod")
private fun Rune.resource(): RuneResource = when (this) {
    Rune.EL -> RuneResource(
        name = R.string.title_rune_el,
        icon = R.drawable.ic_rune_el,
        weapon = R.string.option_el_one,
        helm = R.string.option_el_two,
        armor = R.string.option_el_two,
        shield = R.string.option_el_two,
        gem = null
    )
    Rune.ELD -> RuneResource(
        name = R.string.title_rune_eld,
        icon = R.drawable.ic_rune_eld,
        weapon = R.string.option_eld_one,
        helm = R.string.option_eld_two,
        armor = R.string.option_eld_two,
        shield = R.string.option_eld_three,
        gem = null
    )
    Rune.TIR -> RuneResource(
        name = R.string.title_rune_tir,
        icon = R.drawable.ic_rune_tir,
        weapon = R.string.option_tir_one,
        helm = R.string.option_tir_one,
        armor = R.string.option_tir_one,
        shield = R.string.option_tir_one,
        gem = null
    )
    Rune.NEF -> RuneResource(
        name = R.string.title_rune_nef,
        icon = R.drawable.ic_rune_nef,
        weapon = R.string.option_nef_one,
        helm = R.string.option_nef_two,
        armor = R.string.option_nef_two,
        shield = R.string.option_nef_two,
        gem = null
    )
    Rune.ETH -> RuneResource(
        name = R.string.title_rune_eth,
        icon = R.drawable.ic_rune_eth,
        weapon = R.string.option_eth_one,
        helm = R.string.option_eth_two,
        armor = R.string.option_eth_two,
        shield = R.string.option_eth_two,
        gem = null
    )
    Rune.ITH -> RuneResource(
        name = R.string.title_rune_ith,
        icon = R.drawable.ic_rune_ith,
        weapon = R.string.option_ith_one,
        helm = R.string.option_ith_two,
        armor = R.string.option_ith_two,
        shield = R.string.option_ith_two,
        gem = null
    )
    Rune.TAL -> RuneResource(
        name = R.string.title_rune_tal,
        icon = R.drawable.ic_rune_tal,
        weapon = R.string.option_tal_one,
        helm = R.string.option_tal_two,
        armor = R.string.option_tal_two,
        shield = R.string.option_tal_three,
        gem = null
    )
    Rune.RAL -> RuneResource(
        name = R.string.title_rune_ral,
        icon = R.drawable.ic_rune_ral,
        weapon = R.string.option_ral_one,
        helm = R.string.option_ral_two,
        armor = R.string.option_ral_two,
        shield = R.string.option_ral_three,
        gem = null
    )
    Rune.ORT -> RuneResource(
        name = R.string.title_rune_ort,
        icon = R.drawable.ic_rune_ort,
        weapon = R.string.option_ort_one,
        helm = R.string.option_ort_two,
        armor = R.string.option_ort_two,
        shield = R.string.option_ort_three,
        gem = null
    )
    Rune.THUL -> RuneResource(
        name = R.string.title_rune_thul,
        icon = R.drawable.ic_rune_thul,
        weapon = R.string.option_thul_one,
        helm = R.string.option_thul_two,
        armor = R.string.option_thul_two,
        shield = R.string.option_thul_three,
        gem = R.drawable.ic_chip_topaz
    )
    Rune.AMN -> RuneResource(
        name = R.string.title_rune_amn,
        icon = R.drawable.ic_rune_amn,
        weapon = R.string.option_amn_one,
        helm = R.string.option_amn_two,
        armor = R.string.option_amn_two,
        shield = R.string.option_amn_two,
        gem = R.drawable.ic_chip_amethyst
    )
    Rune.SOL -> RuneResource(
        name = R.string.title_rune_sol,
        icon = R.drawable.ic_rune_sol,
        weapon = R.string.option_sol_one,
        helm = R.string.option_sol_two,
        armor = R.string.option_sol_two,
        shield = R.string.option_sol_two,
        gem = R.drawable.ic_chip_sapphire
    )
    Rune.SHAEL -> RuneResource(
        name = R.string.title_rune_shael,
        icon = R.drawable.ic_rune_shael,
        weapon = R.string.option_shael_one,
        helm = R.string.option_shael_two,
        armor = R.string.option_shael_two,
        shield = R.string.option_shael_three,
        gem = R.drawable.ic_chip_ruby
    )
    Rune.DOL -> RuneResource(
        name = R.string.title_rune_dol,
        icon = R.drawable.ic_rune_dol,
        weapon = R.string.option_dol_one,
        helm = R.string.option_dol_two,
        armor = R.string.option_dol_two,
        shield = R.string.option_dol_two,
        gem = R.drawable.ic_chip_emerald
    )
    Rune.HEL -> RuneResource(
        name = R.string.title_rune_hel,
        icon = R.drawable.ic_rune_hel,
        weapon = R.string.option_hel_one,
        helm = R.string.option_hel_two,
        armor = R.string.option_hel_two,
        shield = R.string.option_hel_two,
        gem = R.drawable.ic_chip_diamond
    )
    Rune.IO -> RuneResource(
        name = R.string.title_rune_io,
        icon = R.drawable.ic_rune_io,
        weapon = R.string.option_io_one,
        helm = R.string.option_io_one,
        armor = R.string.option_io_one,
        shield = R.string.option_io_one,
        gem = R.drawable.ic_low_topaz
    )
    Rune.LUM -> RuneResource(
        name = R.string.title_rune_lum,
        icon = R.drawable.ic_rune_lum,
        weapon = R.string.option_lum_one,
        helm = R.string.option_lum_one,
        armor = R.string.option_lum_one,
        shield = R.string.option_lum_one,
        gem = R.drawable.ic_low_amethyst
    )
    Rune.KO -> RuneResource(
        name = R.string.title_rune_ko,
        icon = R.drawable.ic_rune_ko,
        weapon = R.string.option_ko_one,
        helm = R.string.option_ko_one,
        armor = R.string.option_ko_one,
        shield = R.string.option_ko_one,
        gem = R.drawable.ic_low_sapphire
    )
    Rune.FAL -> RuneResource(
        name = R.string.title_rune_fal,
        icon = R.drawable.ic_rune_fal,
        weapon = R.string.option_fal_one,
        helm = R.string.option_fal_one,
        armor = R.string.option_fal_one,
        shield = R.string.option_fal_one,
        gem = R.drawable.ic_low_ruby
    )
    Rune.LEM -> RuneResource(
        name = R.string.title_rune_lem,
        icon = R.drawable.ic_rune_lem,
        weapon = R.string.option_lem_one,
        helm = R.string.option_lem_two,
        armor = R.string.option_lem_two,
        shield = R.string.option_lem_two,
        gem = R.drawable.ic_low_emerald
    )
    Rune.PUL -> RuneResource(
        name = R.string.title_rune_pul,
        icon = R.drawable.ic_rune_pul,
        weapon = R.string.option_pul_one,
        helm = R.string.option_pul_two,
        armor = R.string.option_pul_two,
        shield = R.string.option_pul_two,
        gem = R.drawable.ic_low_diamond
    )
    Rune.UM -> RuneResource(
        name = R.string.title_rune_um,
        icon = R.drawable.ic_rune_um,
        weapon = R.string.option_um_one,
        helm = R.string.option_um_two,
        armor = R.string.option_um_two,
        shield = R.string.option_um_three,
        gem = R.drawable.ic_topaz
    )
    Rune.MAL -> RuneResource(
        name = R.string.title_rune_mal,
        icon = R.drawable.ic_rune_mal,
        weapon = R.string.option_mal_one,
        helm = R.string.option_mal_two,
        armor = R.string.option_mal_two,
        shield = R.string.option_mal_two,
        gem = R.drawable.ic_amethyst
    )
    Rune.IST -> RuneResource(
        name = R.string.title_rune_ist,
        icon = R.drawable.ic_rune_ist,
        weapon = R.string.option_ist_one,
        helm = R.string.option_ist_two,
        armor = R.string.option_ist_two,
        shield = R.string.option_ist_two,
        gem = R.drawable.ic_sapphire
    )
    Rune.GUL -> RuneResource(
        name = R.string.title_rune_gul,
        icon = R.drawable.ic_rune_gul,
        weapon = R.string.option_gul_one,
        helm = R.string.option_gul_two,
        armor = R.string.option_gul_two,
        shield = R.string.option_gul_two,
        gem = R.drawable.ic_ruby
    )
    Rune.VEX -> RuneResource(
        name = R.string.title_rune_vex,
        icon = R.drawable.ic_rune_vex,
        weapon = R.string.option_vex_one,
        helm = R.string.option_vex_two,
        armor = R.string.option_vex_two,
        shield = R.string.option_vex_two,
        gem = R.drawable.ic_emerald
    )
    Rune.OHM -> RuneResource(
        name = R.string.title_rune_ohm,
        icon = R.drawable.ic_rune_ohm,
        weapon = R.string.option_ohm_one,
        helm = R.string.option_ohm_two,
        armor = R.string.option_ohm_two,
        shield = R.string.option_ohm_two,
        gem = R.drawable.ic_diamond
    )
    Rune.LO -> RuneResource(
        name = R.string.title_rune_lo,
        icon = R.drawable.ic_rune_lo,
        weapon = R.string.option_lo_one,
        helm = R.string.option_lo_two,
        armor = R.string.option_lo_two,
        shield = R.string.option_lo_two,
        gem = R.drawable.ic_high_topaz
    )
    Rune.SUR -> RuneResource(
        name = R.string.title_rune_sur,
        icon = R.drawable.ic_rune_sur,
        weapon = R.string.option_sur_one,
        helm = R.string.option_sur_two,
        armor = R.string.option_sur_two,
        shield = R.string.option_sur_three,
        gem = R.drawable.ic_high_amethyst
    )
    Rune.BER -> RuneResource(
        name = R.string.title_rune_ber,
        icon = R.drawable.ic_rune_ber,
        weapon = R.string.option_ber_one,
        helm = R.string.option_ber_two,
        armor = R.string.option_ber_two,
        shield = R.string.option_ber_two,
        gem = R.drawable.ic_high_sapphire
    )
    Rune.JAH -> RuneResource(
        name = R.string.title_rune_jah,
        icon = R.drawable.ic_rune_jah,
        weapon = R.string.option_jah_one,
        helm = R.string.option_jah_two,
        armor = R.string.option_jah_two,
        shield = R.string.option_jah_three,
        gem = R.drawable.ic_high_ruby
    )
    Rune.CHAM -> RuneResource(
        name = R.string.title_rune_cham,
        icon = R.drawable.ic_rune_cham,
        weapon = R.string.option_cham_one,
        helm = R.string.option_cham_two,
        armor = R.string.option_cham_two,
        shield = R.string.option_cham_two,
        gem = R.drawable.ic_high_emerald
    )
    Rune.ZOD -> RuneResource(
        name = R.string.title_rune_zod,
        icon = R.drawable.ic_rune_zod,
        weapon = R.string.option_zod_one,
        helm = R.string.option_zod_one,
        armor = R.string.option_zod_one,
        shield = R.string.option_zod_one,
        gem = null
    )
}
