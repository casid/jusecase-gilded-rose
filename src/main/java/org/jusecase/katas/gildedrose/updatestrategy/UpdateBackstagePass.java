package org.jusecase.katas.gildedrose.updatestrategy;

import org.jusecase.katas.gildedrose.entities.Item;

public class UpdateBackstagePass extends UpdateStandard {
    @Override
    protected void updateQuality(Item item) {
        if (item.getSellIn() > 10) {
            changeQuality(item, item.getQuality() + 1);
        } else if (item.getSellIn() > 5) {
            changeQuality(item, item.getQuality() + 2);
        } else if (item.getSellIn() > 0) {
            changeQuality(item, item.getQuality() + 3);
        } else {
            changeQuality(item, 0);
        }
    }
}
