package com.gildedrose;

public class GildedRose {
    public static final String AGED_BRIE="Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAXIMUM_QUALITY = 50;
    public static final int BACKSTAGE_PASS_THRESHOLD1 = 11;
    public static final int BACKSTAGE_PASS_THRESHOLD2 = 6;
    public static final String CONJURED = "Conjured";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public static int somar(int numero1, int numero2) {
        return numero1 + numero2;
    }

    public void updateQuality() {
        for (Item item : items) {
            handleIfNormalItem(item);
            handleIfAgedBrie(item);
            handleIfBackstagePasses(item);
            handleIfSulfuras(item);
            handleIfConjured(item);
        }
    }

    private void handleIfConjured(Item item) {
        if (isConjured(item)){
            item.sellIn--;
            item.quality-=2;
        }
    }

    private boolean isConjured(Item item) {
        return item.name.equals(CONJURED);
    }

    private void handleIfSulfuras(Item item){
            }


    private void handleIfBackstagePasses(Item item) {
        if(isBackstagePasses(item)){
            item.sellIn--;
            if(item.sellIn<=0){
                item.quality=0;
            }else if(item.sellIn<BACKSTAGE_PASS_THRESHOLD2){
                item.quality+=3;
            }else if(item.sellIn<BACKSTAGE_PASS_THRESHOLD1){
                item.quality+=2;
            }else{
                item.quality++;
            }
            if(item.quality>MAXIMUM_QUALITY){
                item.quality=MAXIMUM_QUALITY;
            }
        }
    }

    private void handleIfAgedBrie(Item item) {
        if (isAgedBrie(item)){
            item.sellIn--;
            if(item.sellIn<=0){
                item.quality+=2;
            }else{
                item.quality++;
            }
            if(item.quality>MAXIMUM_QUALITY){
                item.quality=MAXIMUM_QUALITY;
            }
        }
    }

    private void handleIfNormalItem(Item item) {
       if(isNormalItem(item)){
           item.sellIn--;
           if(item.sellIn<=0)
               item.quality -= 2;
           else
               item.quality--;
           if(item.quality<0)
               item.quality=0;
       }

    }

    private boolean isNormalItem(Item item) {
        return !(isBackstagePasses(item) || isSulfuras(item) || isAgedBrie(item) || isConjured(item));
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }
}