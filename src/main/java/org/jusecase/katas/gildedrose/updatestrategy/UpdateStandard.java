package org.jusecase.katas.gildedrose.updatestrategy;

import org.jusecase.katas.gildedrose.entities.Item;

public class UpdateStandard implements UpdateStrategy {
    @Override
    public void update(Item item) {
        updateQuality(item);
        updateSellIn(item);
    }

    protected void updateQuality(Item item) {
        changeQuality(item, item.getQuality() - getQualityDegenerationPerDay(item));
    }

    protected void updateSellIn(Item item) {
        changeSellIn(item, item.getSellIn() - 1);
    }

    protected void changeQuality(Item item, int quality) {
        if (quality < 0) {
            quality = 0;
        } else if (quality > 50) {
            quality = 50;
        }
        item.setQuality(quality);
    }

    protected void changeSellIn(Item item, int sellIn) {
        item.setSellIn(sellIn);
    }

    protected int getQualityDegenerationPerDay(Item item) {
        return item.getSellIn() >= 0 ? 1 : 2;
    }
}
