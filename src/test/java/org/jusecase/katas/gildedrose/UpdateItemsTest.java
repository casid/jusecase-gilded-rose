package org.jusecase.katas.gildedrose;

import org.junit.Before;
import org.junit.Test;
import org.jusecase.UsecaseTest;
import org.jusecase.builders.Builder;
import org.jusecase.katas.gildedrose.UpdateItems.Request;
import org.jusecase.katas.gildedrose.UpdateItems.Response;
import org.jusecase.katas.gildedrose.entities.Item;
import org.jusecase.katas.gildedrose.entities.ItemEntity;
import org.jusecase.katas.gildedrose.entities.ItemGatewayMock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jusecase.Builders.a;
import static org.jusecase.Builders.an;
import static org.jusecase.katas.gildedrose.entities.ItemEntityBuilder.item;

public class UpdateItemsTest extends UsecaseTest<Request, Response> {

    private ItemGatewayMock itemGateway = new ItemGatewayMock();

    @Before
    public void setUp() {
        usecase = new UpdateItems(itemGateway);
        givenRequest(a(request()));
    }

    @Test
    public void qualityIsReduced() {
        givenItems(an(item().standard().withQuality(10)));
        whenItemsAreUpdated();
        thenItemQualityIs(9);
    }

    private void whenItemsAreUpdated() {
        whenRequestIsExecuted();
    }

    @Test
    public void sellInIsReduced() {
        givenItems(an(item().standard().withSellIn(5)));
        whenItemsAreUpdated();
        thenItemSellInIs(4);
    }

    @Test
    public void sellInPassed_qualityDegradesTwiceAsFast() {
        givenItems(an(item().standard().withSellIn(-10).withQuality(10)));
        whenItemsAreUpdated();
        thenItemQualityIs(8);
    }

    @Test
    public void sellInReachesZero_qualityDegradesRegulary() {
        givenItems(an(item().standard().withSellIn(1).withQuality(10)));
        whenItemsAreUpdated();
        thenItemQualityIs(9);
    }

    @Test
    public void sellInBecomesNegative_qualityDegradesRegulary() {
        givenItems(an(item().standard().withSellIn(0).withQuality(10)));
        whenItemsAreUpdated();
        thenItemQualityIs(9);
    }

    @Test
    public void itemQualityCannotBecomeNegative() {
        givenItems(an(item().standard().withQuality(0)));
        whenItemsAreUpdated();
        thenItemQualityIs(0);
    }

    @Test
    public void itemQualityCannotBecomeNegative_evenWhenSellInDateIsReached() {
        givenItems(an(item().standard().withQuality(1).withSellIn(-1)));
        whenItemsAreUpdated();
        thenItemQualityIs(0);
    }

    @Test
    public void agedBree_increasesInQuality() {
        givenItems(an(item().agedBree().withQuality(1)));
        whenItemsAreUpdated();
        thenItemQualityIs(2);
    }

    @Test
    public void agedBree_maxQuality() {
        givenItems(an(item().agedBree().withQuality(50)));
        whenItemsAreUpdated();
        thenItemQualityIs(50);
    }

    @Test
    public void agedBree_sellInIsReduced() {
        givenItems(an(item().agedBree().withSellIn(10)));
        whenItemsAreUpdated();
        thenItemSellInIs(9);
    }

    @Test
    public void legendaryItemsAreTimeless() {
        givenItems(an(item().sulfuras()));

        whenItemsAreUpdated();

        thenItemQualityIs(80);
        thenItemSellInIs(80);
    }

    @Test
    public void backstagePass_sellInIsReduced() {
        givenItems(an(item().backstagePass().withSellIn(5)));
        whenItemsAreUpdated();
        thenItemSellInIs(4);
    }

    @Test
    public void backstagePass_normalIncreaseInQuality() {
        givenItems(an(item().backstagePass().withSellIn(11).withQuality(10)));
        whenItemsAreUpdated();
        thenItemQualityIs(11);
    }

    @Test
    public void backstagePass_doubleIncreaseInQuality() {
        givenItems(an(item().backstagePass().withSellIn(10).withQuality(10)));
        whenItemsAreUpdated();
        thenItemQualityIs(12);
    }

    @Test
    public void backstagePass_tripleIncreaseInQuality() {
        givenItems(an(item().backstagePass().withSellIn(5).withQuality(10)));
        whenItemsAreUpdated();
        thenItemQualityIs(13);
    }

    @Test
    public void backstagePass_maxQuality() {
        givenItems(an(item().backstagePass().withQuality(50)));
        whenItemsAreUpdated();
        thenItemQualityIs(50);
    }

    @Test
    public void backstagePass_dropToZeroQualityAfterConcert() {
        givenItems(an(item().backstagePass().withSellIn(0).withQuality(10)));
        whenItemsAreUpdated();
        thenItemQualityIs(0);
    }

    @Test
    public void conjuredItem_qualityDegeneratesTwiceAsFast() {
        givenItems(an(item().conjuredItem().withQuality(10)));
        whenItemsAreUpdated();
        thenItemQualityIs(8);
    }

    @Test
    public void conjuredItem_qualityDegeneratesTwiceAsFastWhenSellInDateIsPassed() {
        givenItems(an(item().conjuredItem().withSellIn(-1).withQuality(10)));
        whenItemsAreUpdated();
        thenItemQualityIs(6);
    }

    @Test
    public void conjuredItem_sellInIsReduced() {
        givenItems(an(item().conjuredItem().withSellIn(10)));
        whenItemsAreUpdated();
        thenItemSellInIs(9);
    }

    @Test
    public void multipleItemsAreUpdated() {
        givenItems(
                an(item().agedBree().withQuality(10)),
                an(item().standard().withQuality(10))
        );

        whenItemsAreUpdated();

        assertThat(getItem(0).getQuality()).isEqualTo(11);
        assertThat(getItem(1).getQuality()).isEqualTo(9);
    }

    private void thenItemQualityIs(int expected) {
        assertThat(getItem(0).getQuality()).isEqualTo(expected);
    }

    private void thenItemSellInIs(int expected) {
        assertThat(getItem(0).getSellIn()).isEqualTo(expected);
    }

    private Item getItem(int index) {
        return itemGateway.getItems().get(index);
    }

    private RequestBuilder request() {
        return new RequestBuilder();
    }

    private void givenItems(ItemEntity ... items) {
        itemGateway.givenItems(items);
    }

    private static class RequestBuilder implements Builder<Request> {
        private Request request = new Request();

        @Override
        public Request build() {
            return request;
        }
    }
}