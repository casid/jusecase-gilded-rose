package org.jusecase.katas.gildedrose;

import org.jusecase.Usecase;
import org.jusecase.katas.gildedrose.entities.Item;
import org.jusecase.katas.gildedrose.entities.ItemEntity;
import org.jusecase.katas.gildedrose.entities.ItemGateway;

public class UpdateItems implements Usecase<UpdateItems.Request, UpdateItems.Response> {

    private final ItemGateway itemGateway;

    public UpdateItems(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    @Override
    public Response execute(Request request) {
        itemGateway.getItems().forEach(ItemEntity::update);
        return null;
    }

    public static class Request {}

    public static class Response {}
}
