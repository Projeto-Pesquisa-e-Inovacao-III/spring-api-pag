package com.fillipe.pagmodulo.domain.order.port;

import com.fillipe.pagmodulo.domain.order.entity.Order;

public interface OrderRepository {
    Order save(Order order);
}
