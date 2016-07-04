package org.jusecase.katas.gildedrose.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemGatewayMock implements ItemGateway {
    private List<ItemEntity> items = new ArrayList<>();

    @Override
    public List<ItemEntity> getItems() {
        return items;
    }

    public void givenItems(ItemEntity ... items) {
        this.items.addAll(Arrays.asList(items));
    }
}
