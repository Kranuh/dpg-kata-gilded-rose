package com.gildedrose.domain.model

class SpoilingItem(name: String, sellIn: Int, quality: Int, private val degradeFactor: Int = 1) : Item(name, sellIn, quality),
                                                                                                  QualityChanger {
    override fun updateQuality() {
        val degradeFactor = if (sellIn > 0) (1 * degradeFactor) else (2 * degradeFactor)
        quality -= degradeFactor
    }
}

class AgedItem(name: String, sellIn: Int, quality: Int) : Item(name, sellIn, quality),
                                                          QualityChanger {
    override fun updateQuality() {
        val qualityIncrease = if (sellIn < 0) 2 else 1
        quality += qualityIncrease
    }
}

class TicketItem(name: String, sellIn: Int, quality: Int) : Item(name, sellIn, quality),
                                                            QualityChanger {
    override fun updateQuality() {
        val qualityIncrease = when {
            sellIn == 0 -> -quality
            sellIn < 6 -> 3
            sellIn < 11 -> 2
            else -> 1
        }

        quality += qualityIncrease
    }
}

class LegendaryItem(name: String, sellIn: Int, quality: Int) : Item(name, sellIn, quality)
