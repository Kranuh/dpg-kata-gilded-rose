package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
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

