package org.jusecase.katas.gildedrose.entities;

import org.jusecase.builders.Builder;
import org.jusecase.katas.gildedrose.updatestrategy.*;

public class ItemEntityBuilder implements Builder<ItemEntity> {
    private ItemEntity item = new ItemEntity();

    @Override
    public ItemEntity build() {
        return item;
    }

    public static ItemEntityBuilder item() {
        return new ItemEntityBuilder();
    }

    public ItemEntityBuilder standard() {
        return this
                .withName("StandardItem")
                .withQuality(5)
                .withSellIn(7)
                .withUpdateStrategy(new UpdateStandard());
    }

    public ItemEntityBuilder agedBree() {
        return this
                .standard()
                .withName("Aged Bree")
                .withUpdateStrategy(new UpdateAgedBree());
    }

    public ItemEntityBuilder sulfuras() {
        return this
                .withName("Sulfuras, Hand of Ragnaros")
                .withQuality(80)
                .withSellIn(80)
                .withUpdateStrategy(new UpdateLegendary());
    }

    public ItemEntityBuilder backstagePass() {
        return this
                .standard()
                .withName("Backstage Pass")
                .withUpdateStrategy(new UpdateBackstagePass());
    }

    public ItemEntityBuilder withSellIn(int sellIn) {
        item.setSellIn(sellIn);
        return this;
    }

    public ItemEntityBuilder withQuality(int quality) {
        item.setQuality(quality);
        return this;
    }

    public ItemEntityBuilder withName(String name) {
        item.setName(name);
        return this;
    }

    private ItemEntityBuilder withUpdateStrategy(UpdateStrategy updateStrategy) {
        item.setUpdateStrategy(updateStrategy);
        return this;
    }

    public ItemEntityBuilder conjuredItem() {
        return this
                .standard()
                .withName("Conjured Staff of Wizdom")
                .withUpdateStrategy(new UpdateConjured());
    }
}
