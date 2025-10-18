package com.example.storefront.service;

import com.example.storefront.dto.StockStatusMessage;

public interface IProductChangeAvailabilityConsumer {

    void receive(final StockStatusMessage message);

}
