package com.example.storefront.service.impl;

import com.example.storefront.dto.StockStatusMessage;
import com.example.storefront.service.IProductChangeAvailabilityConsumer;
import com.example.storefront.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductChangeAvailabilityConsumer implements IProductChangeAvailabilityConsumer {

    private final IProductService service;

    @RabbitListener(queues = "spring.rabbitmq.queue.product.change.availability.queue}")
    @Override
    public void receive(StockStatusMessage message) {
        service.changeActivated(message.id(), message.active());
    }

}
