package com.beok.runewords.common.model

enum class Rune(val compoundCount: Int) {
    EL(compoundCount = 3),
    ELD(compoundCount = 3),
    TIR(compoundCount = 3),
    NEF(compoundCount = 3),
    ETH(compoundCount = 3),
    ITH(compoundCount = 3),
    TAL(compoundCount = 3),
    RAL(compoundCount = 3),
    ORT(compoundCount = 3),
    THUL(compoundCount = 3),
    AMN(compoundCount = 3),
    SOL(compoundCount = 3),
    SHAEL(compoundCount = 3),
    DOL(compoundCount = 3),
    HEL(compoundCount = 3),
    IO(compoundCount = 3),
    LUM(compoundCount = 3),
    KO(compoundCount = 3),
    FAL(compoundCount = 3),
    LEM(compoundCount = 3),
    PUL(compoundCount = 2),
    UM(compoundCount = 2),
    MAL(compoundCount = 2),
    IST(compoundCount = 2),
    GUL(compoundCount = 2),
    VEX(compoundCount = 2),
    OHM(compoundCount = 2),
    LO(compoundCount = 2),
    SUR(compoundCount = 2),
    BER(compoundCount = 2),
    JAH(compoundCount = 2),
    CHAM(compoundCount = 2),
    ZOD(compoundCount = 0);

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
