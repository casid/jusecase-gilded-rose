package org.jusecase.katas.gildedrose.updatestrategy;

import org.jusecase.katas.gildedrose.entities.Item;

public class UpdateAgedBree extends UpdateStandard {
    @Override
    protected void updateQuality(Item item) {
        changeQuality(item, item.getQuality() + 1);
    }
}
