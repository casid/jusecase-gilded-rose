package org.jusecase.katas.gildedrose.updatestrategy;

import org.jusecase.katas.gildedrose.entities.Item;

public interface UpdateStrategy {
    void update(Item item);
}
