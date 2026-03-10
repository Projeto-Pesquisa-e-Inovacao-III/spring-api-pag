package com.fillipe.pagmodulo.domain.order.port;

import com.fillipe.pagmodulo.domain.order.entity.Order;

public interface OrderGateway {
    Order saveOrder (Order order);
}
