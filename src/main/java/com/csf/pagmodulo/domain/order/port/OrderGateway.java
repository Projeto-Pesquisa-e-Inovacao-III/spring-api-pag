package com.csf.pagmodulo.domain.order.port;


import com.csf.pagmodulo.domain.order.entity.Order;

public interface OrderGateway {
    Order saveOrder (Order order);
}
