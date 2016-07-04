package org.jusecase.katas.gildedrose.updatestrategy;

import org.jusecase.katas.gildedrose.entities.Item;

public class UpdateLegendary implements UpdateStrategy {
    @Override
    public void update(Item item) {
        // Legendary items are never touched.
    }
}
