package org.jusecase.katas.gildedrose.entities;

import org.jusecase.katas.gildedrose.updatestrategy.UpdateStrategy;

/**
 * Ugly comment:
 * If there were no angry goblin, we would simply move all of this stuff into the item class!
 */
public class ItemEntity extends Item {
    private UpdateStrategy updateStrategy;

    public void setUpdateStrategy(UpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
    }

    public void update() {
        updateStrategy.update(this);
    }
}
