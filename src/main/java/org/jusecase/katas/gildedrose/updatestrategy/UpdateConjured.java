package org.jusecase.katas.gildedrose.updatestrategy;

import org.jusecase.katas.gildedrose.entities.Item;

public class UpdateConjured extends UpdateStandard {
    @Override
    protected int getQualityDegenerationPerDay(Item item) {
        return 2 * super.getQualityDegenerationPerDay(item);
    }
}
