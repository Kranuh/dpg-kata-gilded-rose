package com.gildedrose

import com.gildedrose.domain.model.AgedItem
import com.gildedrose.domain.model.Item
import com.gildedrose.domain.model.LegendaryItem
import com.gildedrose.domain.model.QualityChanger
import com.gildedrose.domain.model.SpoilingItem
import com.gildedrose.domain.model.TicketItem
import com.gildedrose.utils.mapInPlace

class GildedRose(var items: Array<Item>) {

    private companion object {
        const val AGED_BRIE = "Aged Brie"
        const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
        const val SULFURAS = "Sulfuras, Hand of Ragnaros"
        const val CONJURED = "Conjured Item"

        const val CONJURED_ITEM_DEGRADATION = 2
        const val MAX_QUALITY = 50
    }

    fun updateQuality() {
        items.mapInPlace { item ->
            val mappedItem = mapItem(item)
            updateMappedItem(mappedItem)
        }
    }

    private fun mapItem(item: Item) = when (item.name) {
        AGED_BRIE -> AgedItem(item.name, item.sellIn, item.quality)
        BACKSTAGE_PASS -> TicketItem(item.name, item.sellIn, item.quality)
        SULFURAS -> LegendaryItem(item.name, item.sellIn, item.quality)
        CONJURED -> SpoilingItem(item.name, item.sellIn, item.quality, CONJURED_ITEM_DEGRADATION)
        else -> SpoilingItem(item.name, item.sellIn, item.quality)
    }

    private fun updateMappedItem(mappedItem: Item): Item {
        if (mappedItem is QualityChanger) {
            mappedItem.updateQuality()
        }

        if (mappedItem !is LegendaryItem) {
            mappedItem.sellIn -= 1
            mappedItem.quality = restrictQuality(mappedItem.quality)
        }

        return mappedItem
    }

    private fun restrictQuality(currentQuality: Int) = when {
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

