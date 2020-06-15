package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GildedRoseTest {
    private Item createAndUpdate(String name, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0];
    }

    @Test
    public void testFrameworkWorks() {
        //Item item = createAndUpdate(0,0);
        assertEquals("foo", createAndUpdate("foo", 0,0).name);
    }

    //Add pin down tests to refactor legacy code, to test hipotesis of how we think the code works
    @Test
    public void systemLowersValues(){
        Item item = createAndUpdate("foo", 15,25);
        assertEquals(14,item.sellIn);
        assertEquals(24,item.quality);
    }

    @Test
    public void qualityDegradesTwiceAsFast(){
        assertEquals(15,createAndUpdate("foo", 0,17).quality);
    }
    @Test
    public void qualityIsNeverNegative(){
        assertEquals(0,createAndUpdate("foo", 0,0).quality);
    }

    @Test
    public void agedBrieIncreasesInQuality(){
        assertEquals(26,createAndUpdate(GildedRose.AGED_BRIE, 15,25).quality);
    }

    @Test
    public void qualityNeverMoreThanMaximum(){
        assertEquals(51,createAndUpdate("foo",15,52).quality);
        assertEquals(GildedRose.MAXIMUM_QUALITY,createAndUpdate(GildedRose.AGED_BRIE,15,GildedRose.MAXIMUM_QUALITY).quality);
    }

    @Test
    public void agedBrieNeverExpires(){
        assertEquals(-1,createAndUpdate(GildedRose.AGED_BRIE,0,42).sellIn);
        assertEquals(44,createAndUpdate(GildedRose.AGED_BRIE,0,42).quality);
    }

    @Test
    public void backstagePassMaximumQuality(){
        assertEquals(GildedRose.MAXIMUM_QUALITY,createAndUpdate(GildedRose.BACKSTAGE_PASSES,10,48).quality);
        assertEquals(GildedRose.MAXIMUM_QUALITY,createAndUpdate(GildedRose.BACKSTAGE_PASSES,10,49).quality);
    }

    @Test
    public void backstagePassMinimumQuality(){
        assertEquals(0,createAndUpdate(GildedRose.BACKSTAGE_PASSES,0,1).quality);
    }

    @Test
    public void backstagePassMinimumSellinQualityThreeTimesFaster(){
        assertEquals(4,createAndUpdate(GildedRose.BACKSTAGE_PASSES,3,1).quality);
    }

    @Test
    public void backstagePassMaximumSellinNormalQuality(){
        assertEquals(4,createAndUpdate(GildedRose.BACKSTAGE_PASSES,12,3).quality);
    }

    @Test
    public void conjuredDegradeTwiceAsFast(){
        assertEquals(23,createAndUpdate(GildedRose.CONJURED,15,25).quality);
    }

    @Test
    public void itemStringIsCorrect(){
        assertEquals("Conjured, 14, 23",createAndUpdate(GildedRose.CONJURED,15,25).toString());
    }

    @Test
    public void testaSoma(){
        assertEquals(2,GildedRose.somar(1,1));
    }


}
