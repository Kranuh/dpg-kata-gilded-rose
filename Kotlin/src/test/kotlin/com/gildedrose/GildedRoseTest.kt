package com.gildedrose

import com.gildedrose.domain.model.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `assert that a regular item decreases in quality and that the sellIn value decreases`() {
        // Given
        val item = arrayOf(Item("testitem", 10, 10))
        val app = GildedRose(item)

        // When
        app.updateQuality()

        // Then
        assertEquals(9, app.items[0].sellIn)
        assertEquals(9, app.items[0].quality)
    }

    @Test
    fun `assert that a regular item decreases in value twice as fast, when sellIn hits 0`() {
        // Given
        val item = arrayOf(Item("testitem", 0, 10))
        val app = GildedRose(item)

        // When
        app.updateQuality()

        // Then
        assertEquals(8, app.items[0].quality)
    }

    @Test
    fun `assert that a regular item can not decrease below 0 in quality`() {
        // Given
        val item = arrayOf(Item("testitem", 0, 0))
        val app = GildedRose(item)

        // When
        app.updateQuality()

        // Then
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `assert that aged items can increase in quality`() {
        // Given
        val agedItem = arrayOf(Item("Aged Brie", 10, 10))
        val app = GildedRose(agedItem)

        // When
        app.updateQuality()

        // Then
        assertEquals(11, app.items[0].quality)
    }

    @Test
    fun `assert that aged items can increase in quality, but can never go above a quality of 50`() {
        // Given
        val agedItem = arrayOf(Item("Aged Brie", 10, 50))
        val app = GildedRose(agedItem)

        // When
        app.updateQuality()

        // Then
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun `assert legendary items never decrease in sellIn value or quality value`() {
        // Given
        val legendaryItem = arrayOf(Item("Sulfuras, Hand of Ragnaros", 10, 80))
        val app = GildedRose(legendaryItem)

        // When
        app.updateQuality()

        // Then
        assertEquals(10, app.items[0].sellIn)
        assertEquals(80, app.items[0].quality)
    }

    @Test
    fun `assert that backstage passes increases in quality like aged items if the sellIn value is greater than 10`() {
        // Given
        val backstagePass = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, 10))
        val app = GildedRose(backstagePass)

        // When
        app.updateQuality()

        // Then
        assertEquals(10, app.items[0].sellIn)
        assertEquals(11, app.items[0].quality)
    }

    @Test
    fun `assert that backstage passes increases in quality twice as fast if the sellIn value is 10 or less, but not lower than 6`() {
        // Given
        val backstagePasses = arrayOf(
            Item("Backstage passes to a TAFKAL80ETC concert", 10, 10),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)
        )
        val app = GildedRose(backstagePasses)

        // When
        app.updateQuality()

        // Then
        assertEquals(9, app.items[0].sellIn)
        assertEquals(12, app.items[0].quality)
        assertNotEquals(12, app.items[1].quality)
    }

    @Test
    fun `assert that backstage passes increases in quality thrice as fast if the sellIn value is 5 or less`() {
        // Given
        val backstagePass = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 10))
        val app = GildedRose(backstagePass)

        // When
        app.updateQuality()

        // Then
        assertEquals(4, app.items[0].sellIn)
        assertEquals(13, app.items[0].quality)
    }

    @Test
    fun `assert that backstage passes their quality drop to 0 when the sellIn value reaches 0`() {
        // Given
        val backstagePass = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 10))
        val app = GildedRose(backstagePass)

        // When
        app.updateQuality()

        // Then
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `assert that conjured items degrade in quality twice as fast as normal`() {
        // Given
        val conjuredItems = arrayOf(
            Item("Conjured Item", 10, 10),
            Item("Conjured Item", 0, 10)
        )
        val app = GildedRose(conjuredItems)

        // When
        app.updateQuality()

        // Then
        assertEquals(9, app.items[0].sellIn)
        assertEquals(8, app.items[0].quality)
        assertEquals(6, app.items[1].quality)
    }


}


