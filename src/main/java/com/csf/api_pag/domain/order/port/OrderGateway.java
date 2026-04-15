package com.csf.api_pag.domain.order.port;


import com.csf.api_pag.domain.order.entity.Order;

public interface OrderGateway {
    Order saveOrder (Order order);
}
