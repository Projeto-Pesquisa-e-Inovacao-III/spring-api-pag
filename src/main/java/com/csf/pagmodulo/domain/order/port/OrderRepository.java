package com.csf.pagmodulo.domain.order.port;

import com.csf.pagmodulo.domain.order.entity.Order;

public interface OrderRepository {
    Order save(Order order);
    boolean existsByGatewayOrderId(String gatewayOrderId);
}
