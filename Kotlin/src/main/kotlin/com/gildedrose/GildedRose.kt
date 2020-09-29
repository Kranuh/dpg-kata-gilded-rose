package com.gildedrose

import com.gildedrose.utils.mapInPlace

interface QualityChanger {
    fun updateQuality()
}

class RegularItem(name: String, sellIn: Int, quality: Int) : Item(name, sellIn, quality),
                                                             QualityChanger {
    override fun updateQuality() {
        val degradeFactor = if (sellIn > 0) 1 else 2
        quality -= degradeFactor
    }
}

class AgedItem(name: String, sellIn: Int, quality: Int) : Item(name, sellIn, quality),
                                                          QualityChanger {
    override fun updateQuality() {
        val ageFactor = if (sellIn < 0) 2 else 1
        quality += ageFactor
    }
}

class TicketItem(name: String, sellIn: Int, quality: Int) : Item(name, sellIn, quality),
                                                            QualityChanger {
    override fun updateQuality() {
        val ageFactor = when {
            sellIn == 0 -> -quality
            sellIn < 6 -> 3
            sellIn < 11 -> 2
            else -> 1
        }

        quality += ageFactor
    }
}

class LegendaryItem(name: String, sellIn: Int, quality: Int) : Item(name, sellIn, quality)

class GildedRose(var items: Array<Item>) {

    private companion object {
        const val AGED_BRIE = "Aged Brie"
        const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        const val SULFURAS = "Sulfuras, Hand of Ragnaros"

        const val MAX_QUALITY = 50
    }

    fun updateQuality() {
        items.mapInPlace { item ->
            val mappedItem = mapItem(item)

            if (mappedItem is QualityChanger) {
                mappedItem.updateQuality()
            }

            if (mappedItem !is LegendaryItem) {
                mappedItem.sellIn -= 1
                mappedItem.quality = restrictQuality(mappedItem.quality)
            }

            mappedItem
        }
    }

    private fun mapItem(item: Item): Item {
        return when (item.name) {
            AGED_BRIE -> AgedItem(item.name, item.sellIn, item.quality)
            BACKSTAGE_PASS -> TicketItem(item.name, item.sellIn, item.quality)
            SULFURAS -> LegendaryItem(item.name, item.sellIn, item.quality)
            else -> RegularItem(item.name, item.sellIn, item.quality)
        }
    }

    private fun restrictQuality(currentQuality: Int): Int {
        return when {
            currentQuality < 0 -> {
                0
            }
            currentQuality > MAX_QUALITY -> {
                MAX_QUALITY
            }
            else -> {
                currentQuality
            }
        }
    }

    fun oldUpdateQuality() {
        for (i in items.indices) {
            if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                degradeItem(i)
            } else {
                if (items[i].quality < 50) {
                    // This block increases quality, for backstage passes it increases the quality multiple times based on sellIn value
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            // Decrease sellIn value
            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (items[i].name != "Aged Brie") {
                    if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                        // Decrease quality twice as fast, since sellIn value < 0
                        degradeItem(i)
                    } else {
                        // Set item quality to 0, because of backstage pass requirement
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
                        // Increase quality a second time for aged brie
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }

    private fun degradeItem(i: Int) {
        if (items[i].quality > 0) {
            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                items[i].quality = items[i].quality - 1
            }
        }
    }

}

